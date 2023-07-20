package com.studycafe.member.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.studycafe.team.entity.TeamEntity;

import lombok.Data;

@Data
@Entity
public class MemberEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long memberIdx;
	
	// 시큐리티 필드
	@NotNull
	@Column(unique = true, length = 20)
	private String username;

	@NotNull
	@Column(length = 300)
	private String password;

	@Enumerated(EnumType.STRING)
	private Role role;

	// 나머지
	@NotNull
	@Column(length = 50)
	private String email;

	@NotNull
	@Column(unique = true, length=15)
	private String nickName;

	@NotNull
	@Column(length= 10)
	private String name;

	private int emailAuth; // 이메일 인증여부 필드, 0 = false, 1 = true, nullable.
	
	/**
	 * DB는 오브젝트 저장이 불가. FK설정을 위해선 자바 오브젝트를 불러다가 특정 컬럼 저격을 해줘야함. 
	 * JoinColumn / Many = MemberEntity-teamnumber // One = TeamEntity-teamnumber
	 */
	@ManyToOne
	@JoinColumn(name = "teamNumber")
	private TeamEntity teamNumber;

}
