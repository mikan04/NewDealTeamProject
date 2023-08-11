package com.studycafe.chatroom.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.studycafe.chatroom.entity.ChatRoomEntity;
import com.studycafe.chatroom.service.ChatRoomServiceImpl;

import lombok.extern.slf4j.Slf4j;

/* 채팅방 컨트롤러 */

@Slf4j
@Controller
@RequestMapping("/chatRoom/*")
public class ChatRoomController {

	@Autowired
	private ChatRoomServiceImpl RoomService;

	final List<ChatRoomEntity> roomList = new ArrayList<ChatRoomEntity>();

	@GetMapping("/chat")
	public ModelAndView chat() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/chatroom/chat");
		return mv;
	}

	/* 방 페이지 */
	@GetMapping("/room")
	public String room(Model model) {
		List<ChatRoomEntity> roomList = RoomService.getRoomList();
		model.addAttribute("roomList", roomList);
		return "room";
	}

	/* 방 생성하기 */
	@PostMapping("/createRoom")
	public @ResponseBody List<ChatRoomEntity> createRoom(@RequestParam HashMap<Object, Object> params) {
		String roomName = (String) params.get("roomName");
		if (roomName != null && !roomName.trim().equals("")) {
			ChatRoomEntity room = new ChatRoomEntity();
			room.setRoomName(roomName);
			RoomService.addChatRoom(room);
			roomList.add(room);
		}
		return roomList;
	}

	/* 채팅방 */
	@GetMapping("/moveChating")
	public ModelAndView chating(@RequestParam("teamNumber") Long teamNumber) {
		ModelAndView mv = new ModelAndView();
		log.info("teamNumber : {}", teamNumber);
		ChatRoomEntity room = RoomService.findRoom(teamNumber);
		log.info("룸:{}", room);
		if (room.getRoomIdx() != null && room.getRoomName() != null && room.getTeamEntity() != null) {
			mv.addObject("roomIdx", room.getRoomIdx());
			mv.addObject("room", room);
			mv.setViewName("chatroom/chat");
		} else {
			mv.setViewName("/");
		}
		return mv;
	}

}
