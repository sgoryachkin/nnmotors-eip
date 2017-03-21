package ru.nnmotors.eip.business.api.service;

import ru.nnmotors.eip.business.api.model.entity.UserProfile;

public interface UserService {
	
	Long createUser(UserProfile user);
	
	void updateUser(UserProfile user);

	void removeUser(Long id);
	
	UserProfile getUser(Long id);
	
	UserProfile getUserByLogin(String login);

}
