package com.studycafe.gpt.dto;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChatGptMessage implements Serializable{
	
	/**
	 * @serial ChatGptMessage
	 */
	private static final long serialVersionUID = -9059915123126803343L;

	private String role;
	
	private String content;

	@Builder
	public ChatGptMessage(String role, String content) {
		this.role = role;
		this.content = content;
	}
	
}
