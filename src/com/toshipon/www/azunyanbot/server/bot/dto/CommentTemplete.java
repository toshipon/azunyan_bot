package com.toshipon.www.azunyanbot.server.bot.dto;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * botのつぶやき用のテンプレートクラスです。
 * @author toshipon
 *
 */
@SuppressWarnings("serial")
@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable="true")
public class CommentTemplete implements Serializable{

	public CommentTemplete() {
		super();
	}
	
	public CommentTemplete(String comment, String creUser) {
		this.comment = comment;
		this.creUser = creUser;
	}
	
	@SuppressWarnings("unused")
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
	
	@Persistent
	private String comment;
	
	@Persistent
	private String creUser;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getCreUser() {
		return creUser;
	}

	public void setCreUser(String creUser) {
		this.creUser = creUser;
	}
	
}
