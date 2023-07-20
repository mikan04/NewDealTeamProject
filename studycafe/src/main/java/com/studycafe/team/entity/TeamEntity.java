package com.studycafe.team.entity;

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
	
	@Lob // 많은 양의 데이터를 db에 담을때 쓰는 어노테이션. db타입 => longtext
	private String teamContent;
	
	@NotNull
	@CreationTimestamp
	private Timestamp createDate;
	
}
