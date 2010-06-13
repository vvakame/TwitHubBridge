package net.vvakame.twithubbridge.servlet;

import java.io.IOException;
import java.net.MalformedURLException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.vvakame.twithubbridge.helper.EncryptUtil;
import net.vvakame.twithubbridge.helper.HttpPostMultipartWrapper;
import net.vvakame.twithubbridge.meta.AccountMapperDataMeta;
import net.vvakame.twithubbridge.meta.TweetDataMeta;
import net.vvakame.twithubbridge.model.AccountMapperData;
import net.vvakame.twithubbridge.model.TweetData;

import org.slim3.datastore.Datastore;

import com.esotericsoftware.yamlbeans.YamlReader;

@SuppressWarnings("serial")
public class PostGithubServlet extends HttpServlet {

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

				@SuppressWarnings("unchecked")
				Map<String, Object> yaml = (Map<String, Object>) new YamlReader(
						response).read();

				// 失敗例
				// Token違う
				// {"error":"not authorized"}

				if (yaml.containsKey("error")) {
					throw new IllegalStateException(
							"Github returns error! message="
									+ yaml.get("error"));
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
			}
		}
	}
}
