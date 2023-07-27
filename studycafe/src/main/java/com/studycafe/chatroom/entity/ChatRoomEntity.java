package com.studycafe.chatroom.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.studycafe.team.entity.TeamEntity;

import lombok.Data;

@Entity
@Data
public class ChatRoomEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long roomIdx;

	@NotNull
	private String roomName;

	@NotNull
	@OneToOne
	@JoinColumn(name = "teamNumber")
	private TeamEntity teamEntity;

}
