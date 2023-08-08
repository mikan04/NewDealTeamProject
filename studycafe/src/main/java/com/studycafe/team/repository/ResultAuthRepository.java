package com.studycafe.team.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.studycafe.team.entity.ResultAuthEntity;

@Repository
public interface ResultAuthRepository extends JpaRepository<ResultAuthEntity, Integer> {

	Page<ResultAuthEntity> findByresultAuthTitleContaining(Pageable pageable, String keyword); 
}
