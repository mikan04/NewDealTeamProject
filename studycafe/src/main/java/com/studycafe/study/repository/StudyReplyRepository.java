package com.studycafe.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studycafe.study.entity.StudyReplyEntity;

@Repository
public interface StudyReplyRepository extends JpaRepository<StudyReplyEntity, Integer> {

}
