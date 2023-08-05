package com.studycafe.member.oauth2.service;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class Oauth2MemberAdaptor extends DefaultOAuth2UserService {
	
	// 구글은 액세스토큰 + 사용자 프로필정보를 같이 받음.
	
	// 소셜로그인으로 받은 데이터 후처리 메소드.
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		
		// 탐색전
		log.info("userRequest getClientRegistration : {}", userRequest.getClientRegistration());
		log.info("userRequest getAccessToken().getTokenValue() : {}", userRequest.getAccessToken().getTokenValue());
		
		// 구글 로그인 버튼 클릭 -> 구글로그인창 -> 로그인완료 -> code리턴(Oauth2-Client 라이브러리) -> Accesstoken요청
		// userRequest에 회원 프로필 정보가 담김 -> 이때 loadUser메소드로 추출가능.
		log.info("userRequest super.loadUser(userRequest).getAttributes() : {}", super.loadUser(userRequest).getAttributes()); // 이 부분에서 회원가입.
		
		// 저장방식
		// username : google_super.loadUser(userRequest).getAttributes()."sub"
		// password : 암호화(페이지이름)
		// email : email
		// role = ROLE_MEMBER
		// 으로 할 예정 오케이 렛츠꼬
		
		// 회원가입
		OAuth2User oauth2User = super.loadUser(userRequest);
		
		return super.loadUser(userRequest);
	}

	/*
	 * @Autowired private final MemberRepository memberRepository;
	 * 
	 * private final HttpSession httpSession;
	 * 
	 * @Override public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
	 * 
	 * OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
	 * 
	 * // 소셜로그인 한 정보를 담음 OAuth2User oAuth2User = delegate.loadUser(userRequest);
	 * 
	 * // oauth2 서비스 이름 String registrationId = userRequest.getClientRegistration().getRegistrationId();
	 * 
	 * log.info("oauth2 로그인 시작, 로그인 계정 정보 : {}", oAuth2User.getAttributes());
	 * 
	 * // oauth2 로그인 진행시 key가 되는 값 - 해당 서비스의 고유번호 (PK) String userNameAttributeName = userRequest.getClientRegistration() .getProviderDetails()
	 * .getUserInfoEndpoint() .getUserNameAttributeName();
	 * 
	 * // OAuth2UserService OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
	 * 
	 * log.info("attributes : {}", attributes);
	 * 
	 * MemberEntity user = saveOauth2User(attributes); httpSession.setAttribute("user", new SessionUser(user)); // SessionUser (직렬화된 dto 클래스 사용)
	 * 
	 * log.info("유저의 권한 : {}", user.getRole().toString());
	 * 
	 * return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(Role.ROLE_MEMBER.toString())), attributes.getAttributes(),
	 * attributes.getNameAttributeKey()); }
	 * 
	 * // 유저 생성 및 수정 서비스 로직 private MemberEntity saveOauth2User(OAuthAttributes attributes) {
	 * 
	 * MemberEntity user = memberRepository.findByUsername(attributes.getEmail()) .map(entity -> entity.oauth2UserUpdate( attributes.getName(),
	 * attributes.getEmail() )) .orElse(attributes.toEntity());
	 * 
	 * return memberRepository.save(user);
	 * 
	 * }
	 */

}
