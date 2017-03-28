package ru.nnmotors.eip.web.common.util;

import org.springframework.util.StringUtils;

import ru.nnmotors.eip.business.api.model.entity.UserProfile;

public final class UserProfileAssambleUtils {

	private UserProfileAssambleUtils() {

	}

	public static String fullName(UserProfile user) {

		StringBuilder sb = new StringBuilder();
		if (StringUtils.hasText(user.getFirstName())) {
			sb.append(user.getFirstName());
			sb.append(' ');
		}
		if (StringUtils.hasText(user.getMiddleName())) {
			sb.append(user.getMiddleName());
			sb.append(' ');
		}
		if (StringUtils.hasText(user.getLastName())) {
			sb.append(user.getLastName());
			sb.append(' ');
		}
		String fullName = sb.toString().trim();
		return StringUtils.hasText(fullName) ? fullName : user.getLogin();

	}

	public static String avatarUrl(UserProfile user, boolean small) {
		if (user.getAvatar() != null) {
			return "/attachment/download/" + user.getAvatar().getId() + "/avatar";
		} else {
			return "/static/img/no-avatar.png";
		}
	}

}
