package com.studycafe.team.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.studycafe.team.entity.ResultAuthEntity;
import com.studycafe.team.repository.ResultAuthRepository;

@Service("resultAuthService")
public class ResultAuthServiceImpl implements ResultAuthService{
	
	@Autowired
	private ResultAuthRepository resultAuthRepository;

	@Override
	public void resultAuthInsert(ResultAuthEntity resultAuthEntity) {
		// TODO Auto-generated method stub
		resultAuthRepository.save(resultAuthEntity);
	}
	
	@Override
	public Page<ResultAuthEntity> resultAuthList(Pageable pageable) {
		// TODO Auto-generated method stub
		return resultAuthRepository.findAll(pageable);
	}

	@Override 
	@Transactional
	public Page<ResultAuthEntity> resultAuthSearchList(String keyword, Pageable pageable) {
		// TODO Auto-generated method stub
		Page<ResultAuthEntity> list = resultAuthRepository.findByresultAuthTitleContaining(pageable, keyword);
		return list;
	}

	@Override
	public ResultAuthEntity resultAuthSelect(int id) {
		// TODO Auto-generated method stub
		
		return resultAuthRepository.getById(id);
	}

	@Override
	public void resultAuthDelete(int id) {
		// TODO Auto-generated method stub
	
		resultAuthRepository.deleteById(id);
	}
}
