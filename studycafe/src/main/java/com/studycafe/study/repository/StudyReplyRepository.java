package com.studycafe.study.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.studycafe.study.entity.StudyEntity;
import com.studycafe.study.entity.StudyReplyEntity;

@Repository
public interface StudyReplyRepository extends JpaRepository<StudyReplyEntity, Long> {
	@Query("SELECT COUNT(DISTINCT studyReplyRef) FROM StudyReplyEntity WHERE study_num = :id AND study_reply_depth = 0")
	public int replycount(int id);
	
	@Query("SELECT sr FROM StudyReplyEntity sr WHERE study_num = :studyNum ORDER BY study_reply_ref ASC, study_num ASC")
	public List<StudyReplyEntity> findByStudyEntity(int studyNum);
}
