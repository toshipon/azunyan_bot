package com.toshipon.www.azunyanbot.server.bot.action;

import java.util.logging.Logger;

public class ActionFactory {
	
	private static final Logger LOGGER = Logger.getLogger(ActionFactory.class.getName());
	
	public static ActionTemplete createAction(Class<? extends ActionTemplete> classInfo) {
		ActionTemplete action = null;
		try {
			action = (ActionTemplete) classInfo.newInstance();
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
		}
		return action;
	}
}
