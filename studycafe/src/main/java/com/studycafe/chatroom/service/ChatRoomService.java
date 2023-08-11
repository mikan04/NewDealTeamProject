package com.studycafe.chatroom.service;


import java.util.List;

import com.studycafe.chatroom.entity.ChatRoomEntity;

public interface ChatRoomService {

	/* 채팅방 등록 */
	public void addChatRoom(ChatRoomEntity chatRoom);

	/* 채팅방 목록 불러오기 */
	public List<ChatRoomEntity> getRoomList();

	/* 채팅방 이동 */
	public ChatRoomEntity findRoom(Long teamNumber);
	
	// 팀 메세지 삭제
	void deleteChatRoomMessage(Long roomId);
	
	// 팀 폭파
	public void deleteChatRoom(Long roomId);
	
	
}

