package ru.nnmotors.eip.business.api.service;

import ru.nnmotors.eip.business.api.model.entity.UserProfile;

public interface UserService extends Repository<UserProfile, String, String> {

	UserProfile getUserByLogin(String login);

}
