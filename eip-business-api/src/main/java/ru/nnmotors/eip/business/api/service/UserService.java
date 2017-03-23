package ru.nnmotors.eip.business.api.service;

import java.util.List;

import ru.nnmotors.eip.business.api.model.entity.UserProfile;
import ru.nnmotors.eip.business.api.model.param.ListParam;

public interface UserService {
	
	Long createUser(UserProfile user);
	
	void updateUser(UserProfile user);

	void removeUser(Long id);
	
	UserProfile getUser(Long id);
	
	List<UserProfile> getUserList(ListParam<?> param);
	
	UserProfile getUserByLogin(String login);

}
