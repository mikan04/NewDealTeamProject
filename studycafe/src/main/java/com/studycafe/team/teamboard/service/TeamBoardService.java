package com.studycafe.team.teamboard.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.studycafe.team.teamboard.dto.TeamBoardDTO;

public interface TeamBoardService {

	// 팀 등록 게시판 리스트 및 페이징
	public Page<TeamBoardDTO> getTeamBoardList(Pageable pageable);

	// 팀 게시글 등록
	public Long teamBoardRegis(TeamBoardDTO teamBoardDTO);

	// 조회
	public TeamBoardDTO getTeamBoardPost(long idx);

}
