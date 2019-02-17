package com.deliguoo.ddsapp.vo.user;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.util.StringUtils;

public class User implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	@Indexed
	private String wechatId;
	@Indexed
	private String nickName;
	private String location;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getWechatId() {
		return wechatId;
	}
	public void setWechatId(String wechatId) {
		this.wechatId = wechatId;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	public User() {}
	public User(String wechatId, String nickName, String location) {
		this.wechatId = wechatId;
		this.nickName = nickName;
		this.location = location;
	}
	public boolean isEmpty() {
		return StringUtils.isEmpty(wechatId) || StringUtils.isEmpty(nickName);
	}
    @Override
    public String toString() {
        return String.format(
                "User[wechatId='%s', nickName='%s', location='%s']",
                wechatId, nickName, location);
    }
}