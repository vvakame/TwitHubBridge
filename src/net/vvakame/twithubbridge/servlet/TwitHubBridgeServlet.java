package net.vvakame.twithubbridge.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class TwitHubBridgeServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException {

		res.setContentType("text/plain");
		res.getWriter().println("ぷよぷよ");
	}
}
