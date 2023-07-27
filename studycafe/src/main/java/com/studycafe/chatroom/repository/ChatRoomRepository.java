package com.studycafe.chatroom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.studycafe.chatroom.entity.ChatRoomEntity;

public interface ChatRoomRepository extends JpaRepository<ChatRoomEntity, Long> {

	@Query("SELECT cr FROM ChatRoomEntity cr WHERE cr.teamEntity.teamNumber = :teamNumber ")
	public ChatRoomEntity findChatRoomByTeamNumber(long teamNumber);
}
