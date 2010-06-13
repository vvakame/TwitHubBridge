package net.vvakame.twithubbridge.servlet;

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
public class TweetReaderServlet extends HttpServlet {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(TweetReaderServlet.class
			.getName());

	public void init() throws ServletException {
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		AccountMapperDataMeta aMeta = AccountMapperDataMeta.get();
		List<AccountMapperData> accounts = Datastore.query(aMeta).asList();

		TwitterFactory twitFact = new TwitterFactory();
		Twitter twit = twitFact.getInstance();

		List<TweetData> tweetList = new ArrayList<TweetData>();
		List<Status> statusList = new ArrayList<Status>();

		try {
			ReadStatusDataMeta rMeta = ReadStatusDataMeta.get();
			for (AccountMapperData account : accounts) {
				ReadStatusData readStatus = Datastore.query(rMeta).filter(
						rMeta.screenName.equal(account.getTwitter()))
						.asSingle();
				if (readStatus != null) {
					String statusId = readStatus.getStatusId();
					Paging paging = new Paging(Long.parseLong(statusId));
					ResponseList<Status> resList = twit.getUserTimeline(account
							.getTwitter(), paging);

					statusList.addAll(resList);
				}
				// TODO 明らかに取りこぼしのある実装になっている
				statusList.addAll(twit.getUserTimeline(account.getTwitter()));
			}
		} catch (TwitterException e) {
			throw new ServletException(e);
		}

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
