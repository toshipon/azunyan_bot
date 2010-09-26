package com.toshipon.www.azunyanbot.server.common;

import com.toshipon.www.azunyanbot.server.bot.action.ActionTemplete;
import com.toshipon.www.azunyanbot.server.bot.action.FollowFollowersAction;
import com.toshipon.www.azunyanbot.server.bot.action.PostCommentAction;

public enum Action {

	Post	("post", PostCommentAction.class),
	Follow	("follow", FollowFollowersAction.class);
	
	private String actionName;
	private Class<? extends ActionTemplete> classInfo;
	
	private Action(String actionName, Class<? extends ActionTemplete> classInfo) {
		this.actionName = actionName;
		this.classInfo = classInfo;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public Class<? extends ActionTemplete> getClassInfo() {
		return classInfo;
	}

	public void setClassInfo(Class<? extends ActionTemplete> classInfo) {
		this.classInfo = classInfo;
	}
	
}
