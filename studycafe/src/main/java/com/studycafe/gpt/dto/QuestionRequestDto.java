package com.studycafe.gpt.dto;

import java.io.Serializable;

import lombok.Getter;

@Getter
public class QuestionRequestDto implements Serializable {
	/**
	 * @serial QuestionRequestDto
	 */
	private static final long serialVersionUID = -4486046628251806973L;
	
	// 내가 질문한걸 객체화 시켜서 DTO로 보내봅시다.
	private String question;
}
