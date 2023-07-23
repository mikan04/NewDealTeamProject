package com.studycafe.gpt.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
	

	private static RestTemplate restTemplate = new RestTemplate();

	public HttpEntity<ChatGptRequestDto> buildHttpEntity(ChatGptRequestDto requestDto) {
		
		log.info("buildHttpEntity 실행");
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/json; charset=UTF-8"));
		headers.add("Authorization", "Bearer " + apiKey);
		return new HttpEntity<>(requestDto, headers);
	}

	public ChatGptResponseDto getResponse(HttpEntity<ChatGptRequestDto> chatGptRequestDtoHttpEntity) {
		
		log.info("getResponse 실행");
		
		ResponseEntity<ChatGptResponseDto> responseEntity = restTemplate.postForEntity("https://api.openai.com/v1/completions",
				chatGptRequestDtoHttpEntity, ChatGptResponseDto.class);

		return responseEntity.getBody();
	}

	public ChatGptResponseDto askQuestion(QuestionRequestDto requestDto) {
		log.info("askQuestion 실행");
		
		return this.getResponse(this.buildHttpEntity(new ChatGptRequestDto(model,
				requestDto.getQuestion(), maxToken, 0.0, 1.0)));
	}
}
