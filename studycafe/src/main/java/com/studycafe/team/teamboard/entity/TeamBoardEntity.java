package com.studycafe.team.teamboard.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Entity
@Data
public class TeamBoardEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long teamBoardNum;
	
	@NotNull
	@Column(length = 20)
	private String teamBoardTitle;
	
	@Lob
	@NotNull
	private String teamBoardContent;
	
	@NotNull
	@Column(length = 20)
	private String teamBoardWriter;
	
	@Column(nullable = true)
	private String imgUrl;
	
	@NotNull
	@CreationTimestamp
	private Timestamp createDate;
	
}
