package com.studycafe.member.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class MemberApiController {

	/*
	 * 깃 로그인 테스트 
	 * 아래 링크 들어가면 팀 프로젝트에 가입 가능.
	 * https://github.com/login/oauth/authorize?client_id=Iv1.427e6b094359a979
	 * 
	 * 개선사항 status 404 뜰 경우 깃허브 연동 실패 및, 페이지 이동 경로 변경 추가해야함.
	 * 
	 * */
	
	/* 깃허브 액세스 키 */
	@Value("${key.github.id}")
    private String githubId;
	@Value("${key.github.access-key}")
    private String githubSecret;
	
	@GetMapping("/git")
	public ResponseEntity<String> getGitUserInfo(@RequestParam String code) throws IOException, ParseException{
		
		// 로그인한 아이디 Code 값을 받아온다.
	    String accessToken = getAccessToken(code);

		return ResponseEntity.ok(accessToken);
	}
	
	// 토큰 값 조회
	private String getAccessToken(String code) throws IOException, ParseException {
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
	    
	    JSONObject jsonObj = (JSONObject)obj;
	
	    String access_token = (String)jsonObj.get("access_token");
	    
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

	
    private String getResponse(HttpURLConnection conn, int responseCode) throws IOException {
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
}
