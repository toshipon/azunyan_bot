package com.toshipon.www.azunyanbot.server.token;

import java.io.Serializable;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.IdGeneratorStrategy;

@SuppressWarnings("serial")
@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable="true")
public class Token implements Serializable{

	public Token() {
		super();
	}
	
	public Token(String botName, String accessToken, String accessSecret) {
		super();
		this.botName = botName;
		this.accessToken = accessToken;
		this.accessSecret = accessSecret;
	}
	
	@SuppressWarnings("unused")
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
	
	@Persistent
	private String botName;
	
	@Persistent
	private String accessToken;
	
	@Persistent
	private String accessSecret;

	/**
	 * @return the botname
	 */
	public String getBotname() {
		return botName;
	}

	/**
	 * @param botname the botname to set
	 */
	public void setBotname(String botname) {
		this.botName = botname;
	}

	/**
	 * @return the accessToken
	 */
	public String getAccessToken() {
		return accessToken;
	}

	/**
	 * @param accessToken the accessToken to set
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	/**
	 * @return the accessSecret
	 */
	public String getAccessSecret() {
		return accessSecret;
	}

	/**
	 * @param accessSecret the accessSecret to set
	 */
	public void setAccessSecret(String accessSecret) {
		this.accessSecret = accessSecret;
	}
}
