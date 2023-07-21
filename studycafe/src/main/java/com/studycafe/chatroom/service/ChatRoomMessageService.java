package com.studycafe.chatroom.service;

import java.util.List;

import com.studycafe.chatroom.entity.ChatRoomMessageEntity;

public interface ChatRoomMessageService {
	
	/* 메세지 목록 불러오기 */
	public List<ChatRoomMessageEntity> getMessage(Long roomIdx);
	
	/* 메세지 등록 */
	public void insertMessage(ChatRoomMessageEntity chatRoomMessage);

	
}
