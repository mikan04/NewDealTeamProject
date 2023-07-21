package com.studycafe.chatroom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studycafe.chatroom.entity.ChatRoomEntity;


public interface ChatRoomRepository extends JpaRepository<ChatRoomEntity, Long> {

}
