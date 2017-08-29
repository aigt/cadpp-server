package edu.urfu.cadpp.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.urfu.cadpp.web.dao.UserDAO;
import edu.urfu.cadpp.web.entity.User;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO {

	private SessionFactory sessionFactory;

	@Autowired
	public UserDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void addUser(User user) throws IllegalArgumentException {
		String login = user.getLogin();
		User existedUserWithTheSameLogin = getUserByLogin(login);
		if (existedUserWithTheSameLogin != null) {
			throw new IllegalArgumentException("There is the user with login " + login + " with id " + existedUserWithTheSameLogin.getId());
		}	
		
		sessionFactory.getCurrentSession().save(user);
	}
	
	public void deleteUserByLogin(String login) {
		final Session session = sessionFactory.getCurrentSession();
		session.delete(getUserByLogin(login));
		session.flush();
	}

	public User getUserByLogin(String login) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("login", login));
		return (User) criteria.uniqueResult();
	}

	public User getUserById(long id) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("id", id));
		return (User) criteria.uniqueResult();
	}

	public void updateUser(User user) {
		sessionFactory.getCurrentSession().update(user);
	}

	public List<User> getAllUsers(String login) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
		criteria.add(Restrictions.ne("login", login));
		return (List<User>) criteria.list();
	}

	public List<User> getUsersByIdList(List<Long> idList) {

		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from io.khasang.snet.entity.userauth.User u where u.id in :idlist");
		List<Integer> intList = new ArrayList<Integer>();

		for (Long lon : idList) {
			intList.add(Math.toIntExact(lon));
		}

		if (idList.size() == 0) {
			query.setParameter("idlist", 0);
		} else {
			query.setParameterList("idlist", intList);
		}
		return (List<User>) query.list();
	}

	public List<User> getUsersNotInIdList(List<Long> idList) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from io.khasang.snet.entity.userauth.User u where u.id not in :idlist");
		List<Integer> intList = new ArrayList<Integer>();

		for (Long lon : idList) {
			intList.add(Math.toIntExact(lon));
		}

		if (idList.size() == 0) {
			query.setParameter("idlist", 0);
		} else {
			query.setParameterList("idlist", intList);
		}
		return (List<User>) query.list();
	}

	public List<User> getUsersByPage(int pageid, int total) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from users");
		query.setFirstResult((pageid - 1) * total);
		query.setMaxResults(total);
		return (List<User>) query.list();
	}

	public Long getUserPageCount(int usersPerPage) {
		Session session = sessionFactory.getCurrentSession();
		Long count = ((Long)session.createQuery("select count(*) from users").uniqueResult());
		Long pages = count / usersPerPage + (count % usersPerPage == 0 ? 0 : 1);
		return pages;
	}
}