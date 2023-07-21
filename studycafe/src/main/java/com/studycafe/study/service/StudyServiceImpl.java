package com.studycafe.study.service;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

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
	@Transactional
	public Page<StudyEntity> studySearchList(String keyword, Pageable pageable) {
		// TODO Auto-generated method stub
		Page<StudyEntity> list = studyRepository.findBystudyTitleContaining(keyword, pageable);
		return list;
	}

	@Override
	public StudyEntity studySelect(int id) {
		// TODO Auto-generated method stub

		return studyRepository.getById(id);
	}

	@Override
	public List<StudyEntity> studySelectByMap(int lat, int lon, LocalDate date) {
		return studyRepository.findByMap(lat, lon, date);
	
	}


}
