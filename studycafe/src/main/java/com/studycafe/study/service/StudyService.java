package com.studycafe.study.service;

import com.studycafe.study.entity.StudyEntity;

public interface StudyService {

	public void studyRegisInsert(StudyEntity studyEntity); // 위도, 경도 저장
	public StudyEntity studyRegisSelect(int id); // 위도, 경도 불러오기
}
