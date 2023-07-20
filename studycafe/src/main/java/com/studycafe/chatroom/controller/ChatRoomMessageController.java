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

/* 메세지 컨트롤러 */

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
	public void insertMessage(@RequestParam("userId") String userId, @RequestParam("userName") String userName,
			@RequestParam("roomIdx") Long roomIdx, @RequestParam("msg") String msg) {

		ChatRoomMessageEntity chatRoomMessage = new ChatRoomMessageEntity();

		chatRoomMessage.setMsg(msg);
		chatRoomMessage.setRoomIdx(roomIdx);
		chatRoomMessage.setUserId(userId);
		chatRoomMessage.setUserName(userName);

		messageService.insertMessage(chatRoomMessage);
	}

}
