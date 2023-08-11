package com.studycafe.team.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.studycafe.team.entity.ResultAuthEntity;

public interface ResultAuthService {

	public Page<ResultAuthEntity> resultAuthList(Pageable pageable); // 게시글 조회
	public Page<ResultAuthEntity> resultAuthSearchList(String keyword, Pageable pageable); // 게시글 키워드 조회
	public void resultAuthInsert(ResultAuthEntity resultAuthEntity); // 게시글 입력
	public ResultAuthEntity resultAuthSelect(int id); // 게시글 검색
	public void resultAuthDelete(int id); // 게시글 삭제

	// 게시판 리스트 인덱스 페이지
	public List<ResultAuthEntity> getAuthListToIndex();

}
