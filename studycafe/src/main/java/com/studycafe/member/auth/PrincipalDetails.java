package com.studycafe.member.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.studycafe.member.entity.MemberEntity;

import lombok.Getter;

// 로그인이 완료되면 시큐리티 session 만들어줌. (Security ContextHolder)
// 오브젝트 => Authentication 타입의 객체만 들어갈 수 있음.
// Authentication 안에 user정보가 있어야함.
// User오브젝트 타입 -> UserDetails 타입 객체 (고정)

// 시큐리티 session에는 Authentication 객체 고정으로 들어감 => UserDetails타입으로 꺼내서 씀

@Getter
public class PrincipalDetails implements UserDetails{

	/**
	 *  @serial PrincipalDetails
	 */
	private static final long serialVersionUID = -6487352197708348338L;
	
	private MemberEntity memberEntity; // 컴포지션
	
	public PrincipalDetails(MemberEntity memberEntity) {
		this.memberEntity = memberEntity;
	}
	
	// user 권한 리턴.
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Collection<GrantedAuthority> userRoles = new ArrayList<>();
		userRoles.add(new GrantedAuthority() {
			/**
			 *  @serial userRoles.add(new GrantedAuthority() { ... }
			 */
			private static final long serialVersionUID = 1956719127234299808L;

			@Override
			public String getAuthority() {
				return memberEntity.getRole().toString();
			}
		});
		
		return userRoles;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return memberEntity.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return memberEntity.getUsername();
	}
	
	// 계정 만료
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	
	// 계정 막힘
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	// 계정 비밀번호 기간만료
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	
	// 계정 비활성화여부
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		// 나중에 logindate 컬럼같은거 추가해서 만료설정 가능
		return true;
	}
	
	// 닉네임 체크
	public String getNickName() {
		return memberEntity.getNickName();
	}
	
	
}
