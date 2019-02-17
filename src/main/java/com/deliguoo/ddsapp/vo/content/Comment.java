package com.deliguoo.ddsapp.vo.content;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

import com.deliguoo.ddsapp.vo.user.User;

public class Comment {
	@Id
	private String id;
	// The comment field
	private String c;
	@DBRef	// The user field
	private User u;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getC() {
		return c;
	}
	public void setC(String c) {
		this.c = c;
	}
	public User getU() {
		return u;
	}
	public void setU(User u) {
		this.u = u;
	}

}
