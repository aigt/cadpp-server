package edu.urfu.cadpp.web.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AuthRulesId implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "user_id")
	private long user_id;

	@Column(name = "role_id")
	private String role_id;

	public AuthRulesId() {
	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public String getRole_id() {
		return role_id;
	}

	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}

}
