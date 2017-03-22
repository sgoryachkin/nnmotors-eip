package ru.nnmotors.eip.web.ui.pages.user.profile;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.nnmotors.eip.business.api.model.entity.UserProfile;
import ru.nnmotors.eip.business.api.service.UserService;

@Controller
@Transactional
@RequestMapping("user")
public class ProfileController {
	
	public static final String PROFILE_DATA_ATTRIBUTE = "profile";
	public static final String PROFILE_FORM_ATTRIBUTE = "profileEditForm";
	
	@Autowired
	private UserService userService;

	private static final Logger LOGGER = LoggerFactory.getLogger(ProfileController.class);

	@RequestMapping(value = "{id}/profile", method = RequestMethod.GET)
	public String userProfileView(@PathVariable Long id, Model model) {
		LOGGER.debug("show user profile");
		UserProfile user = userService.getUser(id);
		model.addAttribute(PROFILE_DATA_ATTRIBUTE, assemblProfileData(user));
		return "user.profile-view";
	}
	
	@RequestMapping(value = "{id}/profile-edit", method = RequestMethod.GET)
	public String userProfileEdit(@PathVariable Long id, Model model) {
		LOGGER.debug("show user profile");
		UserProfile user = userService.getUser(id);
		model.addAttribute(PROFILE_DATA_ATTRIBUTE, assemblProfileData(user));
		model.addAttribute(PROFILE_FORM_ATTRIBUTE, assemblProfileEditForm(user));
		return "user.profile-edit";
	}
	
	@RequestMapping(value = "{id}/profile-edit", method = RequestMethod.POST)
	public String userProfileSave(@Valid ProfileEditForm profileEditForm, @PathVariable Long id, BindingResult result) {
		LOGGER.debug("show user profile");
		if (!result.hasErrors()) {
			updateUser(id, profileEditForm);
			return "redirect:/user/" + id + "/profile";
		} else {
			return "user.profile-edit";
		}
	}
	
	private ProfileEditForm assemblProfileEditForm(UserProfile user) {
		ProfileEditForm profileEditForm = new ProfileEditForm();
		profileEditForm.setFirstName(user.getFirstName());
		profileEditForm.setMiddleName(user.getMiddleName());
		profileEditForm.setLastName(user.getLastName());
		return profileEditForm;
	}
	
	private void updateUser(Long id, ProfileEditForm userForm) {
		UserProfile user = userService.getUser(id);
		user.setFirstName(userForm.getFirstName());
		user.setLastName(userForm.getLastName());
		user.setMiddleName(userForm.getMiddleName());
		userService.updateUser(user);
	}
	
	private ProfileViewData assemblProfileData(UserProfile user) {
		ProfileViewData profileData = new ProfileViewData();
		profileData.setFullName(user.getFirstName() + " " + user.getMiddleName() + " " + user.getLastName());
		profileData.setLogin(user.getLogin());
		profileData.setId(user.getId());
		return profileData;
	}

}

