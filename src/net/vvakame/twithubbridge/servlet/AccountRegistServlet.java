package net.vvakame.twithubbridge.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.vvakame.twithubbridge.helper.EncryptUtil;
import net.vvakame.twithubbridge.meta.AccountMapperDataMeta;
import net.vvakame.twithubbridge.model.AccountMapperData;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;

@SuppressWarnings("serial")
public class AccountRegistServlet extends HttpServlet {

	private static final String PROP = "twithubbridge";
	private static String CRYPT_KEY = "default";

	private static final Logger log = Logger
			.getLogger(AccountRegistServlet.class.getName());

	public void init() throws ServletException {
		ResourceBundle rb = ResourceBundle.getBundle(PROP, Locale.getDefault());
		CRYPT_KEY = rb.getString("cryptKey");
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		String twitter = req.getParameter("twitter");
		String github = req.getParameter("github");
		String apiKey = req.getParameter("apikey");
		String mode = req.getParameter("mode");
		if (twitter != null && github != null && apiKey != null) {
			twitter = twitter.trim();
			github = github.trim();
			apiKey = apiKey.trim();
		} else {
			twitter = "";
			github = "";
			apiKey = "";
		}

		if (!"".equals(twitter) && !"".equals(github) && !"".equals(apiKey)) {

			String result = "";

			String apiKeyEncrypted = null;
			try {
				apiKeyEncrypted = EncryptUtil.encrypt(CRYPT_KEY, apiKey);
				apiKey = null;
			} catch (Exception e) {
				throw new ServletException(e);
			}

			log.info("twitter=" + twitter + ", github=" + github);

			if ("delete".equals(mode)) {
				AccountMapperDataMeta aMeta = AccountMapperDataMeta.get();
				List<Key> keys = Datastore.query(aMeta).filter(
						aMeta.twitter.equal(twitter),
						aMeta.github.equal(github),
						aMeta.apiKeyEncrypted.equal(apiKeyEncrypted))
						.asKeyList();
				if (keys.size() != 0) {
					result = "deleted!<br>";
					Datastore.delete(keys);
				}
			} else {
				AccountMapperDataMeta aMeta = AccountMapperDataMeta.get();
				List<Key> keys = Datastore.query(aMeta).filter(
						aMeta.twitter.equal(twitter),
						aMeta.github.equal(github)).asKeyList();
				if (keys.size() != 0) {
					result = "replaced!<br>";
					Datastore.delete(keys);
				}

				AccountMapperData account = new AccountMapperData();

				account.setTwitter(twitter);
				account.setGithub(github);
				account.setApiKeyEncrypted(apiKeyEncrypted);
				Datastore.put(account);
			}

			result += "success!!<br>";
			req.setAttribute("result", result);
		}

		ServletContext sc = getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/regist.jsp");
		rd.forward(req, res);
	}
}
