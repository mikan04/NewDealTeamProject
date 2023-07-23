package com.studycafe.gpt.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.studycafe.gpt.dto.ChatGptMessage;
import com.studycafe.gpt.dto.ChatGptRequestDto;
import com.studycafe.gpt.dto.ChatGptResponseDto;
import com.studycafe.gpt.dto.QuestionRequestDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ChatGptService {
	
	@Value("${gpt.api-key}")
	private String apiKey;
	
	@Value("${gpt.max_token}")
	private int maxToken;
	
	@Value("${gpt.model}")
	private String model;
	
	@Value("${gpt.role}")
	private String role;
	
	@Value("${gpt.temperature}")
	private double temperature;
	
	@Value("${gpt.stream}")
	private boolean stream;
	

	private static RestTemplate restTemplate = new RestTemplate();

	// http header에 gpt로 보낼 정보를 담아서 보내줌.
	public HttpEntity<ChatGptRequestDto> buildHttpEntity(ChatGptRequestDto requestDto) {
		
		log.info("buildHttpEntity 실행");
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/json; charset=UTF-8"));
		headers.add("Authorization", "Bearer " + apiKey);
		return new HttpEntity<>(requestDto, headers);
	}
	
	// gpt의 응답은 restTemplate화 시켜서 들어옴.
	public ChatGptResponseDto getResponse(HttpEntity<ChatGptRequestDto> chatGptRequestDtoHttpEntity) {
		
		log.info("getResponse 실행");
		
		ResponseEntity<ChatGptResponseDto> responseEntity = restTemplate.postForEntity("https://api.openai.com/v1/chat/completions",
				chatGptRequestDtoHttpEntity, ChatGptResponseDto.class);

		return responseEntity.getBody();
	}
	
	// 들어온 message를 받아서 QuestionRequestDto를 통해 전달되어 builder 시킴.
	public ChatGptResponseDto askQuestion(QuestionRequestDto requestDto) {
		log.info("askQuestion 실행");
		
		List<ChatGptMessage> messages = new ArrayList<>();
        messages.add(ChatGptMessage.builder()
                .role(role)
                .content(requestDto.getQuestion())
                .build());
		
        return this.getResponse(
                this.buildHttpEntity(
                        new ChatGptRequestDto(
                        		model,
                        		maxToken,
                        		temperature,
                        		stream,
                                messages
                        )
                )
        );
	}
}
