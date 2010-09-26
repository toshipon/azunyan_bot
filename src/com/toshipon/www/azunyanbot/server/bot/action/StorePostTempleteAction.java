package com.toshipon.www.azunyanbot.server.bot.action;

import java.util.List;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.toshipon.www.azunyanbot.server.bot.dto.CommentTemplete;
import com.toshipon.www.azunyanbot.server.bot.dto.SinceId;
import com.toshipon.www.azunyanbot.server.common.Action;
import com.toshipon.www.azunyanbot.server.common.PMF;

import twitter4j.DirectMessage;
import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class StorePostTempleteAction implements ActionTemplete {

	private static final Logger LOGGER = Logger.getLogger(StorePostTempleteAction.class.getName());
	private PersistenceManager pm;
	
	@Override
	public void execute(Twitter twitter) {
		LOGGER.info("[call]");
		
		pm = PMF.get().getPersistenceManager();
		ResponseList<DirectMessage> messages = null;
		
		long id = getSinceId();
		Paging paging = new Paging(id);
		try {
			messages = twitter.getDirectMessages(paging);
		} catch (TwitterException e) {
			LOGGER.info(e.getMessage());
		}
		
		if (messages == null) { return; }
		storeMessage(messages);
		
		pm.close();
	}

	private void storeMessage(ResponseList<DirectMessage> messages) {
		for (DirectMessage message : messages) {
			CommentTemplete commentTemplete = new CommentTemplete(message.getText(), message.getSenderScreenName());
			pm.makePersistent(commentTemplete);
			LOGGER.info("stored comment :" + message.getText());
		}
	}

	@SuppressWarnings("unchecked")
	private long getSinceId() {
		long id = 1;
		
		Query query = pm.newQuery(SinceId.class);
		List<SinceId> list = (List<SinceId>) query.execute(new SinceId(null, Action.Store.getActionName()));
		
		for (SinceId sinceId : list) {
			if (Action.Store.getActionName().equals(sinceId.getAtionName())) {
				id = sinceId.getSinceId();
			}
		}
		return id;
	}

}
