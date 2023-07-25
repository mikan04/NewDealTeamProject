package com.studycafe.team.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity
public class TeamEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long teamNumber;
	
	@NotNull
	@Column(unique=true)
	private String teamName;
	
	@NotNull
	private String teamHead;
	
	@ColumnDefault("0")
	private int point; 
	
	@ColumnDefault("0")
	private int approveCount; 
	
	@NotNull
	@CreationTimestamp
	private Timestamp createDate;
	
}
