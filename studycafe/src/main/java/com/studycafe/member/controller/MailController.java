package com.studycafe.member.controller;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.studycafe.member.service.EmailService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MailController {

	@Autowired
	private EmailService emailService;

	@PostMapping("/send")
	@ResponseBody
	public String send(@RequestParam("toEmail") String toEmail)
			throws UnsupportedEncodingException, MessagingException {
		log.info("toEmail 의 값은 : {}",toEmail);
		String authNum = emailService.sendEmail(toEmail);

		// 결과 반환
		return authNum;
	}

}
