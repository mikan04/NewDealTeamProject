package com.studycafe.chatroom.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.studycafe.team.entity.TeamEntity;

import lombok.Data;

@Entity
@Data
public class ChatRoomEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long chatroom;
	@OneToOne
	@JoinColumn(name = "teamnumber")	
	private TeamEntity teamEntity;
	
}
	