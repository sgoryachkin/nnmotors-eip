package ru.nnmotors.eip.web.ui.pages.user.profile;

import java.io.IOException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import ru.nnmotors.eip.business.api.model.entity.Attachment;
import ru.nnmotors.eip.business.api.model.entity.Location;
import ru.nnmotors.eip.business.api.model.entity.UserProfile;
import ru.nnmotors.eip.business.api.service.AttachmentService;
import ru.nnmotors.eip.business.api.service.UserService;
import ru.nnmotors.eip.web.common.util.UserProfileAssambleUtils;


@Controller
@Transactional
@RequestMapping("user")
public class ProfileController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProfileController.class);
	
	public static final String PROFILE_DATA_ATTRIBUTE = "profile";
	public static final String PROFILE_FORM_ATTRIBUTE = "profileEditForm";
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AttachmentService attachmentStorageService;

	

	@RequestMapping(value = "{id}/profile", method = RequestMethod.GET)
	public String userProfileView(@PathVariable Long id, Model model) {
		LOGGER.debug("show user profile");
		UserProfile user = userService.get(id);
		model.addAttribute(PROFILE_DATA_ATTRIBUTE, assemblProfileData(user));
		return "user.profile-view";
	}
	
	@RequestMapping(value = "{id}/profile-edit", method = RequestMethod.GET)
	public String userProfileEdit(@PathVariable Long id, Model model) {
		LOGGER.debug("show user profile");
		UserProfile user = userService.get(id);
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
		profileEditForm.setLocation(user.getLocation() == null ? null : user.getLocation().getId());
		return profileEditForm;
	}
	
	private void updateUser(Long id, ProfileEditForm userForm) {
		UserProfile user = userService.get(id);
		if (!StringUtils.isEmpty(userForm.getAvatar().getOriginalFilename())) {
			 user.setAvatar(Attachment.getReference(createUserAvatar(id, userForm.getAvatar())));
		}
		user.setFirstName(userForm.getFirstName());
		user.setLastName(userForm.getLastName());
		user.setMiddleName(userForm.getMiddleName());
		user.setLocation(Location.getReference(userForm.getLocation()));
		userService.update(user);
	}
	
	private Long createUserAvatar(Long id, MultipartFile multipartFile) {
		LOGGER.debug("Uploaded file: " + multipartFile.getOriginalFilename());
		try {
			Long attachmentId = attachmentStorageService.uploadWebImageAttachment(multipartFile.getInputStream(), multipartFile.getOriginalFilename(), multipartFile.getContentType());
			return attachmentId;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	private ProfileViewData assemblProfileData(UserProfile user) {
		ProfileViewData profileData = new ProfileViewData();
		profileData.setFullName(UserProfileAssambleUtils.fullName(user));
		profileData.setLogin(user.getLogin());
		profileData.setId(user.getId());
		profileData.setAvatarUrl(UserProfileAssambleUtils.avatarUrl(user, false));
		profileData.setEmail(user.getEmail());
		profileData.setWorkPhone(user.getWorkPhone());
		return profileData;
	}

}

