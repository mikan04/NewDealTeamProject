package com.studycafe.member.oauth2.dto;

import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.studycafe.member.entity.MemberEntity;
import com.studycafe.member.entity.Role;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@ToString
public class OAuthAttributes {
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	private Map<String, Object> attributes; // OAuth2 반환하는 유저 정보 Map
	private String nameAttributeKey;

	private String givenName;
	private String username;
	private String email;
	private String name;

	@Builder
	public OAuthAttributes(
			Map<String, Object> attributes,
			String email, String nameAttributeKey,
			String name, String username,
			String givenName) {
		this.attributes = attributes;
		this.nameAttributeKey = nameAttributeKey;
		this.username = username;
		this.name = name;
		this.givenName = givenName;
		this.email = email;
	}

	public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
		// 여기서 네이버와 카카오 등 구분 (ofNaver, ofKakao)

		return ofGoogle(userNameAttributeName, attributes);
	}

	private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
		return OAuthAttributes.builder()
				.name((String) attributes.get("name"))
				.email((String) attributes.get("email"))
				.attributes(attributes)
				.nameAttributeKey(userNameAttributeName)
				.build();
	}

	public MemberEntity toEntity() {

		String randomString = generateRandomString();
		
		/*
		 * String encodedString = encoder.encode(randomString);
		 * 
		 * log.info(encodedString);
		 */

		return MemberEntity.builder()
				.username(email)
				.name(name)
				.nickName(name)
				.password(randomString)
				.email(email)
				.role(Role.ROLE_MEMBER) // 기본 권한 Member
				.build();
	}

	public String generateRandomString() {

		int leftLimit = 48; // numeral '0'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 12;
		Random random = new Random();

		String generatedString = random.ints(leftLimit, rightLimit + 1)
				.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
				.limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
				.toString();

		return generatedString;
	}
}
