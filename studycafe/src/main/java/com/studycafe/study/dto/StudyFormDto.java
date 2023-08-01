package com.studycafe.study.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.joda.time.DateTime;

import com.studycafe.member.dto.MemberDto;
import com.studycafe.member.entity.MemberEntity;
import com.studycafe.study.entity.StudyEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class StudyFormDto {

	private String studyTitle;
	private String studyWriter;
	private String studyContent;
	private String studyFilePath;
	private double latitude;
	private double longitude;
	private String reserveDate;
	private String reserveTime;

	public static StudyEntity toStudyEntity(StudyFormDto studyFormDto) {
//		String timeSliced = studyFormDto.getReserveTime().substring(0, 0)
		
		StudyEntity study = StudyEntity.builder().studyTitle(studyFormDto.getStudyTitle()).studyWriter(studyFormDto.getStudyWriter())
				.studyContent(studyFormDto.getStudyContent())
				.studyFilePath(studyFormDto.getStudyFilePath())
				.latitude(studyFormDto.getLatitude()).longitude(studyFormDto.getLongitude())
				.reserveDate(LocalDate.parse(studyFormDto.getReserveDate()))
				.reserveTime(LocalDateTime.parse(studyFormDto.getReserveTime(), DateTimeFormatter.ISO_DATE_TIME)).build();

		return study;

	}

//	@Builder
//	public StudyFormDto() {
//
//	}

}
