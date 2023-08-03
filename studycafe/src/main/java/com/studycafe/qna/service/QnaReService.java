package com.studycafe.qna.service;

import java.util.List;

import com.studycafe.qna.entity.QnaReEntity;

public interface QnaReService {

	public void qnaReInsert(QnaReEntity qnaReEntity);
	public List<QnaReEntity> qnaReList(long qnaNum);
	public long qnaReCount(long qnaNum);
	public void qnaReDelete(long qnaNum);
}
