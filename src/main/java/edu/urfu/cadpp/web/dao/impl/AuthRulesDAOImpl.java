package edu.urfu.cadpp.web.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.urfu.cadpp.web.dao.AuthRulesDAO;
import edu.urfu.cadpp.web.entity.AuthRules;

@Repository
@Transactional
public class AuthRulesDAOImpl implements AuthRulesDAO {

	private SessionFactory sessionFactory;

	@Autowired
	public AuthRulesDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void addAuthRules(AuthRules authRules) {
		sessionFactory.getCurrentSession().save(authRules);
	}

	public void deleteAllUserAuthRules(long user_id) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(AuthRules.class);
		criteria.add(Restrictions.eq("id.user_id", user_id));
		List<AuthRules> list = (List<AuthRules>) criteria.list();

		final Session session = sessionFactory.getCurrentSession();

		for (AuthRules ar : list) {
			session.delete(ar);
		}
		session.flush();
	}

	public void deleteAllRoleAuthRules(String role_id) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(AuthRules.class);
		criteria.add(Restrictions.eq("id.role_id", role_id));
		List<AuthRules> list = (List<AuthRules>) criteria.list();

		final Session session = sessionFactory.getCurrentSession();

		for (AuthRules ar : list) {
			session.delete(ar);
		}
		session.flush();
	}

	public List<AuthRules> getAllUserAuthRules(Long user_id) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(AuthRules.class);
		criteria.add(Restrictions.eq("id.user_id", user_id));
		return (List<AuthRules>) criteria.list();
	}
}