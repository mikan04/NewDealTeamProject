package com.studycafe.cs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studycafe.cs.entity.CsEntity;

@Repository
public interface CsRepository extends JpaRepository<CsEntity, Long>{

}
