package com.studycafe.cs.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.studycafe.cs.dto.CsBoardDTO;
import com.studycafe.cs.entity.CsEntity;

public interface CsService {

	// 게시판 리스트 및 페이징
	public Page<CsBoardDTO> getCsBoardList(Pageable pageable);

	// 게시판 리스트 인덱스 페이지
	public List<CsEntity> getCsBoardListToIndex();

	// 게시글 등록
	public Long csBoardRegis(CsBoardDTO csBoardDTO);

	// 조회
	public CsBoardDTO getCsBoardPost(long idx);

	// 삭제
	public boolean deleteCsBoard(long idx);
}
