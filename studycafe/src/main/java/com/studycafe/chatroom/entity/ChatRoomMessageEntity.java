package com.studycafe.chatroom.entity;

import java.sql.Timestamp;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;


import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Entity
@Data
public class ChatRoomMessageEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long messageIdx;
	
	@NotNull
	private Long roomIdx;
	
	private String username;
	
	@NotNull
	private String nickName;
	
	@NotNull
	private String msg;
		
	@CreationTimestamp
	private Timestamp regDate;
	
}
