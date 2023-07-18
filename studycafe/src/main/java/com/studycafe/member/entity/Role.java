package com.studycafe.member.entity;

import lombok.Getter;

@Getter
public enum Role {
	
	ROLE_ADMIN("관리자"),
	ROLE_MENTOR("멘토"),
	ROLE_MEMBER("일반회원");
	
	private String roleDescription;
	
	// ROLE_ 출력을 위해
	Role(String roleDescription) {
		this.roleDescription = roleDescription;
	}
}
