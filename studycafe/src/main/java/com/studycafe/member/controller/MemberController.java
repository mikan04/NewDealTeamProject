package com.studycafe.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MemberController {
	
	@RequestMapping(value ="/loginForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String home(Model model, HttpSession session) {
		
		return "member/loginForm";
	}
	
	@GetMapping("/joinForm")
	public String joinForm1() {
		return "member/joinForm";
	}

}
