package com.studycafe.member.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.studycafe.member.entity.MemberAddressEntity;
import com.studycafe.member.entity.MemberEntity;
import com.studycafe.member.entity.Role;
import com.studycafe.member.service.MemberService;
import com.studycafe.member.vo.JoinVO;

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
	public boolean joinPro(@RequestBody JoinVO joinVO, HttpServletRequest request) {

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

	@PostMapping("/idCheck")
	@ResponseBody
	public boolean idCheck(@RequestParam("username") String username) {

		boolean idcheck = memberService.idCheck(username);

		return idcheck;
	}

	@PostMapping("/nickCheck")
	@ResponseBody
	public boolean nickCheck(@RequestParam("nickName") String nickName) {

		boolean nickCheck = memberService.nickCheck(nickName);

		return nickCheck;
	}

	@GetMapping("/searchId")
	public String searchId() {
		return "/member/searchId";
	}
}
