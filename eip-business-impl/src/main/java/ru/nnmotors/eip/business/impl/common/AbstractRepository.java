package ru.nnmotors.eip.business.impl.common;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import ru.nnmotors.eip.business.api.model.entity.HasId;
import ru.nnmotors.eip.business.api.model.param.ListParam;
import ru.nnmotors.eip.business.api.service.Repository;

public class AbstractRepository<T extends HasId, F, O> implements Repository<T, F, O> {
	
	@PersistenceContext
	private EntityManager em;
	
	private Class<T> entityClass; 
	
	public AbstractRepository(Class<T> entityClass) {
		this.entityClass = entityClass; 
	}

	@Override
	public Long create(T entity) {
		em.persist(entity);
		return entity.getId();
	}

	@Override
	public T get(Long id) {
		T entity = em.find(entityClass, id);
		if (entity == null) {
			throw new NoResultException(entityClass.getSimpleName() + "  not found: " + id);
		}
		return entity;
	}

	@Override
	public void update(T entity) {
		em.merge(entity);
	}

	@Override
	public List<T> getList(ListParam<F, O> param) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<T> criteria = criteriaBuilder.createQuery(entityClass);

		Root<T> root = criteria.from(entityClass);

		criteria.select(root);
		int firstResult = param.getPageSize() * param.getPage() - param.getPageSize();
		int maxResults = param.getPageSize();
		
		return em.createQuery(criteria).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
	}

}
