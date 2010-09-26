package com.toshipon.www.azunyanbot.server.token;

import java.io.IOException;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.toshipon.www.azunyanbot.server.common.PMF;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.http.AccessToken;
import twitter4j.http.RequestToken;

@SuppressWarnings("serial")
public class CallbackServlet extends HttpServlet {

	private static final Logger LOGGER = Logger.getLogger(CallbackServlet.class.getName());
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		LOGGER.info("[call]:doGet");
		
		// セッションからtwitterオブジェクトとRequestTokenを取得
		AccessToken accessToken = null;
		Twitter twitter = (Twitter) req.getSession().getAttribute("twitter");
		RequestToken requestToken = (RequestToken) req.getSession().getAttribute("requestToken");
		String verifier = req.getParameter("oauth_verifier");
		
		// AccessTokenの取得
		try {
			accessToken = twitter.getOAuthAccessToken(requestToken, verifier);
			req.getSession().removeAttribute("requestToken");
		} catch (TwitterException e) {
			LOGGER.info(e.getMessage());
		}
		
		// TokenオブジェクトにAccessToken/SecretとbotNameを格納してGAEに保存
		if (accessToken != null) {
			PersistenceManager pm = PMF.get().getPersistenceManager();
			Token token = new Token();
			token.setAccessToken(accessToken.getToken());
			token.setAccessSecret(accessToken.getTokenSecret());
			token.setBotname((String) req.getSession().getAttribute("botname"));
			pm.makePersistent(token);
			pm.close();
		}
		
		req.getSession().removeAttribute("botname");
		resp.sendRedirect(req.getContextPath() + "/");
	}

}
