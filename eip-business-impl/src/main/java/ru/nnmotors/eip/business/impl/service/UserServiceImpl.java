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
import org.springframework.util.StringUtils;

import ru.nnmotors.eip.business.api.model.entity.UserProfile;
import ru.nnmotors.eip.business.api.model.entity.UserProfile_;
import ru.nnmotors.eip.business.api.service.SecurityService;
import ru.nnmotors.eip.business.api.service.UserService;
import ru.nnmotors.eip.business.impl.common.AbstractRepository;

@Service
@Transactional
public class UserServiceImpl extends AbstractRepository<UserProfile, String, String> implements UserService {

	public UserServiceImpl() {
		super(UserProfile.class);
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private SecurityService securityService;

	@Override
	public Long create(UserProfile user) {
		user.setCreateTime(new Date());
		return super.create(user);
	}

	@Override
	public void update(UserProfile entity) {
		securityService.checkUser(entity.getId());
		super.update(entity);
	}

	@Override
	protected <R> void createListWhereRestrictions(CriteriaQuery<R> criteriaQuery, Root<? extends UserProfile> root,
			String filter) {
		super.createListWhereRestrictions(criteriaQuery, root, filter);
		if (StringUtils.isEmpty(filter)) {
			return;
		}
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		Predicate loginPredicate = criteriaBuilder.like(root.get(UserProfile_.login), "%" + filter + "%");
		Predicate firstNamePredicate = criteriaBuilder.like(root.get(UserProfile_.firstName), "%" + filter + "%");
		Predicate lastNamePredicate = criteriaBuilder.like(root.get(UserProfile_.lastName), "%" + filter + "%");
		Predicate middleNamePredicate = criteriaBuilder.like(root.get(UserProfile_.middleName), "%" + filter + "%");
		criteriaQuery.where(loginPredicate, firstNamePredicate, lastNamePredicate, middleNamePredicate);
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
