package net.vvakame.twithubbridge.servlet;

import java.io.IOException;
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
import net.vvakame.twithubbridge.model.AccountMapperData;

import org.slim3.datastore.Datastore;

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

		String twitter = req.getParameter("twitter").trim();
		String github = req.getParameter("github").trim();
		String apiKey = req.getParameter("apikey").trim();
		if (twitter != null && github != null && apiKey != null) {
			String apiKeyEncrypted = null;
			try {
				apiKeyEncrypted = EncryptUtil.encrypt(CRYPT_KEY, apiKey);
				apiKey = null;
			} catch (Exception e) {
				throw new ServletException(e);
			}

			log.info("twitter=" + twitter + ", github=" + github);

			AccountMapperData account = new AccountMapperData();
			account.setTwitter(twitter);
			account.setGithub(github);
			account.setApiKeyEncrypted(apiKeyEncrypted);
			Datastore.put(account);
		}

		ServletContext sc = getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/regist.jsp");
		rd.forward(req, res);
	}
}
