package com.studycafe.qna.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Entity
@Data
public class QnaReEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long qnaReplyNum;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "qnaNum")
	private QnaEntity qnaEntity;
	
	@Column(length = 100)
	@NotNull
	private String QnaReplyWriter;
	
	@Column(columnDefinition = "TEXT")
	@NotNull
	private String qnaReplyContent;
	
	@Column(length = 10)
	private String isDeleted;
	
	private long qnaReplyRef;
	
	@CreationTimestamp
	private Timestamp qnaReplyDate;
	
	private int qnaReplyDepth;

}
