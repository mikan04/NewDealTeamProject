package com.studycafe.team.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.studycafe.team.dto.TeamMonthCountDto;
import com.studycafe.team.dto.TopTeamDto;
import com.studycafe.team.entity.TeamEntity;

public interface TeamRepository extends JpaRepository<TeamEntity, Long> {

	@Query(value = "SELECT team_name as teamName, point FROM team_entity ORDER BY point DESC LIMIT 6", nativeQuery = true)
	List<TopTeamDto> findTopTeam();

	@Query(value = "SELECT team_name as teamName, approve_count as point FROM team_entity ORDER BY approve_count DESC LIMIT 6", nativeQuery = true)
	List<TopTeamDto> findTopApproveTeam();
	
	@Query(value = "SELECT * FROM team_entity WHERE approve_date IS NULL", nativeQuery = true)
	List<TeamEntity> findNotApprovedTeam();

	@Query(value = "SELECT MONTHNAME(create_date) as month, IF(ISNULL(approve_date), 'PENDING', 'APPROVED') as approve, COUNT(*) as count "
			+ "FROM team_entity WHERE create_date >= NOW()- INTERVAL 1 YEAR "
			+ "GROUP BY MONTH(create_date), approve "
			+ "ORDER BY create_date", nativeQuery = true)
	List<TeamMonthCountDto> findTeamByMonth();

	public boolean existsByTeamName(String teamName);

	public TeamEntity findByTeamNumber(long TeamNumber);
	
	List<TeamEntity> findTop5ByOrderByPointDesc();

	TeamEntity findByTeamHead(String username);

}
