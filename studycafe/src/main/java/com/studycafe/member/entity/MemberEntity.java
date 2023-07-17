package com.studycafe.member.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class MemberEntity {
	
	@Id // primary key
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	private String email;
	private String name;
	private String pw;
	private String pwCheck;
	private String phone;
	


}
