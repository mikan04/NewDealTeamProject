package com.studycafe.study.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.studycafe.study.entity.StudyEntity;

@Repository
public interface StudyRepository extends JpaRepository<StudyEntity, Integer> {

	Page<StudyEntity> findBystudyTitleContaining(String keyword, Pageable pageable);
	
	@Query(value="SELECT * FROM study_entity WHERE longitude= :longitude and latitude= :latitude and reserve_date= :date", nativeQuery = true)
	List<StudyEntity> findByMap(@Param("longitude") int longitude, @Param("latitude") int latitude , @Param("date") LocalDate date);
	
	
}
