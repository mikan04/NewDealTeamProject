package com.studycafe.qna.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.studycafe.member.entity.MemberEntity;
import com.studycafe.qna.entity.QnaEntity;
import com.studycafe.qna.repository.QnaRepository;

@Service
public class QnaServiceImpl implements QnaService{
	
	@Autowired
	private QnaRepository qnaRe;

	@Override
	public Page<QnaEntity> qnaList(Pageable pageable) {
		// TODO Auto-generated method stub
		return qnaRe.findAll(pageable);
	}

	@Override
	public Page<QnaEntity> qnaSearchList(String keyword, Pageable pageable) {
		// TODO Auto-generated method stub
		
		Page<QnaEntity> list = qnaRe.findByqnaTitleContaining(keyword, pageable);
		
		return list;
	}

	@Override
	public void qnaRegister(QnaEntity qnaEntity) {
		// TODO Auto-generated method stub
		
		qnaRe.save(qnaEntity);
	
	}

//	@Override
//	public MemberEntity getMember(String nickName) {
//		// TODO Auto-generated method stub
//		
//		return null;
//	}
	
	
	
}
