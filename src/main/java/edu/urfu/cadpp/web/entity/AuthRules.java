package edu.urfu.cadpp.web.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity(name = "users_roles")
public class AuthRules {

	@EmbeddedId
	private AuthRulesId id;

	public AuthRules() {
		id = new AuthRulesId();
	}

	public AuthRulesId getId() {
		return id;
	}

	public void setId(AuthRulesId id) {
		this.id = id;
	}

}