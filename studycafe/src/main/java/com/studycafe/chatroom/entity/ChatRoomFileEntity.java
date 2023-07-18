package com.studycafe.chatroom.entity;

import java.util.UUID;

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
	private long id;
	@NotNull
	@ManyToOne
	@JoinColumn(name = "chatroom")
	private ChatRoomEntity roomEntity;
	@NotNull
	private String chatFilePath;
	
}
