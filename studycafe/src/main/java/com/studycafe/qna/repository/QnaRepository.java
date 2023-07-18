package com.studycafe.qna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studycafe.qna.entity.QnaEntity;

@Repository
public interface QnaRepository extends JpaRepository<QnaEntity, String> {

}