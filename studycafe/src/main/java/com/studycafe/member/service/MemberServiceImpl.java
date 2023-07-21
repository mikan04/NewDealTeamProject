package com.studycafe.member.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studycafe.member.entity.MemberAddressEntity;
import com.studycafe.member.entity.MemberEntity;
import com.studycafe.member.entity.Role;
import com.studycafe.member.repository.MemberAddressRepository;
import com.studycafe.member.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberRepository memRe;
	
	@Autowired
	private MemberAddressRepository memberAddRe;



	@Override
	@Transactional
	public void insertMember(MemberEntity member, MemberAddressEntity address) {
		
		member.setRole(Role.ROLE_MEMBER);
		
		memRe.save(member);
		
		address.setMemberEntity(member);
		
		memberAddRe.save(address);
	}

}
