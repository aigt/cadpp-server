package edu.urfu.cadpp.web.dao;

import java.util.List;

import edu.urfu.cadpp.web.entity.Roles;

public interface RolesDAO {

	void addRoles(Roles roles);

	void deleteRolesByName(String role);

	List<Roles> getAllRoles();

	Roles getRolesByName(String name);

}
