package com.toshipon.www.azunyanbot.server.bot.dto;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * botのつぶやき用のテンプレートクラスです。
 * @author toshipon
 *
 */
@SuppressWarnings("serial")
public class CommentTemplete implements Serializable{

	public CommentTemplete() {
		super();
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
