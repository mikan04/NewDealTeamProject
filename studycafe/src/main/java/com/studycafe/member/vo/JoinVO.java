package com.studycafe.member.vo;

import org.springframework.beans.factory.annotation.Autowired;

import com.studycafe.member.entity.MemberAddressEntity;
import com.studycafe.member.entity.MemberEntity;

import lombok.Data;

@Data
public class JoinVO {
	@Autowired
	private MemberEntity memberEntity;
	@Autowired
	private MemberAddressEntity memberAddressEntity;
}
