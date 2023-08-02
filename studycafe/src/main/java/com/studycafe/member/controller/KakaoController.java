package com.studycafe.member.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.studycafe.member.entity.MemberEntity;
import com.studycafe.member.service.KakaoAPI;
import com.studycafe.member.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class KakaoController {

	@Autowired
	private MemberService memberService;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private KakaoAPI kakao;

	@GetMapping(value = "/kakaoLoginCallback1")
	public String indexkakao() {

		return "/member/kakaotest";
	}

//	간편정상작동
//    @RequestMapping(value="/kakao")
//    public String loginkakao() {
//        
//        return "/member/kakaotest";
//    }

//	@RequestMapping(value="/kakaoLoginCallback")
////		@PostMapping("/kakaoLoginCallback")
//	    public String loginkakao(@RequestParam("code") String code, HttpSession session) {
//	        String access_Token = kakao.getAccessToken(code);
//	        HashMap<String, Object> userInfo = kakao.getUserInfo(access_Token);
//	        System.out.println("login Controller : " + userInfo);
//
//	        //    클라이언트의 이메일이 존재할 때 세션에 해당 이메일과 토큰 등록
//	        if (userInfo.get("email") != null) {
//	            session.setAttribute("userId", userInfo.get("email"));
//	            session.setAttribute("access_Token", access_Token);
//
//	            System.out.println("aasdasdsd"+userInfo.get("email"));
//	        }
//
//
//	        return "redirect:/";
//	    }

	@GetMapping(value = "/kakaoLoginCallback")
	public String loginkakao(@RequestParam String code, HttpServletRequest request, Model model) throws IOException {
		String access_Token = kakao.getAccessToken(code);
		Map<String, Object> userInfo = kakao.getUserInfo(access_Token);
		System.out.println("login Controller : " + userInfo);

		@SuppressWarnings("unchecked")
		Map<String, Object> account = (Map<String, Object>) userInfo.get("kakao_account");

		@SuppressWarnings("unchecked")
		String ninkname = (String) ((Map<String, Object>) account.get("profile")).get("nickname");
		String email = (String) account.get("email");
		String id = (String) userInfo.get("id");

		log.info("닉네임 : " + ninkname);
		log.info("이메일 : " + email);
		log.info("아이디 : " + id);

		MemberEntity kakaoResultInfo = new MemberEntity();
		kakaoResultInfo.setNickName(ninkname);
		kakaoResultInfo.setEmail(email);

		HttpSession session = request.getSession();
		session.setAttribute("kakaoResultInfo", kakaoResultInfo);
		
		MemberEntity loginUser = kakaoResultInfo;

		// 클라이언트의 이메일이 존재할 때 세션에 해당 이메일과 토큰 등록
		if (userInfo.get("email") != null) {
			session.setAttribute("kakaoResultInfo", kakaoResultInfo);

			System.out.println("aasdasdsd" + userInfo.get("email"));
			session.setAttribute("loginUser", loginUser);
			return "redirect:/main";
		}

		return "redirect:/";
	}

	@PostMapping(value = "/logout")
	public String logout(HttpSession session) {
		kakao.kakaoLogout((String) session.getAttribute("access_Token"));
		session.removeAttribute("access_Token");
		session.removeAttribute("userId");
		return "index";
	}

}
