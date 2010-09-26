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
		
		// accessToken�����twitter�I�u�W�F�N�g�𐶐�����
		// consumerkey��appengine-web.xml����
		TwitterFactory twitterFactory = new TwitterFactory();
		twitter = twitterFactory.getOAuthAuthorizedInstance(new AccessToken("", ""));
		req.getSession().setAttribute("twitter", twitter);
		
		// callback�p��URL�𐶐����Ċi�[
		StringBuffer callbackUrl = req.getRequestURL();
		int index = callbackUrl.lastIndexOf("/");
		callbackUrl.replace(index, callbackUrl.length(), "").append("/callback");
		
		try {
			// RequestToken���擾���ăZ�b�V�����Ɋi�[�A�A�v���P�[�V�����F��ʂ֑J��
			RequestToken requestToken = twitter.getOAuthRequestToken(callbackUrl.toString());
			req.getSession().setAttribute("requestToken", requestToken);
			resp.sendRedirect(requestToken.getAuthenticationURL());
		} catch (TwitterException e) {
			LOGGER.info(e.getMessage());
		}
	}
}
