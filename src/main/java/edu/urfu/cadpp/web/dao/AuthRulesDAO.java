package edu.urfu.cadpp.web.dao;

import java.util.List;

import edu.urfu.cadpp.web.entity.AuthRules;

public interface AuthRulesDAO {

	/**
	 * Adds new object into ORM
	 * 
	 * @param authRules
	 *            object toadd
	 */
	void addAuthRules(AuthRules authRules);

	/**
	 * Removes object from ORM
	 * 
	 * @param user_id
	 *            id of object to delete
	 */
	void deleteAllUserAuthRules(long user_id);

	void deleteAllRoleAuthRules(String role_id);

	List<AuthRules> getAllUserAuthRules(Long user_id);

}
