package ru.nnmotors.eip.business.impl.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.nnmotors.eip.business.api.model.UserRole;
import ru.nnmotors.eip.business.api.model.entity.User;
import ru.nnmotors.eip.business.api.model.entity.User_;
import ru.nnmotors.eip.business.api.model.exception.SecurityCheckException;
import ru.nnmotors.eip.business.api.model.exception.UserAccessCheckException;
import ru.nnmotors.eip.business.api.service.SecurityService;

@Service
@Transactional
public class SecurityServiceImpl implements SecurityService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SecurityServiceImpl.class);

	@PersistenceContext
	private EntityManager em;

	@Override
	public void checkUser(Long userId) {
		String login = getUserByLogin(userId);
		checkUser(login, userId);
	}

	private void checkUser(String login, Long userId) {
		assert (login != null);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication != null && authentication.isAuthenticated() && authentication.getName() != "anonymousUser"
				&& (authentication.getAuthorities().stream()
						.anyMatch(authority -> UserRole.ROLE_ADMIN.name().equals(authority.getAuthority()))
						|| login.equals(authentication.getName()))) {
			return;
		} else {
			LOGGER.debug("No access for user " + login);
			throw new UserAccessCheckException(userId, "No access for user " + login);
		}
	}

	private String getUserByLogin(Long userId) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<String> criteria = criteriaBuilder.createQuery(String.class);
		Root<User> root = criteria.from(User.class);
		Predicate idPredicate = criteriaBuilder.equal(root.get(User_.id), userId);
		return em.createQuery(criteria.select(root.get(User_.login)).where(idPredicate)).getSingleResult();
	}

	@Override
	public boolean isCheckUser(Long userId) {
		try {
			checkUser(userId);
			return true;
		} catch (SecurityCheckException e) {
			return false;
		}
	}

}
