package com.studycafe.member.dto;

import org.springframework.beans.factory.annotation.Autowired;

import com.studycafe.member.entity.MemberAddressEntity;
import com.studycafe.member.entity.MemberEntity;

import lombok.Data;

@Data
public class JoinDto {
	@Autowired
	private MemberEntity memberEntity;
	@Autowired
	private MemberAddressEntity memberAddressEntity;
}
