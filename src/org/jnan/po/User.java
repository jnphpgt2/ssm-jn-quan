package org.jnan.po;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class User {
	private Integer id;
	@Size(min=2,max=10,message="{user.username.length}")
	private  String username;
	//@NotNull(message="user.pwd.notnull")
	private String pwd;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
}
