package com.studycafe.gpt.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Choice implements Serializable {

    /**
	 * @serial Choice
	 */
	private static final long serialVersionUID = 7157422639832559082L;
	
	// devtools에 표시되는 JSON 데이터도 받아봅시다. 
	private String text;
    private Integer index;
    @JsonProperty("finish_reason")
    private String finishReason;

    @Builder
    public Choice(String text, Integer index, String finishReason) {
        this.text = text;
        this.index = index;
        this.finishReason = finishReason;
    }
}
