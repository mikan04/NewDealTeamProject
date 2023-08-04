package com.studycafe.member.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.studycafe.member.entity.Join;
import com.studycafe.member.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MemberApiController {

	/*
	 * 깃 로그인 테스트 아래 링크 들어가면 팀 프로젝트에 가입 가능. https://github.com/login/oauth/authorize?client_id=Iv1.427e6b094359a979
	 */

	@Autowired
	private MemberService memberService;

	/* 깃허브 액세스 키 */
	@Value("${key.github.id}")
	private String githubId;
	@Value("${key.github.access-key}")
	private String githubSecret;

	@GetMapping("/git")
	public String getGitUserInfo(@RequestParam String code, Model model) throws IOException, ParseException {
		// 로그인한 아이디 Code 값을 받아온다.
		String accessToken = getGitAccessToken(code);

		JSONParser parser = new JSONParser();
		JSONObject jsonToken = (JSONObject) parser.parse(accessToken);

		String id = (String) jsonToken.get("login");

		// 스터디 사이트 가입여부 확인
		if (memberService.idCheck(id) == false) {
			
			Map<String, String> userInfo = new HashMap<>();

			userInfo.put("username", id);
			userInfo.put("joinMethod", Join.GIT_HUB.toString());

			model.addAttribute("userInfo", userInfo);

			return "/member/joinForm";
			
		} else {

			throw new AccessDeniedException("해당 아이디로 가입된 계정이 존재합니다.");
		}

	}

	// 토큰 값 조회
	private String getGitAccessToken(String code) throws IOException, ParseException {
		URL url = new URL("https://github.com/login/oauth/access_token");

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Accept", "application/json");

		try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()))) {
			bw.write("client_id=" + githubId + "&client_secret=" + githubSecret + "&code=" + code);
			bw.flush();
		}

		int responseCode = conn.getResponseCode();

		String responseData = getResponse(conn, responseCode);

		conn.disconnect();

		System.out.println("responseData : " + responseData);

		return access(responseData);
	}

	private String access(String response) throws IOException, ParseException {
		// json 파싱하기 위해 json.simple 의존성 추가
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(response);

		JSONObject jsonObj = (JSONObject) obj;

		String access_token = (String) jsonObj.get("access_token");

		System.out.println("access_token : " + access_token);

		URL url = new URL("https://api.github.com/user");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");
		conn.setRequestProperty("Authorization", "token " + access_token);

		int responseCode = conn.getResponseCode();

		String result = getResponse(conn, responseCode);

		conn.disconnect();

		System.out.println("result : " + result);

		return result;
	}

	private String getResponse(HttpURLConnection conn, int responseCode) throws IOException, ParseException {
		StringBuilder sb = new StringBuilder();

		if (responseCode == 200) {
			try (InputStream is = conn.getInputStream();
					BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
				for (String line = br.readLine(); line != null; line = br.readLine()) {
					sb.append(line);
				}
			}
		}

		return sb.toString();
	}
	
	/*
	 * 카카오 로그인
	 * */
	
	// 카카오 연동정보 조회
	@GetMapping(value = "/login/oauth_kakao")
	public String oauthKakao (@RequestParam(value = "code", required = false) String code,
		RedirectAttributes dir, HttpSession session, Model model) throws Exception {

		System.out.println("code : " + code);
        String access_Token = getKakaoAccessToken(code);
        System.out.println("access_Token : " + access_Token);
        
        
        HashMap<String, Object> kakaoUserInfo = getUserInfo(access_Token);
        System.out.println("access_Token : " + access_Token);
       
        JSONObject kakaoInfo =  new JSONObject(kakaoUserInfo);
        
        String id = (String)kakaoInfo.get("id");

		System.out.println("session : " + id);

		// 스터디 사이트 가입여부 확인
        if (memberService.idCheck(id) == false) {
			
			Map<String, String> userInfo = new HashMap<>();

			userInfo.put("username", id);
			userInfo.put("joinMethod", Join.GIT_HUB.toString());

			model.addAttribute("userInfo", userInfo);

			return "/member/joinForm";
			
		} else {

			throw new AccessDeniedException("해당 아이디로 가입된 계정이 존재합니다.");
		}
	}
	
    // 토큰 발급
	public String getKakaoAccessToken (String authorize_code) {
        String access_Token = "";
        String refresh_Token = "";
        String reqURL = "https://kauth.kakao.com/oauth/token";

        try {
            URL url = new URL(reqURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // URL연결은 입출력에 사용 될 수 있고, POST 혹은 PUT 요청을 하려면 setDoOutput을 true로 설정해야함.
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            // POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=8cf10493bca5fb96602027f51d020cba");	// rest key
            sb.append("&redirect_uri=http://localhost:8080/login/oauth_kakao"); // redirect 설정값
            sb.append("&code=" + authorize_code);
            bw.write(sb.toString());
            bw.flush();

            // 결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();

            // 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }

            // Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            access_Token = element.getAsJsonObject().get("access_token").getAsString();
            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

            br.close();
            bw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return access_Token;
    }
	
    // 유저 정보 조회
    public HashMap<String, Object> getUserInfo (String access_Token) {
    	
    	System.out.println("access_Token : " + access_Token);

        // 요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
        HashMap<String, Object> userInfo = new HashMap<String, Object>();
        String reqURL = "https://kapi.kakao.com/v2/user/me";
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // 요청에 필요한 Header에 포함될 내용
            conn.setRequestProperty("Authorization", "Bearer " + access_Token);

            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }

            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            String id = element.getAsJsonObject().get("id").getAsString();
           
            userInfo.put("accessToken", access_Token);
            userInfo.put("id", id);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return userInfo;
    }
}
