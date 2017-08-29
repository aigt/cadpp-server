package edu.urfu.cadpp.web.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.urfu.cadpp.web.dao.RolesDAO;
import edu.urfu.cadpp.web.entity.Roles;

@Repository
@Transactional
public class RolesDAOImpl implements RolesDAO {

	private SessionFactory sessionFactory;

	@Autowired
	public RolesDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void addRoles(Roles roles) {
		sessionFactory.getCurrentSession().save(roles);
	}

	public void deleteRolesByName(String role) {
		final Session session = sessionFactory.getCurrentSession();
		session.delete(getRolesByName(role));
		session.flush();
	}

	public Roles getRolesByName(String name) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Roles.class);
		criteria.add(Restrictions.eq("name", name));
		return (Roles) criteria.uniqueResult();
	}

	public List<Roles> getAllRoles() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Roles.class);
		return (List<Roles>) criteria.list();
	}
}