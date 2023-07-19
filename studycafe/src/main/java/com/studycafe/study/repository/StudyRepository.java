package com.studycafe.study.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studycafe.study.entity.StudyEntity;

@Repository
public interface StudyRepository extends JpaRepository<StudyEntity, Integer> {

	Page<StudyEntity> findByTitleContaining(String searchKeyword, Pageable pageable);
}
