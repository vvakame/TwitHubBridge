package net.vvakame.twithubbridge.servlet;

import java.io.IOException;
import java.net.MalformedURLException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.vvakame.twithubbridge.helper.EncryptUtil;
import net.vvakame.twithubbridge.helper.HttpPostMultipartWrapper;
import net.vvakame.twithubbridge.meta.AccountMapperDataMeta;
import net.vvakame.twithubbridge.meta.TweetDataMeta;
import net.vvakame.twithubbridge.model.AccountMapperData;
import net.vvakame.twithubbridge.model.BadData;
import net.vvakame.twithubbridge.model.TweetData;

import org.json.JSONException;
import org.json.JSONObject;
import org.slim3.datastore.Datastore;

@SuppressWarnings("serial")
public class PostGithubServlet extends HttpServlet {

	private static final Logger log = Logger.getLogger(PostGithubServlet.class
			.getName());

	private static final String PROP = "twithubbridge";
	private static String CRYPT_KEY = "default";

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

		TweetDataMeta tMeta = TweetDataMeta.get();
		List<TweetData> twitList = Datastore.query(tMeta).asList();
		for (TweetData twitData : twitList) {

			try {
				String uriStr = "http://github.com/api/v2/json/issues/open/%s/%s";

				AccountMapperDataMeta aMeta = AccountMapperDataMeta.get();
				AccountMapperData account = Datastore.query(aMeta).filter(
						aMeta.twitter.equal(twitData.getScreenName()))
						.asSingle();

				HttpPostMultipartWrapper post = new HttpPostMultipartWrapper(
						String.format(uriStr, account.getGithub(), twitData
								.getProject()));
				post.pushString("login", account.getGithub());
				post.pushString("token", EncryptUtil.decrypt(CRYPT_KEY, account
						.getApiKeyEncrypted()));
				post.pushString("title", twitData.getTitle());
				post.pushString("body", twitData.getBody());

				post.close();

				String response = post.readResponse();

				JSONObject json = null;
				try {
					json = new JSONObject(response);
				} catch (JSONException e) {
					// 握りつぶす
				}

				if (json == null || json.has("error")) {
					log.warning("catch up error!");
					BadData bad = new BadData();
					bad.setTwitter(account.getTwitter());
					bad.setGithub(account.getGithub());
					bad.setApiKeyEncrypted(account.getApiKeyEncrypted());
					bad.setStatusId(twitData.getStatusId());
					bad.setError(json != null && json.has("error") ? json
							.getString("error") : "unknown");
					Datastore.put(bad);
				}

				Datastore.delete(twitData.getKey());

			} catch (MalformedURLException e) {
				throw new ServletException(e);
			} catch (IOException e) {
				throw new ServletException(e);
			} catch (InvalidKeyException e) {
				throw new ServletException(e);
			} catch (IllegalBlockSizeException e) {
				throw new ServletException(e);
			} catch (NoSuchAlgorithmException e) {
				throw new ServletException(e);
			} catch (BadPaddingException e) {
				throw new ServletException(e);
			} catch (NoSuchPaddingException e) {
				throw new ServletException(e);
			} catch (JSONException e) {
				throw new ServletException(e);
			}
		}
		
		ServletContext sc = getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/done.jsp");
		rd.forward(req, res);
	}
}
