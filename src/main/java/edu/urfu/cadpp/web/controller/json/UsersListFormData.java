package edu.urfu.cadpp.web.controller.json;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnore;

import edu.urfu.cadpp.web.entity.AuthRules;
import edu.urfu.cadpp.web.entity.User;
import edu.urfu.cadpp.web.service.UserService;

public class UsersListFormData {
	Long id;
	String login;
	String name;
	String surname;
	String roles;
	public static UsersListFormData InstanceFromUser(User user, UserService userService) {
		UsersListFormData data = new UsersListFormData();
		data.setId(user.getId());
		data.setLogin(user.getLogin());
		data.setName(user.getName());
		data.setSurname(user.getSurname());
		
		String userRoles = "";
		List<AuthRules> rules = userService.getAllUserAuthRules(user.getId());
		for (AuthRules r : rules) {
			if (userRoles != "")
				userRoles = userRoles + ", ";
			userRoles = userRoles + r.getId().getRole_id();
		}
		data.setRoles(userRoles);
		
		return data;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
}
