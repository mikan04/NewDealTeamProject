package com.studycafe.qna.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.studycafe.qna.entity.QnaEntity;

public interface QnaService {
	public Page<QnaEntity> qnaList(Pageable pageable);
	
	public Page<QnaEntity> qnaSearchList(String keyword, Pageable pageable);
	
	public void qnaRegister(QnaEntity qnaEntity); 

	public QnaEntity selectQna(Long qnaNum);
}
