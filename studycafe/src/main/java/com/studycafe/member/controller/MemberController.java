package com.studycafe.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.studycafe.member.entity.MemberAddressEntity;
import com.studycafe.member.entity.MemberEntity;
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
	public String loginPage(Model model, HttpSession session) {

		return "/member/loginForm";
	}

	@GetMapping("/joinform")
	public String joinForm() {
		return "/member/joinForm";
	}

	@PostMapping("/joinPro")
	public String joinPro(MemberEntity memberEn, MemberAddressEntity memAddEn, HttpServletRequest request) {

		String rawPassword = memberEn.getPassword();
		String encPassword = encoder.encode(rawPassword);
		memberEn.setPassword(encPassword);

		memberService.insertMember(memberEn, memAddEn);

		log.info("가입한 회원의 정보 : {}",memberEn);
		log.info("가입한 회원의 주소 : {}",memAddEn);

		return "redirect:/member/loginform";
	}

}
