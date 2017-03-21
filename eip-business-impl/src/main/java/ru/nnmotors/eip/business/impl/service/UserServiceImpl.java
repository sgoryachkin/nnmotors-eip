package ru.nnmotors.eip.business.impl.service;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.nnmotors.eip.business.api.model.entity.UserProfile;
import ru.nnmotors.eip.business.api.service.SecurityService;
import ru.nnmotors.eip.business.api.service.UserService;
import ru.nnmotors.eip.business.api.model.entity.UserProfile_;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private SecurityService securityService;

	@Override
	public Long createUser(UserProfile user) {
		user.setCreateTime(new Date());
		em.persist(user);
		LOGGER.debug("User created: " + user.getId());
		return user.getId();
	}
	
	@Override
	public void removeUser(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateUser(UserProfile user) {
		securityService.checkUser(user.getId());
		em.merge(user);
		LOGGER.debug("User edit: " + user.getId());

	}

	@Override
	public UserProfile getUser(Long id) {
		UserProfile user = em.find(UserProfile.class, id);
		if (user == null) {
			throw new IllegalStateException("User not found: " + id);
		}
		return user;
	}

	@Override
	public UserProfile getUserByLogin(String login) {
		LOGGER.debug("getUserByLogin: " + login);
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<UserProfile> criteria = criteriaBuilder.createQuery(UserProfile.class);

		Root<UserProfile> root = criteria.from(UserProfile.class);
		Predicate loginPredicate = criteriaBuilder.equal(root.get(UserProfile_.login), login);

		try {
			return em.createQuery(criteria.where(loginPredicate)).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
