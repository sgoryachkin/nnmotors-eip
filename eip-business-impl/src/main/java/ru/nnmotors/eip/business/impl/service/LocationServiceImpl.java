package ru.nnmotors.eip.business.impl.service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import ru.nnmotors.eip.business.api.model.entity.Location;
import ru.nnmotors.eip.business.api.model.entity.Location_;
import ru.nnmotors.eip.business.api.service.LocationService;

@Service
@Transactional
public class LocationServiceImpl extends AbstractRepository<Location, String, String> implements LocationService {

	public LocationServiceImpl() {
		super(Location.class);
	}
	
	@Override
	protected <R> void createListWhereRestrictions(CriteriaQuery<R> criteriaQuery, Root<? extends Location> root,
			String filter) {
		super.createListWhereRestrictions(criteriaQuery, root, filter);
		if (StringUtils.isEmpty(filter)) {
			return;
		}
		CriteriaBuilder cb = em.getCriteriaBuilder();
		Predicate namePredicate = cb.like(cb.lower(root.get(Location_.name)), "%" + filter.toLowerCase() + "%");
		criteriaQuery.where(cb.or(namePredicate));
	}

}
