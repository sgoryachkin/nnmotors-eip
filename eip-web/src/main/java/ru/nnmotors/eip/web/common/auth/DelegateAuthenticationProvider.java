package ru.nnmotors.eip.web.common.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.transaction.annotation.Transactional;

import ru.nnmotors.eip.business.api.model.entity.User;
import ru.nnmotors.eip.business.api.service.UserService;

public class DelegateAuthenticationProvider implements AuthenticationProvider {
	
	private AuthenticationProvider authenticationProvider;
	
	@Autowired
	private UserService userService;

	@Override
	@Transactional
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		Authentication authenticationResult = authenticationProvider.authenticate(authentication);
		if (userService.getUserByLogin(authenticationResult.getName()) == null) {
			User user = new User();
			user.setLogin(authenticationResult.getName());
			userService.createUser(user);
		}
		return authentication;
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
