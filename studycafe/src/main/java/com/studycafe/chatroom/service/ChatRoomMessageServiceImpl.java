package com.studycafe.chatroom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studycafe.chatroom.entity.ChatRoomMessageEntity;
import com.studycafe.chatroom.repository.ChatRoomMessageRepository;

@Service
public class ChatRoomMessageServiceImpl implements ChatRoomMessageService {

	@Autowired
	private ChatRoomMessageRepository messageRepository;

	/* 메세지 목록 불러오기 */
	@Override
	public List<ChatRoomMessageEntity> getMessage(Long roomIdx) {

		List<ChatRoomMessageEntity> messageList = messageRepository.findByRoomIdx(roomIdx);
		return messageList;

	}

	/* 메세지 등록 */
	@Override
	public void insertMessage(ChatRoomMessageEntity chatRoomMessage) {

		messageRepository.save(chatRoomMessage);
	}

}
