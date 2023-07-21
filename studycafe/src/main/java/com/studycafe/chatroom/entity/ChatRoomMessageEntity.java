package com.studycafe.chatroom.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Entity
@Data
@Table(name="chatRoomMessage")
public class ChatRoomMessageEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long messageIdx;
	
	@NotNull
	private Long roomIdx;
	
	private String userId;
	
	@NotNull
	private String userName;
	
	@NotNull
	private String msg;
	
	@NotNull
	private String chatMessage;
	
	@CreationTimestamp
	private Timestamp regDate;

}
