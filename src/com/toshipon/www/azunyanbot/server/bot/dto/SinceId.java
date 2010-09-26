package com.toshipon.www.azunyanbot.server.bot.dto;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * Actionの前回取得ID情報を保持するクラスです。
 * @author toshipon
 *
 */
@SuppressWarnings("serial")
@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable="true")
public class SinceId implements Serializable {

	public SinceId() {
		super();
	}

	public SinceId(Long sinceId, String actionName) {
		super();
		this.sinceId = sinceId;
		this.ationName = actionName;
	}
	
	@SuppressWarnings("unused")
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;

	@Persistent
	private Long sinceId;
	
	@Persistent
	private String ationName;

	public Long getSinceId() {
		return sinceId;
	}

	public void setSinceId(Long sinceId) {
		this.sinceId = sinceId;
	}

	public String getAtionName() {
		return ationName;
	}

	public void setAtionName(String ationName) {
		this.ationName = ationName;
	}
	
}
