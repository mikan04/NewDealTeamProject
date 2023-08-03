package com.studycafe.member.entity;

import java.io.Serializable;
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

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class MemberEntity implements Serializable {
	
	

	/**
	 * @serial MemberEntity
	 */
	private static final long serialVersionUID = -8751826630478201593L;

	// 시큐리티 필드
	@Id
	@NotNull
	@Column(length = 20)
	private String username;

	@NotNull
	@Column(length = 300)
	private String password;

	@NotNull
	@Enumerated(EnumType.STRING)
	private Role role;

	// 나머지
	@Column(length = 50)
	private String email;

	@NotNull
	@Column(unique = true, length = 15)
	private String nickName;

	@NotNull
	@Column(length = 10)
	private String name;

	@Column(nullable = false, updatable = false)
	@CreationTimestamp
	private Timestamp createdAt;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private Join joinMethod;

	@ManyToOne
	@JoinColumn(name = "teamNumber")
	private TeamEntity teamNumber;

	@Builder
	public MemberEntity(@NotNull String username, @NotNull String password, @NotNull Role role,Join joinMethod ,@NotNull String email, @NotNull String nickName,
			@NotNull String name, Timestamp createdAt, TeamEntity teamNumber) {

		this.username = username;
		this.password = password;
		this.role = role;
		this.joinMethod = joinMethod;
		this.email = email;
		this.nickName = nickName;
		this.name = name;
		this.createdAt = createdAt;
		this.teamNumber = teamNumber;
	}

}
