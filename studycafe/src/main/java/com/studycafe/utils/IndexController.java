package com.studycafe.utils;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.studycafe.member.entity.MemberAdaptor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class IndexController {
	
	@GetMapping("/")
	public String 접속컨트롤러(@AuthenticationPrincipal MemberAdaptor memberAdaptor) {
		log.info("메인페이지 접속");
		
		if (memberAdaptor != null) {
			log.info(memberAdaptor.getUsername());
			log.info(memberAdaptor.getPassword());
			log.info("현재 유저의 권한 : {}" , memberAdaptor.getAuthorities());
			log.info("memberAdaptor.getMember() : {}" , memberAdaptor.getMember());
			
		}
		
		return "index";
	}
}
