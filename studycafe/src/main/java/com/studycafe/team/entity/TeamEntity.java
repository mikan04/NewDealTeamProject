package com.studycafe.team.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.studycafe.member.entity.MemberEntity;

import lombok.Data;

@Data
@Entity
public class TeamEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long teamnumber;
	@Column(unique=true)
	private String teamname;
	
}
