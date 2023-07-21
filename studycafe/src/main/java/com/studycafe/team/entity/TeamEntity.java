package com.studycafe.team.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

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
	
	@NotNull
	@CreationTimestamp
	private Timestamp createDate;
	
}
