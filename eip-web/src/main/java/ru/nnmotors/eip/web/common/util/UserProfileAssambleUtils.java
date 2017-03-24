package ru.nnmotors.eip.web.common.util;

import org.springframework.util.StringUtils;

import ru.nnmotors.eip.business.api.model.entity.UserProfile;

public final class UserProfileAssambleUtils {

	private UserProfileAssambleUtils() {

	}

	public static String fullName(UserProfile user) {
		if (StringUtils.isEmpty(user.getFirstName()) && StringUtils.isEmpty(user.getMiddleName())
				&& StringUtils.isEmpty(user.getLastName())) {
			return user.getLogin();
		} else {
			StringBuilder sb = new StringBuilder();
			if (!StringUtils.isEmpty(user.getFirstName())) {
				sb.append(user.getFirstName());
				sb.append(' ');
			} 
			if (!StringUtils.isEmpty(user.getMiddleName())) {
				sb.append(user.getFirstName());
				sb.append(' ');
			}
			if (!StringUtils.isEmpty(user.getLastName())) {
				sb.append(user.getLastName());
				sb.append(' ');
			}
			return sb.toString().trim();
		}
	}
	
	public static String avatarUrl(UserProfile user, boolean small) {
		if (user.getAvatar() != null) {
			return user.getAvatar().getId() + "/avatar.png";
		} else {
			return "/noavatar";
		}
	}

}
