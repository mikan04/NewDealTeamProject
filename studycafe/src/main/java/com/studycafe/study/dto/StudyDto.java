package com.studycafe.study.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class StudyDto {

	// 인터셉터 전달용 dto
	private String studyTitle;
	private LocalDateTime dateTime;

	@Builder
	public StudyDto(String studyTitle, LocalDateTime dateTime) {
		
		this.studyTitle = studyTitle;
		this.dateTime = dateTime;
	}

}
