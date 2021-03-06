package ru.nnmotors.eip.web.common.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.transaction.annotation.Transactional;

import ru.nnmotors.eip.business.api.model.entity.UserProfile;
import ru.nnmotors.eip.business.api.service.UserService;

public class DelegateAuthenticationProvider implements AuthenticationProvider {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DelegateAuthenticationProvider.class);
	
	private AuthenticationProvider authenticationProvider;
	
	@Autowired
	private UserService userService;

	@Override
	@Transactional
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		Authentication authenticationResult = authenticationProvider.authenticate(authentication);
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("User authenticated: " + authenticationResult.getName());
			LOGGER.debug("User authority: " + authenticationResult.getAuthorities());
		}
		
		if (userService.getUserByLogin(authenticationResult.getName()) == null) {
			UserProfile user = new UserProfile();
			user.setLogin(authenticationResult.getName());
			userService.create(user);
		}
		return authenticationResult;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authenticationProvider.supports(authentication);
	}

	@Required
	public void setAuthenticationProvider(AuthenticationProvider authenticationProvider) {
		this.authenticationProvider = authenticationProvider;
	}

}
