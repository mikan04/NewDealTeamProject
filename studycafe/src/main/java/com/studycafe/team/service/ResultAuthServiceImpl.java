package com.studycafe.team.service;

import org.springframework.stereotype.Service;

import com.studycafe.team.entity.ResultAuthEntity;
import com.studycafe.team.repository.ResultAuthRepository;

@Service("resultAuthService")
public class ResultAuthServiceImpl implements ResultAuthService{

	private ResultAuthRepository resultAuthRepository;

	@Override
	public void resultAuthInsert(ResultAuthEntity resultAuthEntity) {
		// TODO Auto-generated method stub
		resultAuthRepository.save(resultAuthEntity);
	}
}
