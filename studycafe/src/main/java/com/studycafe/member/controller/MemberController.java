package com.studycafe.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.studycafe.member.entity.MemberAddressEntity;
import com.studycafe.member.entity.MemberEntity;
import com.studycafe.member.service.MemberService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller

public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@RequestMapping(value ="/loginForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String home(Model model, HttpSession session) {
		
		return "member/loginForm";
	}
	
	@GetMapping("/joinForm")
	public String joinForm() {
		return "member/joinForm";
	}
	
	@PostMapping("/joinPro")
	public String joinPro(MemberEntity memberEn,MemberAddressEntity memAddEn, HttpServletRequest request) {
		
		String rawPassword=memberEn.getPassword();
		String encPassword=encoder.encode(rawPassword);
		memberEn.setPassword(encPassword);
		
		memberService.insertMember(memberEn);
		memberService.insertMemAdd(memAddEn);
		
		System.out.println("================="+memberEn);
		System.out.println("================="+memAddEn);
		log.info(memberEn);
		log.info(memAddEn);
		
		return "redirect:/loginForm";
	}
}
