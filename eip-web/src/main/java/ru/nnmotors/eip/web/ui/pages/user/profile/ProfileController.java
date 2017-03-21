package ru.nnmotors.eip.web.ui.pages.user.profile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.nnmotors.eip.business.api.model.entity.UserProfile;
import ru.nnmotors.eip.business.api.service.UserService;
import ru.nnmotors.eip.web.common.util.AttributeUtils;

@Controller
@Transactional
@RequestMapping("user")
public class ProfileController {
	
	public static final String PROFILE_DATA_ATTRIBUTE = AttributeUtils.createNameForClass(ProfileData.class);
	
	@Autowired
	private UserService userService;

	private static final Logger LOGGER = LoggerFactory.getLogger(ProfileController.class);

	@RequestMapping(value = "{id}/profile", method = RequestMethod.GET)
	public String userProfile(@PathVariable Long id, Model model) {
		LOGGER.debug("show user profile");
		UserProfile user = userService.getUser(id);
		model.addAttribute(PROFILE_DATA_ATTRIBUTE, assemblProfileData(user));
		return "user.profile";
	}
	
	private ProfileData assemblProfileData(UserProfile user) {
		ProfileData profileData = new ProfileData();
		profileData.setFullName(user.getFirstName() + " " + user.getMiddleName() + " " + user.getLastName());
		profileData.setLogin(user.getLogin());
		return profileData;
	}

}

