package com.toshipon.www.azunyanbot.server.common;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

/**
 * for getting PersistenceManagerFactory Class.
 * @author toshipon
 *
 */
public class PMF {
	private static final PersistenceManagerFactory pmfInstance = 
		JDOHelper.getPersistenceManagerFactory("transactions-optional");
	
	private PMF() {}
	
	/**
	 * get PersistenceManagerFactory
	 * @return
	 */
	public static PersistenceManagerFactory get() {
		return pmfInstance;
	}

}
