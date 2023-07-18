package com.studycafe.member.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class MemberAddressEntity {
	
	@Id
	private String id;
	private String zipcode;
	private String address1;
	private String address2;
}
