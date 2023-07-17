package com.studycafe.study.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class StudyEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int studyNum;
	
	private int longitude;
	
	private int latitude;
	
}
