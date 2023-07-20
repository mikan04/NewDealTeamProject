package com.studycafe.member.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
public class MemberAddressEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int address_idx;
	
	@NotNull
	private String zipcode; // 우편번호
	
	@NotNull
	private String address1; // 상세주소1, 시, 도 등등
	
	private String address2; // 상세주소2, 나머지 주소. null 가능
}
