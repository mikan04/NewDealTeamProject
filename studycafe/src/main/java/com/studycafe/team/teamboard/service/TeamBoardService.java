package com.studycafe.team.teamboard.service;

import org.springframework.web.multipart.MultipartFile;

import com.studycafe.team.teamboard.entity.TeamBoardEntity;

public interface TeamBoardService {
	
	// 팀 게시글 등록
	public void teamBoardRegis(MultipartFile file, TeamBoardEntity teamBoard);
}
