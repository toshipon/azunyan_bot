package com.toshipon.www.azunyanbot.server.bot.action;

import java.util.logging.Logger;

import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;

public class PostCommentAction implements ActionTemplete{

	private static final Logger LOGGER = Logger.getLogger(PostCommentAction.class.getName());
	
	public void execute(Twitter twitter) {
//		interval = getInterval("postMessage");
//		if (interval == null) {return;}
//		
//		Date now = new Date();
//		if (now.getTime() < interval.getLastDate().getTime() + interval.getInterval()) {
//			
//		}
		
		try {
			Status status = selectFriend(twitter);
			postMessage(status, twitter);
		} catch (TwitterException e) {
			LOGGER.info(e.getMessage());
		}
		
	}

	private void postMessage(Status status, Twitter twitter) throws TwitterException {
		User user = status.getUser();
		if ("_azunyan_bot".equals(user.getScreenName())) {return;}
		twitter.updateStatus("@" + user.getScreenName() + " æ”yIŽdŽ–‚·‚é‚Å‚·I");
	}

	private Status selectFriend(Twitter twitter) throws TwitterException {
//		PagableResponseList<User> statuses = twitter.getFriendsStatuses();
		ResponseList<Status> statuses = twitter.getFriendsTimeline();
//		int length = statuses.size();
//		if (length == 1) {return statuses.get(0);}
//		return statuses.get((int) Math.round((Math.random()*(length-1))));
		return statuses.get(0);
	}

//	@SuppressWarnings("unchecked")
//	private ProcessingInterval getInterval(String actionName) {
//		PersistenceManager pm = PMF.get().getPersistenceManager();
//		Query query = pm.newQuery(ProcessingInterval.class);
//		List<ProcessingInterval> intervals = (List<ProcessingInterval>) query.execute();
//		for (ProcessingInterval interval : intervals) {
//			if (actionName.equals(interval.getActionName())) {
//				return interval;
//			}
//		}
//		return null;
//	}
}
