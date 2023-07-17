package com.studycafe.member.service;

import com.studycafe.member.entity.MemberEntity;

public interface MemberService {
	
	public void insertMember(MemberEntity member);
	
	public MemberEntity loginService(MemberEntity member);
	
	

}
