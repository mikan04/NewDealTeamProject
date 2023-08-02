package com.studycafe.qna.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studycafe.qna.entity.QnaReEntity;
import com.studycafe.qna.repository.QnaReRepository;

@Service
public class QnaReServiceImpl implements QnaReService {
	
	@Autowired
	private QnaReRepository qnaReRe;

	@Override
	public void qnaReInsert(QnaReEntity qnaReEntity) {
		// TODO Auto-generated method stub
		
		qnaReRe.save(qnaReEntity);
		
	}

	@Override
	public List<QnaReEntity> qnaReList(long qnaNum) {
		// TODO Auto-generated method stub
		
		return qnaReRe.findByQnaEntity(qnaNum);
	}

	@Override
	public long qnaReCount(long qnaNum) {
		// TODO Auto-generated method stub
		return qnaReRe.replycount(0);
	}

	@Override
	public void qnaReDelete(long qnaNum) {
		// TODO Auto-generated method stub
		qnaReRe.deleteById(qnaNum);
	}

}
