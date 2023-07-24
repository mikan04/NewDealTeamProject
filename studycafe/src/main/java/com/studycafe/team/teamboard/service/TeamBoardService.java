package com.studycafe.team.teamboard.service;

import java.util.List;

import com.studycafe.team.teamboard.dto.TeamBoardDTO;

public interface TeamBoardService {
	
	// 팀 등록 게시판 리스트 및 페이징
	public List<TeamBoardDTO> getTeamBoardList();
	
	// 팀 게시글 등록
	public void teamBoardRegis(TeamBoardDTO teamBoardDTO);
	
	// 조회
	public TeamBoardDTO getTeamBoardPost(long idx);
}
