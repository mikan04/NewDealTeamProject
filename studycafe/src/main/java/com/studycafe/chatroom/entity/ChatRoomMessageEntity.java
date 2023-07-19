package com.studycafe.chatroom.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Entity
@Data
public class ChatRoomMessageEntity {
	public enum MessageType {
		ENTER, TALK
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long ChatRoomMessageEntityIdx;
	@NotNull
	@ManyToOne
	@JoinColumn(name = "chatroom")
	private ChatRoomEntity roomEntity;
	@NotNull
	private String sender;
	@NotNull
	private MessageType type;
	@NotNull
	private String chatMessage;
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "messagetime")
	private Date messageTime;

}
