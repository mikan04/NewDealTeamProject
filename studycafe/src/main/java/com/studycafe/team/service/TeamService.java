package com.studycafe.team.service;

import java.util.List;

import com.studycafe.team.dto.TeamMonthCountDto;
import com.studycafe.team.dto.TopTeamDto;
import com.studycafe.team.entity.ResultAuthEntity;
import com.studycafe.team.entity.TeamEntity;

public interface TeamService {
	public TeamEntity teamInsert(TeamEntity teamEntity);
	public void approveTeam(Long teamId);
	public void disapproveTeam(Long teamId);
	public List<TeamEntity> getAllTeam();
	public List<TeamEntity> getNotApprovedTeam();
	public boolean findTeamByName(String name);
	public TeamEntity findTeamById(Long id);
	public List<TopTeamDto> getTopTeamByPoint();
	public List<TopTeamDto> getTopTeamByApproveCount();
	public List<TeamMonthCountDto> getTeamByMonth();
	public TeamEntity getMyTeam(long teamNumber);
	public void deleteTeam(long teamNumber);
	public List<TeamEntity> getRanking();
	public void updatePoint(ResultAuthEntity resultAuthEntity);

	

}
