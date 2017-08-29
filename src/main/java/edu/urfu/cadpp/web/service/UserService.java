package edu.urfu.cadpp.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.urfu.cadpp.web.dao.AuthRulesDAO;
import edu.urfu.cadpp.web.dao.RolesDAO;
import edu.urfu.cadpp.web.dao.UserDAO;
import edu.urfu.cadpp.web.entity.AuthRules;
import edu.urfu.cadpp.web.entity.Roles;
import edu.urfu.cadpp.web.entity.User;

@Service
@Transactional
public class UserService {

	@Autowired
	UserDAO userDAO;
	@Autowired
	AuthRulesDAO authRulesDAO;
	@Autowired
	RolesDAO rolesDAO;

	// User
	public void addUser(User user) throws IllegalArgumentException {
		userDAO.addUser(user);
	}

	public User addUserWithUserRoles(User user, List<String> roleNames) throws IllegalArgumentException {
		addUser(user);
		User us = getUserByLogin(user.getLogin());
		for (String roleName : roleNames) {
			Roles role = getRolesByName(roleName);
			AuthRules authRules = new AuthRules();
			authRules.getId().setUser_id(us.getId());
			authRules.getId().setRole_id(role.getName());
			addAuthRules(authRules);
		}

		return us;
	}

	public User getUserByLogin(String login) {
		return userDAO.getUserByLogin(login);
	}

	public void updateUser(User user) {
		userDAO.updateUser(user);
	}

	public void deleteUserByLogin(String login) {
		userDAO.deleteUserByLogin(login);
	}

	public void deleteUserWithRulesByLogin(String login) {
		User user = getUserByLogin(login);
		deleteAllUserAuthRules(user.getId());
		deleteUserByLogin(login);
	}

	public List<User> getAllUsers(String login) {
		return userDAO.getAllUsers(login);
	}

	public List<User> getUsersByIdList(List<Long> idList) {
		return userDAO.getUsersByIdList(idList);
	}

	public List<User> getUsersNotInIdList(List<Long> idList) {
		return userDAO.getUsersNotInIdList(idList);
	}

	public List<User> getUsersByPage(int pageid, int total) {
		return userDAO.getUsersByPage(pageid, total);
	}

	public Long getUserPageCount(int usersPerPage) {
		return userDAO.getUserPageCount(usersPerPage);
	}

	// Auth rules
	public void addAuthRules(AuthRules authRules) {
		authRulesDAO.addAuthRules(authRules);
	}

	public void deleteAllUserAuthRules(long user_id) {
		authRulesDAO.deleteAllUserAuthRules(user_id);
	}

	public void deleteAllRolesAuthRules(String role_id) {
		authRulesDAO.deleteAllRoleAuthRules(role_id);
	}

	public List<AuthRules> getAllUserAuthRules(long user_id) {
		return authRulesDAO.getAllUserAuthRules(user_id);
	}

	// Roles
	public void addRole(Roles roles) {
		rolesDAO.addRoles(roles);
	}

	public void deleteRolesByName(String name) {
		rolesDAO.deleteRolesByName(name);
	}

	public Roles getRolesByName(String name) {
		return rolesDAO.getRolesByName(name);
	}

	public List<Roles> getAllRoles() {
		return rolesDAO.getAllRoles();
	}
}
