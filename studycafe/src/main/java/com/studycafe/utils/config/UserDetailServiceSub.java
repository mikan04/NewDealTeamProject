package com.studycafe.utils.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.studycafe.member.entity.MemberAdaptor;
import com.studycafe.member.entity.MemberEntity;
import com.studycafe.member.repository.MemberRepository;

@Service
public class UserDetailServiceSub implements UserDetailsService {

	@Autowired
	private MemberRepository memRe;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		MemberEntity member = memRe.findById(username).get();
		
		if (member == null) {
			throw new UsernameNotFoundException("찾는 회원이 존재하지 않습니다.");
		}
		
		return new MemberAdaptor(member); 
		
		
	}
	
}
