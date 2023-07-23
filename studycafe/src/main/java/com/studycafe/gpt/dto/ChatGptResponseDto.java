package com.studycafe.gpt.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class ChatGptResponseDto implements Serializable {
	/*
	 * 웹 서비스 및 API: RESTful 웹 서비스 또는 API를 구축할 때 개체는 종종 클라이언트와 서버 간의 데이터 교환을 위해 JSON
	 * 또는 XML 형식으로 직렬화시킴.
	 */

	/**
	 * @serial ChatGptResponseDto
	 */
	private static final long serialVersionUID = -4518110487040117180L;
	
	// GPT의 응답을 받아와봅시다
	private String id;
	private String object;
	private long created;
	private String model;
	private Usage usage;
	private List<Choice> choices;

	@Getter
	@Setter
	public static class Usage {
		@JsonProperty("prompt_tokens")
		private int promptTokens;
		@JsonProperty("completion_tokens")
		private int completionTokens;
		@JsonProperty("total_tokens")
		private int totalTokens;
	}

	@Getter
	@Setter
	public static class Choice {
		private ChatGptMessage message;
		@JsonProperty("finish_reason")
		private String finishReason;
		private int index;
	}
}
