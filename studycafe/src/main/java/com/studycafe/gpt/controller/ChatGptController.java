package com.studycafe.gpt.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.studycafe.gpt.dto.ChatGptResponseDto;
import com.studycafe.gpt.dto.QuestionRequestDto;
import com.studycafe.gpt.service.ChatGptService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ChatGptController {

	private final ChatGptService chatGptService;

	public ChatGptController(ChatGptService chatGptService) {
		this.chatGptService = chatGptService;
	}
	
	// gpt 페이지 입장
	@GetMapping("/gpt/opengpt")
	public String openGpt() {
		return "/gpt/gptpage";
	}

	@PostMapping("/chat-gpt/question")
	@ResponseBody
	public ResponseEntity<ChatGptResponseDto> sendQuestion(@RequestBody QuestionRequestDto requestDto) {
		
		log.info("gpt 질문들어옴 : {}", requestDto.getQuestion());
		
		ChatGptResponseDto responseDto = chatGptService.askQuestion(requestDto);

        return ResponseEntity.ok(responseDto);

	}
}
