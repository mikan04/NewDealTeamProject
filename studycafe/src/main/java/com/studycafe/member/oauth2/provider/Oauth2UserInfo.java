package com.studycafe.member.oauth2.provider;

public interface Oauth2UserInfo {
	
	String getOAuth2Id();
	String getOAuth2Path();
	String getEmail();
	String getName();
}
