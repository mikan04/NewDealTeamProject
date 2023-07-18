package com.studycafe.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.studycafe.member.entity.MemberEntity;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class MemberController {
	
	@RequestMapping(value ="/loginForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String home(Model model, HttpSession session) {
		
		return "member/loginForm";
	}
	
	@GetMapping("/joinForm")
	public String joinForm() {
		return "member/joinForm";
	}
	
	@PostMapping("/joinPro")
	public String joinPro(MemberEntity memberEn, HttpServletRequest request) {
		log.info(memberEn);
		return "redirect:member/loginForm";
	}

}
