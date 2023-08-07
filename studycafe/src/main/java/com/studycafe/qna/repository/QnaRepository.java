package com.studycafe.qna.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.studycafe.qna.entity.QnaEntity;
import com.studycafe.study.entity.StudyEntity;

/*@Repository*/
public interface QnaRepository  extends JpaRepository<QnaEntity, Long>  {
	
	Page<QnaEntity> findByqnaTitleContaining(String keyword, Pageable pageable);

	Page<QnaEntity> findByisDeleted(Pageable pageable, int isDeleted);
	
//	이게 되나?
	Page<QnaEntity> findByIsDeletedEquals(int isDeletedValue, Pageable pageable);
}