package ru.nnmotors.eip.web.ui.pages.user;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ru.nnmotors.eip.business.api.model.entity.UserProfile;
import ru.nnmotors.eip.business.api.model.param.ListParam;
import ru.nnmotors.eip.business.api.service.UserService;
import ru.nnmotors.eip.web.common.util.UserProfileAssambleUtils;
import ru.nnmotors.eip.web.ui.component.paging.PagindUtils;

@Controller
@Transactional
@RequestMapping("user")
public class UserListController {

	public static final String USER_FILTER_FORM_DATA_ATTRIBUTE = "userFilterFormData";
	public static final String USER_FILTER_FORM_ATTRIBUTE = "userFilterForm";
	public static final String USER_LIST_DATA_ATTRIBUTE = "userListData";
	public static final String USER_PAGING_DATA_ATTRIBUTE = "userPagingData";

	public static final int PAGE_SIZE = 10;

	private static final Logger LOGGER = LoggerFactory.getLogger(UserListController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String userProfileEdit(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(required = false) String filter, Model model) {
		LOGGER.debug("show user list");
		model.addAttribute(USER_LIST_DATA_ATTRIBUTE, assebleUserListData(page, filter));
		model.addAttribute(USER_FILTER_FORM_ATTRIBUTE, new SimpleTextFilter(filter));
		return "user.list";
	}
	
	@RequestMapping(value = "list", method = RequestMethod.POST)
	public String submitFilter(@RequestParam(defaultValue = "1") Integer page, @Valid SimpleTextFilter userFilterForm, RedirectAttributes redirectAttributes) {
		LOGGER.debug("filter: " + userFilterForm.getText());
		redirectAttributes.addAttribute("filter", userFilterForm.getText());
		redirectAttributes.addAttribute("page", page);
		return "redirect:list";
	}

	UserListData assebleUserListData(int page, String filter) {
		UserListData userListData = new UserListData();
		ListParam<String, String> listParam = new ListParam<>(PagindUtils.startIndex(page, PAGE_SIZE), PAGE_SIZE, filter);
		List<UserProfile> userProfiles = userService.getList(listParam);
		List<UserListElementData> users = userProfiles.stream()
				.map(userProfile -> assebleUserListElementData(userProfile)).collect(Collectors.toList());
		userListData.setItems(users);

		int count = userService.getListCount(filter);

		userListData.setPages(PagindUtils.pagingData(page, PAGE_SIZE, count));
		userListData.setCount(count);

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
