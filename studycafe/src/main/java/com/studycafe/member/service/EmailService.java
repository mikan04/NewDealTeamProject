package com.studycafe.member.service;

import java.io.UnsupportedEncodingException;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.studycafe.utils.config.MailConfig;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {


	private final JavaMailSender javaMailSender;

	private String authNum;

	public void createCode() {
		Random random = new Random();
		StringBuilder key = new StringBuilder();

		for (int i = 0; i < 8; i++) {
			int index = random.nextInt(3);

			switch (index) {
			case 0:
				key.append((char) ((int) random.nextInt(26) + 97));
				break;
			case 1:
				key.append((char) ((int) random.nextInt(26) + 65));
				break;
			case 2:
				key.append(random.nextInt(9));
				break;
			}
		}
		authNum = key.toString();
	}

	public MimeMessage createEmailForm(String email) throws MessagingException, UnsupportedEncodingException {
		createCode();

		String toEmail = email;
		String title = "스터디카페 회원가입 인증 번호";

		MimeMessage message = javaMailSender.createMimeMessage();
		message.setSubject(title);
		message.setFrom();


		String content = "<div style='margin:100px;'>";
		content += " <h1> 안녕하세요.</h1>";
		content += "<h1> 스터디카페 입니다.</h1>";
		content += "<br><p>아래 인증번호를 회원가입 창으로 돌아가 입력해주세요.</p><br>";
		content += "<div align='center; style='border:1px solid black;'>";
		content += "<h3 style='color:blue'> 회원가입 인증 코드 입니다. </h3>";
		content += "<div style='font-size:130%'>" + authNum + "</div>";
		content += "</div></div>";

		message.setContent(content, "text/html; charset=utf-8");


		message.addRecipients(MimeMessage.RecipientType.TO, toEmail);

		return message;
	}

	public String sendEmail(String toEmail) throws MessagingException, UnsupportedEncodingException {
		
		MimeMessage emailForm = createEmailForm(toEmail);

		javaMailSender.send(emailForm);

		return authNum; 
	}
}