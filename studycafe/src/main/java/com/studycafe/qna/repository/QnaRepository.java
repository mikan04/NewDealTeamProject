package com.studycafe.qna.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.studycafe.qna.entity.QnaEntity;
import com.studycafe.study.entity.StudyEntity;

/*@Repository*/
public interface QnaRepository  extends JpaRepository<QnaEntity, String>  {
	
	Page<QnaEntity> findByqnaTitleContaining(String keyword, Pageable pageable);

}