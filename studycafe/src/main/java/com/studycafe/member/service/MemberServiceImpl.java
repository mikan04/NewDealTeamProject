package com.studycafe.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studycafe.member.entity.MemberEntity;
import com.studycafe.member.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberRepository memRe;

	@Override
	public void insertMember(MemberEntity member) {
		// TODO Auto-generated method stub
		memRe.save(member);
	}

	@Override
	public MemberEntity loginService(MemberEntity member) {
		// TODO Auto-generated method stub
		return null;
	}

}
