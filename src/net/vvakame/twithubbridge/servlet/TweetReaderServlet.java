package net.vvakame.twithubbridge.servlet;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.vvakame.twithubbridge.meta.AccountMapperDataMeta;
import net.vvakame.twithubbridge.model.AccountMapperData;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.labs.taskqueue.QueueFactory;
import com.google.appengine.api.labs.taskqueue.TaskOptions;

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

		for (AccountMapperData account : accounts) {
			QueueFactory.getDefaultQueue().add(
					TaskOptions.Builder.url("/workers/tweetreader").param("t",
							account.getTwitter()).param("g",
							account.getGithub()));
		}

		ServletContext sc = getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/done.jsp");
		rd.forward(req, res);
	}
}
