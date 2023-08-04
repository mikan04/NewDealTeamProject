package com.studycafe.member.oauth2.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.studycafe.member.oauth2.entity.Oauth2UserEntity;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Oauth2LoginService implements UserDetails, OAuth2User {
	
	private Oauth2UserEntity oauth2UserEntity;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		List<GrantedAuthority> roles = new ArrayList<>();
		
		roles.add(new GrantedAuthority() {
			
			@Override
			public String getAuthority() {
				
				return oauth2UserEntity.getRole().toString();
			}
		});
		
		return roles;
	}

	@Override
	public Map<String, Object> getAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return oauth2UserEntity.getName();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return oauth2UserEntity.getOauth2Id();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
}
