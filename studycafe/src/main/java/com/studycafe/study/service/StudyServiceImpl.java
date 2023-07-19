package com.studycafe.study.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.studycafe.study.entity.StudyEntity;
import com.studycafe.study.repository.StudyRepository;

@Service("studyService")
public class StudyServiceImpl implements StudyService {

	@Autowired
	private StudyRepository studyRepository;
	
	@Override
	public void studyInsert(StudyEntity studyEntity) {
		// TODO Auto-generated method stub
		
		studyRepository.save(studyEntity);
	}

	@Override
	public Page<StudyEntity> studyList(Pageable pageable) {
		// TODO Auto-generated method stub
		return studyRepository.findAll(pageable);
	}
	
	@Override
	public Page<StudyEntity> studySearchList(String searchKeyword, Pageable pageable) {
		// TODO Auto-generated method stub
		return studyRepository.findByTitleContaining(searchKeyword, pageable);
	}

	@Override
	public StudyEntity studySelect(int id) {
		// TODO Auto-generated method stub
		
		return studyRepository.getById(id);
	}
	
	
	
}
