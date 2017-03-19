package ru.nnmotors.eip.dev.auth;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

public class AuthProvider implements AuthenticationProvider {
	
	protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();
	private GrantedAuthoritiesMapper authoritiesMapper = new NullAuthoritiesMapper();

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		Assert.isInstanceOf(UsernamePasswordAuthenticationToken.class, authentication,
				this.messages.getMessage("LdapAuthenticationProvider.onlySupports",
						"Only UsernamePasswordAuthenticationToken is supported"));
		
		String password = (String) authentication.getCredentials();
		
		UserDetails user = getUserDetais((UsernamePasswordAuthenticationToken) authentication);
		
		UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(
				user, password,
				this.authoritiesMapper.mapAuthorities(user.getAuthorities()));
		result.setDetails(authentication.getDetails());

		return result;

	}
	
	protected UserDetails getUserDetais(UsernamePasswordAuthenticationToken authentication) {
		UserDetails userDetails = new UserDetails() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 6154605164550060091L;

			@Override
			public boolean isEnabled() {
				return true;
			}
			
			@Override
			public boolean isCredentialsNonExpired() {
				return true;
			}
			
			@Override
			public boolean isAccountNonLocked() {
				return true;
			}
			
			@Override
			public boolean isAccountNonExpired() {
				return true;
			}
			
			@Override
			public String getUsername() {
				return authentication.getName();
			}
			
			@Override
			public String getPassword() {
				return (String) authentication.getCredentials();
			}
			
			@Override
			public Collection<? extends GrantedAuthority> getAuthorities() {
				if ("admin".equals(authentication.getName())) {
					return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
				} else {
					return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
				}
			}
		};
	
		return userDetails;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
