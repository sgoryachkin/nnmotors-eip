package ru.nnmotors.eip.web.ui.pages.user;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ru.nnmotors.eip.business.api.model.entity.UserProfile;
import ru.nnmotors.eip.business.api.model.param.ListParam;
import ru.nnmotors.eip.business.api.service.UserService;
import ru.nnmotors.eip.web.common.util.UserProfileAssambleUtils;

@Controller
@Transactional
@RequestMapping("user")
public class UserListController {

	public static final String USER_LIST_DATA_ATTRIBUTE = "userListData";

	public static final int PAGE_SIZE = 10;

	private static final Logger LOGGER = LoggerFactory.getLogger(UserListController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String userProfileEdit(
			@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(required = false) String filter, Model model) {
		LOGGER.debug("show user profile");
		model.addAttribute(USER_LIST_DATA_ATTRIBUTE, assebleUserListData(page, filter));
		return "user.list";
	}

	UserListData assebleUserListData(int page, String filter) {
		UserListData userListData = new UserListData();
		ListParam<String, String> listParam = new ListParam<>(page, PAGE_SIZE, filter);
		List<UserProfile> userProfiles = userService.getList(listParam);
		List<UserListElementData> users = userProfiles.stream()
				.map(userProfile -> assebleUserListElementData(userProfile)).collect(Collectors.toList());
		userListData.setUsers(users);
		return userListData;

	}

	UserListElementData assebleUserListElementData(UserProfile userProfile) {
		UserListElementData userListElementData = new UserListElementData();
		userListElementData.setId(userProfile.getId());
		userListElementData.setFullName(UserProfileAssambleUtils.fullName(userProfile));
		userListElementData.setAvatarUrl(UserProfileAssambleUtils.avatarUrl(userProfile, true));
		return userListElementData;

	}

}
