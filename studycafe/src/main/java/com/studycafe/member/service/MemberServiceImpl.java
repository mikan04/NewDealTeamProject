package com.studycafe.member.service;

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
	public void insertMember(MemberEntity member) {
		// TODO Auto-generated method stub
		
		member.setRole(Role.ROLE_MEMBER);
		
		memRe.save(member);
	}


	@Override
	public void insertMemAdd(MemberAddressEntity address) {
		// TODO Auto-generated method stub
		memberAddRe.save(address);
		
	}


	@Override
	public MemberEntity loginService(MemberEntity member) {
		// TODO Auto-generated method stub
		return null;
	}

}
