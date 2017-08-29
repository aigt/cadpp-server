package edu.urfu.cadpp.web.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity(name = "roles")
public class Roles {

	@Id
	@Column(name = "name")
	private String name;
	
	@Transient
	private Set<User> users = new HashSet<User>();

	public Roles() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

}
