package com.studycafe.study.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.studycafe.member.dto.MemberDto;
import com.studycafe.member.entity.MemberEntity;
import com.studycafe.study.entity.StudyEntity;
import com.studycafe.study.entity.StudyEntity.StudyEntityBuilder;

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
	@JsonInclude(Include.NON_NULL)
	private int studyNum;
	private String studyTitle;
	private String studyWriter;
	private String studyContent;
	private String studyFilePath;
	private double latitude;
	private double longitude;
	private String reserveDate;
	private String reserveTime;
	@JsonInclude(Include.NON_NULL)
	private String dateTime;
	
	public static StudyEntity toStudyEntity(StudyFormDto studyFormDto) {
		StudyEntityBuilder study = StudyEntity.builder().studyTitle(studyFormDto.getStudyTitle()).studyWriter(studyFormDto.getStudyWriter())
				.studyContent(studyFormDto.getStudyContent())
				.studyFilePath(studyFormDto.getStudyFilePath())
				.latitude(studyFormDto.getLatitude()).longitude(studyFormDto.getLongitude())
				.reserveDate(LocalDate.parse(studyFormDto.getReserveDate()))
				.reserveTime(LocalDateTime.parse(studyFormDto.getReserveTime(), DateTimeFormatter.ISO_DATE_TIME));
		
		if(studyFormDto.getStudyNum() >= 0)
			study.studyNum(studyFormDto.getStudyNum());
		if(studyFormDto.getDateTime() != null)
			study.dateTime(LocalDateTime.parse(studyFormDto.getDateTime(), DateTimeFormatter.ISO_DATE_TIME));
		StudyEntity studyEntity = study.build();
		return studyEntity;
	}


}
