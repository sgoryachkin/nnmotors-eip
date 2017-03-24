package ru.nnmotors.eip.web.ui.pages;

import org.apache.tiles.Attribute;
import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import ru.nnmotors.eip.business.api.model.UserRole;
import ru.nnmotors.eip.business.api.model.entity.UserProfile;
import ru.nnmotors.eip.business.api.service.UserService;
import ru.nnmotors.eip.web.common.util.AttributeUtils;
import ru.nnmotors.eip.web.common.util.UserProfileAssambleUtils;

@Component
public class HeaderViewPreparer implements ViewPreparer {

	public static final String HEADER_DATA_ATTRIBUTE = AttributeUtils.createNameForClass(HeaderData.class);

	@Autowired
	private UserService userService;

	@Override
	public void execute(Request request, AttributeContext attributeContext) {
		HeaderData headerData = new HeaderData();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.isAuthenticated() && authentication.getName() != "anonymousUser") {
			UserProfile user = userService.getUserByLogin(authentication.getName());
			headerData.setCurrentUserId(user.getId().toString());
			headerData.setCurrentUserName(userName(user, authentication));
		}
		attributeContext.putAttribute(HEADER_DATA_ATTRIBUTE, new Attribute(headerData));
	}

	private String userName(UserProfile user, Authentication authentication) {
		return UserProfileAssambleUtils.fullName(user) + (authentication.getAuthorities().stream()
				.anyMatch(grant -> UserRole.ROLE_ADMIN.name().equals(grant.getAuthority())) ? "(Администратор)" : "");
	}

}
