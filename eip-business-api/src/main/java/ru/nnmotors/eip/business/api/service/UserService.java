package ru.nnmotors.eip.business.api.service;

import ru.nnmotors.eip.business.model.entity.User;

public interface UserService {
	
	Long createUser(User user);
	
	void updateUser(User user);

	void removeUser(Long id);
	
	User getUser(Long id);
	
	User getUserByLogin(String login);

}
