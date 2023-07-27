package com.studycafe.gpt.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChatGptRequestDto implements Serializable {

	/**
	 * @serial ChatGptRequestDto
	 */
	private static final long serialVersionUID = -2086126620122641945L;
	
	// 들어가기에 앞서 https://platform.openai.com/docs/api-reference/chat 여기 한번씩 봅시다.
	
	// GPT한테 질문을 해봅시다.
	private String model;
	@JsonProperty("max_tokens")
	private Integer maxTokens;
	private Double temperature;
	private Boolean stream;
	private List<ChatGptMessage> messages;

	@Builder
    public ChatGptRequestDto(String model, Integer maxTokens, Double temperature,
                          Boolean stream, List<ChatGptMessage> messages
                          /*,Double topP*/) {
        this.model = model;
        this.maxTokens = maxTokens;
        this.temperature = temperature;
        this.stream = stream;
        this.messages = messages;
    }
}
