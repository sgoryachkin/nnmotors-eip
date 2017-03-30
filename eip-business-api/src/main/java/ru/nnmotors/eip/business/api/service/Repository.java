package ru.nnmotors.eip.business.api.service;

import java.util.List;

import ru.nnmotors.eip.business.api.model.entity.HasId;
import ru.nnmotors.eip.business.api.model.param.ListParam;

public interface Repository<T extends HasId, F, O> {
	
	Long create(T entity);
	
	T get(Long id);
	
	T getReference(Long id);
	
	void update(T entity);
	
	List<T> getList(ListParam<F, O> param);
	
	int getListCount(F filter);

}
