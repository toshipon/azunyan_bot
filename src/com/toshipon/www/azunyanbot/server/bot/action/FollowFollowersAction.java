package com.toshipon.www.azunyanbot.server.bot.action;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.JDOFatalUserException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.toshipon.www.azunyanbot.server.bot.dto.Follower;
import com.toshipon.www.azunyanbot.server.common.PMF;

import twitter4j.IDs;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class FollowFollowersAction implements ActionTemplete{
	
	private static final Logger LOGGER = Logger.getLogger(FollowFollowersAction.class.getName());
	private PersistenceManager pm;
	
	@Override
	public void execute(Twitter twitter) {
		pm = PMF.get().getPersistenceManager();
		try {
			IDs ids = twitter.getFollowersIDs();
			List<Follower> list = getFollowers();
			for (int userId : ids.getIDs()) {
				if (!existUser(list, userId)) {
					try {
						twitter.createFriendship(userId);
					} catch(TwitterException e) {
						LOGGER.info("already stored follow id:" + userId);
					}
					saveFollower(userId);
					LOGGER.info("follow id:" + userId);
				}
			}
		} catch (TwitterException e) {
			LOGGER.info(e.getMessage());
		}
		pm.close();
	}

	private boolean existUser(List<Follower> followers, int userId) {
		for (Follower follower : followers) {
			if (follower.getUserId() == userId) {
				return true;
			}
		}
		return false;
	}

	// followしたユーザをデータストアに格納
	private void saveFollower(int userId) {
		Follower follower = new Follower();
		follower.setUserId(userId);
		pm.makePersistent(follower);
	}

	// データストアからfollower情報を取得
	@SuppressWarnings("unchecked")
	private List<Follower> getFollowers() {
		List<Follower> followers = null;
		try {
			Query query = pm.newQuery(Follower.class);
			followers = (List<Follower>) query.execute();
		} catch (JDOFatalUserException e) {
			// テーブルが存在してなかったら初回は例外が発生する
			LOGGER.info(e.getMessage());
			followers = new ArrayList<Follower>();
		}
		return followers;
	}
	
	
}
