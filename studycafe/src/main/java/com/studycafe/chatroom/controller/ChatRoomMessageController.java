package com.studycafe.chatroom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.studycafe.chatroom.entity.ChatRoomMessageEntity;
import com.studycafe.chatroom.service.ChatRoomMessageServiceImpl;

import lombok.extern.slf4j.Slf4j;

/* 메세지 컨트롤러 */
@Slf4j
@Controller
@RequestMapping("/chatRoomMessage/*")
public class ChatRoomMessageController {

	@Autowired
	private ChatRoomMessageServiceImpl messageService;

	/* 채팅방 메세지 불러오기 */
	@GetMapping("/getMessage")
	@ResponseBody
	public List<ChatRoomMessageEntity> getMessage(@RequestParam("roomIdx") Long roomIdx) {

		List<ChatRoomMessageEntity> messageList = messageService.getMessage(roomIdx);

		return messageList;
	}

	/* 채팅 메세지 저장 */
	@PostMapping("/insertMessage")
	@ResponseBody
	public void insertMessage(@RequestParam("username") String username, @RequestParam("nickName") String nickName,
			@RequestParam("roomIdx") Long roomIdx, @RequestParam("msg") String msg) {

		log.info("chatRoomMessage 123123: {}", username);

		ChatRoomMessageEntity chatRoomMessage = new ChatRoomMessageEntity();
		chatRoomMessage.setMsg(msg);
		chatRoomMessage.setRoomIdx(roomIdx);
		chatRoomMessage.setUsername(username);
		chatRoomMessage.setNickName(nickName);

		messageService.insertMessage(chatRoomMessage);
	}

}
