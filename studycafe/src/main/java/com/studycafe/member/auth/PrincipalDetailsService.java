package com.studycafe.member.auth;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.studycafe.member.entity.MemberEntity;
import com.studycafe.member.repository.MemberRepository;

// 시큐리티 설정에서 login 관련 url 접속시 동작함. 
@Service
public class PrincipalDetailsService implements UserDetailsService{
	
	@Autowired
	private MemberRepository memberRepository;
	
	// UserDetailsService IoC되어 있는 아래 함수 실행.
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		MemberEntity member = memberRepository.findByUsername(username).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				
				throw new IllegalArgumentException("해당하는 회원은 존재하지 않는 회원입니다.");
			}
		});
		
		return new PrincipalDetails(member);
	}
	
	
}
