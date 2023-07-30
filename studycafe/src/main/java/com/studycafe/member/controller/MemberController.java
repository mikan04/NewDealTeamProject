package com.studycafe.member.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.studycafe.member.dto.JoinDto;
import com.studycafe.member.entity.MemberAdaptor;
import com.studycafe.member.entity.MemberAddressEntity;
import com.studycafe.member.entity.MemberEntity;
import com.studycafe.member.entity.Role;
import com.studycafe.member.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MemberController {

	@Autowired
	private MemberService memberService;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@GetMapping("/loginform")
	public String loginPage() {

		return "/member/loginForm";
	}

	@PostMapping("/loginform")
	public String login() {

		return "redirect:/";
	}

	@GetMapping("/joinform")
	public String joinForm() {
		return "/member/joinForm";
	}

	@GetMapping("/logout")
	public String logout() {
		return "/";
	}

	@PostMapping("/joinPro")
	@ResponseBody
	public boolean joinPro(@RequestBody JoinDto joinVO, HttpServletRequest request) {

		MemberEntity memberEntity = joinVO.getMemberEntity();
		MemberAddressEntity memberAddressEntity = joinVO.getMemberAddressEntity();

		String rawPassword = memberEntity.getPassword();
		String encPassword = encoder.encode(rawPassword);

		memberEntity.setPassword(encPassword);
		memberEntity.setRole(Role.ROLE_MEMBER);
		boolean insert = memberService.insertMember(memberEntity, memberAddressEntity);

		log.info("가입한 회원의 정보 : {}", memberEntity);
		log.info("가입한 회원의 주소 : {}", memberAddressEntity);

		return insert;
	}

	// 아이디 중복체크
	@PostMapping("/idCheck")
	@ResponseBody
	public boolean idCheck(@RequestParam("username") String username) {

		boolean idcheck = memberService.idCheck(username);

		return idcheck;
	}

	// 닉네임 중복체크
	@PostMapping("/nickCheck")
	@ResponseBody
	public boolean nickCheck(@RequestParam("nickName") String nickName) {

		boolean nickCheck = memberService.nickCheck(nickName);

		return nickCheck;
	}

	// 이메일 중복체크
	@PostMapping("/emailCheck")
	@ResponseBody
	public boolean emailCheck(@RequestParam("email") String email) {

		boolean emailCheck = memberService.emailCheck(email);
		
		return emailCheck;
	}

	@GetMapping("/findAccount")
	public String findAccount() {
		return "/member/searchId";
	}

	// 아이디 찾기
	@PostMapping("/getUserId")
	@ResponseBody
	public MemberEntity getUsername(@RequestParam("email") String email) {
		try {
			MemberEntity memberEntity = memberService.getUsername(email);
			if (memberEntity != null) {
				log.info("memberEntity의 값은 :{}", memberEntity);
				return memberEntity;
			} else {
				log.info("memberEntity의 값은 :{}", memberEntity);
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 아이디 찾기
	@GetMapping("/resultUsername")
	public String resultUsername() {

		return "/member/resultUsername";
	}

	// 비밀번호 찾기 1
	@GetMapping("/searchPwd1")
	public String searchPwd1() {
		return "/member/searchPwd1";
	}

	// 비밀번호 찾기1 (아이디 찾기)
	@PostMapping("/searchPwdFindUsername")
	@ResponseBody
	public MemberEntity searchPwdFindUsername(@RequestParam("username") String username) {
		MemberEntity memberEntity = memberService.findUsername(username);

		return memberEntity;
	}

	// 비밀번호 찾기 2
	@GetMapping("/searchPwd2")
	public String searchPwd2() {

		return "/member/searchPwd2";
	}

	// 비밀번호 찾기2 (아이디 찾기)
	@PostMapping("/searchPwdFindUsernameEmail")
	@ResponseBody
	public MemberEntity searchPwdFindUsernameEmail(@RequestParam("username") String username,
			@RequestParam("email") String email) {
		MemberEntity memberEntity = memberService.findUsernameByEmail(username, email);
		log.info("memberEntity 는 : {}", memberEntity);
		return memberEntity;
	}

	@GetMapping("/searchPwd3")
	public String searchPwd3() {
		return "/member/searchPwd3";
	}

	@PostMapping("/updatePassword")
	@ResponseBody
	public boolean updatePassword(@RequestParam("username") String username,
			@RequestParam("password") String password) {

		boolean result = memberService.updatePassword(username, password);

		return result;
	}

	// 회원정보 수정
	@GetMapping("/member/modifyinfo")
	public String modifyUser(@AuthenticationPrincipal MemberAdaptor memberAdaptor , Model model) {
		
		String username = memberAdaptor.getMember().getUsername();
		
		MemberEntity memberInfo = memberAdaptor.getMember();
		
		MemberAddressEntity address = memberService.getUserAddress(username);
		
		model.addAttribute("member", memberInfo);
		
		model.addAttribute("memberAddress", address);
		
		return "/member/modifymember";
	}

}
