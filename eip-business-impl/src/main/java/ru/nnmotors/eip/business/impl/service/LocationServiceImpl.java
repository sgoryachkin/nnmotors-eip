package ru.nnmotors.eip.business.impl.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.nnmotors.eip.business.api.model.entity.Location;
import ru.nnmotors.eip.business.api.service.LocationService;
import ru.nnmotors.eip.business.impl.common.AbstractRepository;

@Service
@Transactional
public class LocationServiceImpl extends AbstractRepository<Location, String, String> implements LocationService {

	public LocationServiceImpl() {
		super(Location.class);
	}

}
