package com.studycafe.member.oauth2.service;

import java.util.Collections;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.studycafe.member.entity.MemberEntity;
import com.studycafe.member.entity.Role;
import com.studycafe.member.oauth2.dto.OAuthAttributes;
import com.studycafe.member.oauth2.dto.SessionUser;
import com.studycafe.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOauth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

	@Autowired
	private final MemberRepository memberRepository;

	private final HttpSession httpSession;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

		OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
		
		// 소셜로그인 한 정보를 담음
		OAuth2User oAuth2User = delegate.loadUser(userRequest);

		// oauth2 서비스 이름
		String registrationId = userRequest.getClientRegistration().getRegistrationId();

		log.info("oauth2 로그인 시작, 로그인 계정 정보 : {}", oAuth2User.getAttributes());

		// oauth2 로그인 진행시 key가 되는 값 - 해당 서비스의 고유번호 (PK)
		String userNameAttributeName = userRequest.getClientRegistration()
				.getProviderDetails()
				.getUserInfoEndpoint()
				.getUserNameAttributeName();

		// OAuth2UserService
		OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

		log.info("attributes : {}", attributes);

		MemberEntity user = saveOauth2User(attributes);
		httpSession.setAttribute("user", new SessionUser(user)); // SessionUser (직렬화된 dto 클래스 사용)

		log.info("유저의 권한 : {}", user.getRole().toString());

		return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(Role.ROLE_MEMBER.toString())),
				attributes.getAttributes(),
				attributes.getNameAttributeKey());
	}

	// 유저 생성 및 수정 서비스 로직
	private MemberEntity saveOauth2User(OAuthAttributes attributes) {

		MemberEntity user = memberRepository.findByUsername(attributes.getEmail())
				.map(entity -> entity.oauth2UserUpdate(
						attributes.getName(),
						attributes.getEmail()
						))
				.orElse(attributes.toEntity());

		return memberRepository.save(user);

	}

}
