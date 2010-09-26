package com.toshipon.www.azunyanbot.server.bot.dto;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * followerÇÃuserIdÇï€éùÇ∑ÇÈÉNÉâÉXÇ≈Ç∑ÅB
 * @author toshipon
 *
 */
@SuppressWarnings("serial")
@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable="true")
public class Follower implements Serializable {

	public Follower() {
		super();
	}
	
	public Follower(int userId) {
		super();
		this.userId = Long.parseLong(String.valueOf((userId)));
	}
	
	@SuppressWarnings("unused")
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;

	@Persistent
	private Long userId;
	
	public int getUserId() {
		return userId.intValue();
	}

	public void setUserId(int userId) {
		this.userId = Long.parseLong(String.valueOf(userId));
	}
	
}
