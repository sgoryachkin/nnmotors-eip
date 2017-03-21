package ru.nnmotors.eip.dev.auth;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.authentication.AbstractLdapAuthenticationProvider;
import org.springframework.security.ldap.userdetails.LdapUserDetailsImpl;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;

public class DevAuthProvider extends AbstractLdapAuthenticationProvider {
	
	public DevAuthProvider() {
		setUserDetailsContextMapper(new UserDetailsContextMapper() {
			
			@Override
			public void mapUserToContext(UserDetails user, DirContextAdapter ctx) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public UserDetails mapUserFromContext(DirContextOperations ctx, String username,
					Collection<? extends GrantedAuthority> authorities) {
				LdapUserDetailsImpl.Essence essence = new LdapUserDetailsImpl.Essence();
				essence.setDn("eip");
				essence.setUsername(username);
				for (GrantedAuthority grantedAuthority : authorities) {
					essence.addAuthority(grantedAuthority);
				}
				return essence.createUserDetails();

			}
		});
	}

	@Override
	protected DirContextOperations doAuthentication(UsernamePasswordAuthenticationToken auth) {
		return null;
	}

	@Override
	protected Collection<? extends GrantedAuthority> loadUserAuthorities(DirContextOperations userData, String username,
			String password) {
		if ("admin".equals(username)) {
			return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
		} else {
			return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
		}
	}

}
