package com.studycafe.member.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.studycafe.member.dto.MemberDto;
import com.studycafe.member.dto.MemberSafeDto;
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

	// 이메일 중복 체크
	public boolean emailCheck(String email);

	public int getNewMemberCount();

	public List<MemberSafeDto> getAllMember();

	// 아이디 찾기
	public MemberEntity getUsername(String email);

	// 비밀번호 찾기 (아이디 찾기)
	public MemberEntity findUsername(String username);

	// 비밀번호 찾기 (아이디 찾기)
	public MemberEntity findUsernameByEmail(String username, String email);

	// 비밀번호 재설정
	public boolean updatePassword(String username, String password);
	
	// 회원정보 수정
	public MemberAddressEntity getUserAddress(String username);

	public boolean updateInfo(MemberDto memberDto);

}
