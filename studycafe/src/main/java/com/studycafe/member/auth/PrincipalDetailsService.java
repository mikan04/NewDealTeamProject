package com.studycafe.member.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.studycafe.member.entity.MemberEntity;
import com.studycafe.member.repository.MemberRepository;

@Service
public class PrincipalDetailsService implements UserDetailsService{
	
	@Autowired
	private MemberRepository memberRepository;
	
	// UserDetailsService IoC되어 있는 아래 함수 실행.
	// 해당 함수 종료시 @AuthenticationPrincipal 어노테이션이 만들어진다. (중요)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		MemberEntity member = memberRepository.findByUsername(username);
		
		if(member == null) {
			throw new UsernameNotFoundException("찾는 회원이 존재하지 않습니다.");
		}
		
		return new PrincipalDetails(member);
	}
	
	
}
