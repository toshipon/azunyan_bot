package com.toshipon.www.azunyanbot.server.token;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.http.AccessToken;
import twitter4j.http.RequestToken;

@SuppressWarnings("serial")
public class GateAccessTokenServlet extends HttpServlet {

	private static final Logger LOGGER = Logger.getLogger(GateAccessTokenServlet.class.getName());

	private Twitter twitter = null;
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		LOGGER.info("[call]:doPost");
		
		String botName = req.getParameter("name");
		req.getSession().setAttribute("botname", botName);
		
		// accessTokenを空でtwitterオブジェクトを生成する
		// consumerkeyはappengine-web.xmlから
		TwitterFactory twitterFactory = new TwitterFactory();
		twitter = twitterFactory.getOAuthAuthorizedInstance(new AccessToken("", ""));
		req.getSession().setAttribute("twitter", twitter);
		
		// callback用のURLを生成して格納
		StringBuffer callbackUrl = req.getRequestURL();
		int index = callbackUrl.lastIndexOf("/");
		callbackUrl.replace(index, callbackUrl.length(), "").append("/callback");
		
		try {
			// RequestTokenを取得してセッションに格納、アプリケーション認可画面へ遷移
			RequestToken requestToken = twitter.getOAuthRequestToken(callbackUrl.toString());
			req.getSession().setAttribute("requestToken", requestToken);
			resp.sendRedirect(requestToken.getAuthenticationURL());
		} catch (TwitterException e) {
			LOGGER.info(e.getMessage());
		}
	}
}
