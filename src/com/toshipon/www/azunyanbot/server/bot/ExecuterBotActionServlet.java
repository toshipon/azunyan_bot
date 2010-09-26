package com.toshipon.www.azunyanbot.server.bot;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import javax.cache.Cache;
import javax.cache.CacheException;
import javax.cache.CacheFactory;
import javax.cache.CacheManager;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.http.AccessToken;

import com.toshipon.www.azunyanbot.server.bot.action.ActionFactory;
import com.toshipon.www.azunyanbot.server.bot.action.ActionTemplete;
import com.toshipon.www.azunyanbot.server.common.Action;
import com.toshipon.www.azunyanbot.server.common.PMF;
import com.toshipon.www.azunyanbot.server.token.Token;

/**
 * ���N�G�X�g�p�����[�^��actionName�����ɑΏۂƂȂ�Action�����s���܂�
 * @author toshipon
 *
 */
@SuppressWarnings("serial")
public class ExecuterBotActionServlet extends HttpServlet {
	
	private static final Logger LOGGER = Logger.getLogger(ExecuterBotActionServlet.class.getName());
	private String cacheToken = "accessToken";
	private String cacheSecret = "accessSecret";

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		// ���s����action���̎擾
		String actionName = req.getParameter("action");
		if (actionName == null) { return; }
		
		// botName�̎擾
		String botName = req.getParameter("botname");
		if (botName == null) { return; }
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		// accesstoken�̎擾
		Cache cache = null;
		try {
			CacheFactory cacheFactory = CacheManager.getInstance().getCacheFactory();
			cache = cacheFactory.createCache(Collections.emptyMap());
		} catch (CacheException e) {
			LOGGER.info(e.getMessage());
		}
		
		if (cache.get(cacheToken) == null || cache.get(cacheSecret) == null) {
			Query query = pm.newQuery(Token.class);
			List<Token> tokens = (List<Token>) query.execute();
			for (Token token : tokens) {
				if (botName.equals(token.getBotname())) {
					cache.put(cacheToken, token.getAccessToken());
					cache.put(cacheSecret, token.getAccessSecret());
				}
			}
			pm.close();
		}
		
		// twitter�I�u�W�F�N�g�̐���
		Twitter twitter = new TwitterFactory().getOAuthAuthorizedInstance(
				new AccessToken((String)cache.get(cacheToken), (String)cache.get(cacheSecret)));
		
		// bot���������s
		execute(twitter, actionName);
	}

	// ���N�G�X�g�p�����[�^��actionName������bot���������s
	private void execute(Twitter twitter, String actinName) {
		for (Action action : Action.values()) {
			if (action.getActionName().equals(actinName)) {
				ActionTemplete actionTemplete = ActionFactory.createAction(action.getClassInfo());
				if (actionTemplete != null) { actionTemplete.execute(twitter); };
			}
		}
	}

}
