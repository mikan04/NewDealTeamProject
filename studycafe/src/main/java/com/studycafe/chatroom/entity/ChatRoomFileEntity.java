package com.studycafe.chatroom.entity;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


import lombok.Data;

@Entity
@Data
public class ChatRoomFileEntity {	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToOne
	@JoinColumn(name = "chatroom")
	private ChatRoomEntity roomEntity;
	private String chatFilePath;
	
}
