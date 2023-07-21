package com.studycafe.chatroom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.studycafe.chatroom.entity.ChatRoomMessageEntity;


public interface ChatRoomMessageRepository extends JpaRepository<ChatRoomMessageEntity, Long> {
	public List<ChatRoomMessageEntity> findByRoomIdx(Long roomIdx);
}
