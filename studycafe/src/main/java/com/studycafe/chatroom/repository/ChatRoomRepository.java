package com.studycafe.chatroom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studycafe.chatroom.entity.ChatRoomEntity;


public interface ChatRoomRepository extends JpaRepository<ChatRoomEntity, String> {

}
