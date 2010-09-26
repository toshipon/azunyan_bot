package com.toshipon.www.azunyanbot.server.bot.dto;

import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * botの行動の処理間隔を保持するクラスです。
 * @author toshipon
 *
 */
@SuppressWarnings("serial")
public class ProcessingInterval implements Serializable {

	public ProcessingInterval() {
		super();
	}
	
	@SuppressWarnings("unused")
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
	
	@Persistent
	private String actionName;
	
	@Persistent
	private Date lastDate;
	
	@Persistent
	private Long interval;

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public Date getLastDate() {
		return lastDate;
	}

	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}

	public long getInterval() {
		return interval;
	}

	public void setInterval(long interval) {
		this.interval = interval;
	}
}
