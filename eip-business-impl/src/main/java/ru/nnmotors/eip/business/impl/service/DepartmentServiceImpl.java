package ru.nnmotors.eip.business.impl.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.nnmotors.eip.business.api.model.entity.Department;
import ru.nnmotors.eip.business.api.service.DepartmentService;

@Service
@Transactional
public class DepartmentServiceImpl extends AbstractRepository<Department, String, String> implements DepartmentService {

	public DepartmentServiceImpl() {
		super(Department.class);
	}

}
