package com.studycafe.member.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import com.studycafe.team.entity.TeamEntity;

import lombok.Data;

@Data
@Entity
public class MemberEntity {

	// 시큐리티 필드
	@Id
	@NotNull
	@Column(unique = true, length = 20)
	private String username;

	@NotNull
	@Column(length = 300)
	private String password;
	
	@NotNull
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
	
	private int emailAuth; // 이메일 인증여부 필드, 0 = false, 1 = true, nullable, default = 0.
	
	@Column(nullable = false, updatable = false)
	@CreationTimestamp
	private Timestamp createdAt;
	
	/**
	 * DB는 오브젝트 저장이 불가. FK설정을 위해선 ORM으로 자바 오브젝트를 불러다가 특정 컬럼 저격을 해줘야함. 
	 * JoinColumn / Many = MemberEntity-teamnumber // One = TeamEntity-teamnumber
	 */
	@ManyToOne
	@JoinColumn(name = "teamNumber")
	private TeamEntity teamNumber;

}
