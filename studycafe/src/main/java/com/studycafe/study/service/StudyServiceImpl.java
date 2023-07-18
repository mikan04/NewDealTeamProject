package com.studycafe.study.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studycafe.study.entity.StudyEntity;
import com.studycafe.study.repository.StudyRepository;

@Service("studyService")
public class StudyServiceImpl implements StudyService {

	@Autowired
	private StudyRepository studyRepository;
	
	@Override
	public void studyRegisInsert(StudyEntity studyEntity) {
		// TODO Auto-generated method stub
		
		studyRepository.save(studyEntity);
	}

	@Override
	public StudyEntity studyRegisSelect(int id) {
		// TODO Auto-generated method stub
		
		return studyRepository.getById(id);
	}
}
