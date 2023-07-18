package com.studycafe.member.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
public class MemberEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int memberIdx;
	
	@NotNull
	private String email;
	
	@NotNull
	private String nickname;
	
	@NotNull
	private String name;
	
	private int emailauth; // 이메일 인증여부 필드, 0 = false, 1 = true, nullable.
	private int teamnumber;
	
	// 시큐리티 필드
	@NotNull
	private String username;
	
	@NotNull
	private String password;
	
	@Enumerated(EnumType.STRING)
	private Role role;

}
