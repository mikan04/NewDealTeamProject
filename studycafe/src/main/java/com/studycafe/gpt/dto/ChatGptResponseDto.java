package com.studycafe.gpt.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

	private String id;
	private String object;
	private LocalDate created;
	private String model;
	private List<Choice> choices;

	@Builder
	public ChatGptResponseDto(String id, String object, LocalDate created, String model, List<Choice> choices) {
		this.id = id;
		this.object = object;
		this.created = created;
		this.model = model;
		this.choices = choices;
	}
}
