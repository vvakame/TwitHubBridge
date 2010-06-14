package net.vvakame.twithubbridge.taskqueue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.vvakame.twithubbridge.meta.AccountMapperDataMeta;
import net.vvakame.twithubbridge.meta.ReadStatusDataMeta;
import net.vvakame.twithubbridge.model.AccountMapperData;
import net.vvakame.twithubbridge.model.ReadStatusData;
import net.vvakame.twithubbridge.model.TweetData;

import org.slim3.datastore.Datastore;

import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

@SuppressWarnings("serial")
public class TweetReaderTaskQueueServlet extends HttpServlet {

	@SuppressWarnings("unused")
	private static final Logger log = Logger
			.getLogger(TweetReaderTaskQueueServlet.class.getName());

	private static TwitterFactory twitFact = null;

	public void init() throws ServletException {
		twitFact = new TwitterFactory();
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		String twitter = req.getParameter("t");

		if (twitter == null) {
			return;
		}

		AccountMapperDataMeta aMeta = AccountMapperDataMeta.get();
		List<AccountMapperData> accounts = Datastore.query(aMeta).filter(
				aMeta.twitter.equal(twitter)).asList();

		if (accounts == null || accounts.size() <= 0) {
			return;
		}

		Twitter twit = twitFact.getInstance();

		try {
			ReadStatusDataMeta rMeta = ReadStatusDataMeta.get();

			ReadStatusData readStatus = Datastore.query(rMeta).filter(
					rMeta.screenName.equal(twitter)).asSingle();

			Paging paging = new Paging();
			paging.setCount(200);
			if (readStatus != null) {
				paging.setSinceId(readStatus.getStatusId());
			}
			ResponseList<Status> statusList = twit.getUserTimeline(twitter,
					paging);

			if (0 < statusList.size()) {
				pushTweet(statusList);

				Status status = statusList.get(0);
				if (readStatus == null) {
					readStatus = new ReadStatusData();
					readStatus.setScreenName(twitter);
				}
				readStatus.setStatusId(status.getId());
				readStatus.setCreateAt(status.getCreatedAt());
				Datastore.put(readStatus);
			}
		} catch (TwitterException e) {
			throw new ServletException(e);
		}
	}

	private void pushTweet(List<Status> statusList) {

		List<TweetData> tweetList = new ArrayList<TweetData>();

		for (Status status : statusList) {
			String txt = status.getText();
			if (txt.startsWith("TODO ")) {
				String mes = txt.substring(5).trim();
				mes.trim();
				int idx = -1;
				idx = mes.indexOf(" ");
				String project = mes.substring(0, idx);
				mes = mes.substring(idx + 1);
				String[] ary = mes.split("/");
				String title = null;
				String body = null;
				if (ary.length > 1) {
					title = ary[0].trim();
					body = ary[1].trim();
				}
				if (project != null && title != null && body != null
						&& !"".equals(project) && !"".equals(title)) {

					TweetData twiData = new TweetData();
					twiData.setScreenName(status.getUser().getScreenName());
					twiData.setTweet(txt);
					twiData.setDate(status.getCreatedAt());
					twiData.setProject(project);
					twiData.setTitle(title);
					twiData.setBody(body);
					tweetList.add(twiData);
				}
			}
		}

		Datastore.put(tweetList);
	}
}
