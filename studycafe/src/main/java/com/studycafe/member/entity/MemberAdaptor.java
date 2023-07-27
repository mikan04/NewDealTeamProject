package com.studycafe.member.entity;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;

@Getter
public class MemberAdaptor extends User{
	
	private static final long serialVersionUID = 1L;
	
	private MemberEntity member;
	
	public MemberAdaptor(MemberEntity member) {
		
		super(member.getUsername(), member.getPassword(),AuthorityUtils.createAuthorityList(member.getRole().toString()));
		
		this.member = member;
	}

}
