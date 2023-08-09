package com.studycafe.chatroom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studycafe.chatroom.entity.ChatRoomEntity;
import com.studycafe.chatroom.repository.ChatRoomMessageRepository;
import com.studycafe.chatroom.repository.ChatRoomRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ChatRoomServiceImpl implements ChatRoomService {

	@Autowired
	private ChatRoomRepository chatRoomRepository;
	
	@Autowired
	private ChatRoomMessageRepository chatRoomMessageRepository;

	/* 채팅방 등록 */
	@Override
	public void addChatRoom(ChatRoomEntity chatRoom) {
		
		chatRoomRepository.save(chatRoom);
	}
	
	// 팀 삭제 전 메세지 삭제
	@Override
	public void deleteChatRoomMessage(Long roomId) {
		
		chatRoomMessageRepository.deleteByRoomIdx(roomId);
	}
	
	// 팀 삭제
	@Override
	public void deleteChatRoom(Long roomId) {
		
		chatRoomRepository.deleteById(roomId);
	}

	/* 채팅방 목록 불러오기 */
	@Override
	public List<ChatRoomEntity> getRoomList() {

		List<ChatRoomEntity> roomList = chatRoomRepository.findAll();
		return roomList;
	}

	/* 채팅방 이동 */
	@Override
	public ChatRoomEntity findRoom(Long teamNumber) {
		
		ChatRoomEntity room = chatRoomRepository.findChatRoomByTeamNumber(teamNumber);
		log.info("ChatRoomEntity : {}",room.getRoomIdx());
		log.info("ChatRoomEntity : {}",room.getRoomName());
		log.info("ChatRoomEntity : {}",room.getTeamEntity());
		return room;
	}

}
