package com.studycafe.member.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.studycafe.member.entity.MemberAddressEntity;
import com.studycafe.member.entity.MemberEntity;
import com.studycafe.member.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class KakaoController {

	@Autowired
	private MemberService memberService;

	@Autowired
	private BCryptPasswordEncoder encoder;

	
	
	
	
	@RequestMapping(value="/kakaoLoginCallback1")
    public String indexkakao() {
        
        return "/member/kakaotest";
    }
    
//    @RequestMapping(value="/kakao")
//    public String loginkakao() {
//        
//        return "/member/kakaotest";
//    }
    @RequestMapping(value="/kakaoLoginCallback")
    public String loginkakao(@RequestParam("code") String code) {
        System.out.println("code : " + code);
        return "index";
    }

}
