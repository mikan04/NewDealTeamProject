package com.studycafe.cs.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class CsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idx;

	@NotNull
	@Column(length = 50)
	private String csTitle;

	@Lob
	@NotNull
	private String csContent;

	@NotNull
	@Column(length = 20, updatable = false)
	private String csWriter;
	
	@ColumnDefault("0")
	private boolean secret;

	@CreationTimestamp
	@Column(updatable = false)
	private Timestamp createDate;

	@LastModifiedDate
	private LocalDateTime modifiedDate;

	@Builder
	public CsEntity(long idx, @NotNull String csTitle, @NotNull String csContent, @NotNull String csWriter, Timestamp createDate,
			LocalDateTime modifiedDate, boolean secret) {
		
		this.idx = idx;
		this.csTitle = csTitle;
		this.csContent = csContent;
		this.csWriter = csWriter;
		this.secret = secret;
		this.createDate = createDate;
		this.modifiedDate = modifiedDate;
	}

}
