package com.studycafe.chatroom.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
public class ChatRoomFileEntity {	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long ChatRoomFileEntityIdx;
	@NotNull
	@ManyToOne
	@JoinColumn(name = "chatRoom")
	private ChatRoomEntity roomEntity;
	@NotNull
	private String chatFilePath;
	
}
