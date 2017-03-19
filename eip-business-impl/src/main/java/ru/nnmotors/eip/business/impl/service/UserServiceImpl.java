package ru.nnmotors.eip.business.impl.service;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.nnmotors.eip.business.api.service.UserService;
import ru.nnmotors.eip.business.model.entity.User;
import ru.nnmotors.eip.business.model.entity.User_;

public class UserServiceImpl implements UserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@PersistenceContext
	private EntityManager em;

	@Override
	public Long createUser(User user) {
		user.setCreateTime(new Date());
		em.persist(user);
		LOGGER.debug("User created: " + user.getId());
		return user.getId();
	}

	@Override
	public void updateUser(User user) {
		em.merge(user);
		LOGGER.debug("User edit: " + user.getId());

	}

	@Override
	public void removeUser(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public User getUser(Long id) {
		User user = em.find(User.class, id);
		if (user == null) {
			throw new IllegalStateException("User not found: " + id);
		}
		return user;
	}

	@Override
	public User getUserByLogin(String login) {
		LOGGER.debug("getUserByLogin: " + login);
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<User> criteria = criteriaBuilder.createQuery(User.class);

		Root<User> root = criteria.from(User.class);
		Predicate loginPredicate = criteriaBuilder.equal(root.get(User_.login), login);

		return em.createQuery(criteria.where(loginPredicate)).getSingleResult();
	}

}
