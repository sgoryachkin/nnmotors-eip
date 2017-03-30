package ru.nnmotors.eip.integration.impl;

import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ru.nnmotors.eip.business.api.model.entity.Department;
import ru.nnmotors.eip.business.api.model.entity.Location;
import ru.nnmotors.eip.business.api.model.entity.UserProfile;
import ru.nnmotors.eip.business.api.service.DepartmentService;
import ru.nnmotors.eip.business.api.service.LocationService;
import ru.nnmotors.eip.business.api.service.UserService;

@Component
public class DemoDataInitializer {

	Random rnd = new Random(5);

	static final String[] LAST_NAMES = { "Иванов", "Петров", "Сидоров", "Бобров", "Цой", "Багданович" };
	static final String[] FIRST_NAMES = { "Иван", "Глеб", "Марк", "Александ", "Бандит", "Чубака", "Маша", "Михаил" };
	static final String[] MIDDLE_NAMES = { "Иванович", "", "Михайлович", "Сергеевич", "Аксандрович", "Джекович"};
	
	static final String[] DEPARTMENTS_NAMES = { "Сервис", "Салон", "АйТи", "Бухгалтерия" };
	
	static final String[] LOCATION_NAMES = { "Мицубиши на Ларина", "Мицубиши на Московском", "Тойота на Гагарина"};

	@Autowired
	private UserService userService;
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private LocationService locationService;

	@PostConstruct
	void createDemoData() {
		createDemoUsers();
		createDemoDepartments();
		createDemoLocations();
	}

	private void createDemoUsers() {
		for (int i = 0; i < 35; i++) {
			UserProfile up = new UserProfile();
			up.setLogin("demo" + i);
			up.setLastName(LAST_NAMES[rnd.nextInt(LAST_NAMES.length - 1)]);
			up.setFirstName(FIRST_NAMES[rnd.nextInt(FIRST_NAMES.length - 1)]);
			up.setMiddleName(MIDDLE_NAMES[rnd.nextInt(MIDDLE_NAMES.length - 1)]);
			up.setWorkPhone("+791008450" + i);
			up.setEmail("demo" + i + "@nnmotors.ru");
			userService.create(up);
		}
	}
	
	private void createDemoDepartments() {
		for (int i = 0; i < 11; i++) {
			Department d = new Department();
			d.setName(DEPARTMENTS_NAMES[rnd.nextInt(DEPARTMENTS_NAMES.length - 1)] + rnd.nextInt(10));
			departmentService.create(d);
		}
	}
	
	private void createDemoLocations() {
		for (int i = 0; i < 25; i++) {
			Location l = new Location();
			l.setName(LOCATION_NAMES[rnd.nextInt(LOCATION_NAMES.length - 1)] + rnd.nextInt(10));
			locationService.create(l);
		}
	}

}