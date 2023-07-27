package com.studycafe.member.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.studycafe.member.entity.MemberAddressEntity;
import com.studycafe.member.entity.MemberEntity;

@Service
public interface MemberService {
	
	// 회원가입
	public boolean insertMember(MemberEntity memberEntity, MemberAddressEntity memberAddressEntity);
	// 아이디 중복 체크
	public boolean idCheck(String username);
	// 닉네임 중복 체크
	public boolean nickCheck(String nickName);
	
	public int getNewMemberCount();
	public List<MemberEntity> getAllMember();
}
