package com.studycafe.member.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.studycafe.member.entity.MemberAddressEntity;
import com.studycafe.member.entity.MemberEntity;

@Service
public interface MemberService {

	public int insertMember(MemberEntity memberEntity, MemberAddressEntity memberAddressEntity);

	public boolean idCheck(String username);

	public boolean nickCheck(String nickName);
	
	public int getNewMemberCount();
	public List<MemberEntity> getAllMember();
}
