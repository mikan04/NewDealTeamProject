package com.studycafe.team.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.studycafe.team.entity.TeamEntity;

public interface TeamRepository extends JpaRepository<TeamEntity, Long>  {

	@Query(value="SELECT * FROM team_entity ORDER BY point DESC LIMIT 6", nativeQuery = true)
	List<TeamEntity> findTopTeam();
	
	@Query(value="SELECT * FROM team_entity ORDER BY approveCount DESC LIMIT 6", nativeQuery = true)
	List<TeamEntity> findTopApproveTeam();

}
