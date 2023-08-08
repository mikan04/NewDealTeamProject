package com.studycafe.qna.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.studycafe.qna.entity.QnaReEntity;

@Repository
public interface QnaReRepository extends JpaRepository<QnaReEntity, Long> {
	
	@Query("SELECT COUNT(DISTINCT qnaReRef) FROM QnaReEntity WHERE qna_num = :id AND qna_reply_depth = 0")
	public int replycount(long id);
	
	@Query("SELECT sr FROM QnaReEntity sr WHERE qna_num = :qnaNum ORDER BY qna_reply_ref ASC, qna_num ASC")
	public List<QnaReEntity> findByQnaEntity(long qnaNum);

}
