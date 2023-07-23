package com.studycafe.chatroom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studycafe.chatroom.entity.ChatRoomEntity;
import com.studycafe.chatroom.repository.ChatRoomRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ChatRoomServiceImpl implements ChatRoomService {

	@Autowired
	private ChatRoomRepository chatRoomRepository;

	/* 채팅방 등록 */
	@Override
	public void addChatRoom(ChatRoomEntity chatRoom) {

		chatRoomRepository.save(chatRoom);
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
