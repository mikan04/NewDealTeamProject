package com.studycafe.gpt.dto;

import java.io.Serializable;

import lombok.Getter;

@Getter
public class QuestionRequestDto implements Serializable {
	/**
	 * @serial QuestionRequestDto
	 */
	private static final long serialVersionUID = -4486046628251806973L;
	
	private String question;
}
