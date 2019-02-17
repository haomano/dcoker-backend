package com.deliguoo.ddsapp.vo.content;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

import com.deliguoo.ddsapp.vo.user.User;

public class Post {
	public Post() {}
	@Id
	private String id;
	@TextIndexed // The question field
	private String q;
	@DBRef	// The user field
	private User u;
//	@DBRef // The comment list field
//	private List<Comment> c;
	// The date field when the question was created
	private Date d;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getQ() {
		return q;
	}
	public void setQ(String q) {
		this.q = q;
	}
	public User getU() {
		return u;
	}
	public void setU(User u) {
		this.u = u;
	}
//	public List<Comment> getC() {
//		return c;
//	}
//	public void setC(List<Comment> c) {
//		this.c = c;
//	}
	public Date getD() {
		return d;
	}
	public void setD(Date d) {
		this.d = d;
	}
	
}
