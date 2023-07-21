package com.studycafe.chatroom.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
@Table(name="chatRoom")
public class ChatRoomEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long roomIdx;
	
	@NotNull
	private String roomName;
	
}
