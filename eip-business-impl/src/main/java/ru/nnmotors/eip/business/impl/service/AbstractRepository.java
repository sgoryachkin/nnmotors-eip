package ru.nnmotors.eip.business.impl.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.transaction.annotation.Transactional;

import ru.nnmotors.eip.business.api.model.entity.HasId;
import ru.nnmotors.eip.business.api.model.param.ListParam;
import ru.nnmotors.eip.business.api.service.Repository;

@Transactional
public abstract class AbstractRepository<T extends HasId, F, O> implements Repository<T, F, O> {
	
	@PersistenceContext
	protected EntityManager em;
	
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
		createListWhereRestrictions(criteria, root, param.getFilter());
		int firstResult = param.getFirstResult();
		int maxResults = param.getMaxResults();
		criteria.select(root);
		
		return em.createQuery(criteria).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
	}
	
	@Override
	public int getListCount(F filter) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = criteriaBuilder.createQuery(Long.class);
		Root<T> root = criteria.from(entityClass);
		createListWhereRestrictions(criteria, root, filter);
		criteria.select(criteriaBuilder.countDistinct(root));
		return em.createQuery(criteria).getSingleResult().intValue();
	}
	
	protected <R> void createListWhereRestrictions(CriteriaQuery<R> criteriaQuery, Root<? extends T> root, F filter) {
		//criteria.where...
		return;
	}
	
	protected <R> void createListOrder(CriteriaQuery<R> criteriaQuery, Root<? extends T> root, O filter) {
		//criteria.orderBy...
		return;
	}

}
