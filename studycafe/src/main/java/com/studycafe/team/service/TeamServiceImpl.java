package com.studycafe.team.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studycafe.team.dto.TeamMonthCountDto;
import com.studycafe.team.dto.TopTeamDto;
import com.studycafe.team.entity.TeamEntity;
import com.studycafe.team.repository.TeamRepository;

@Service("teamService")
public class TeamServiceImpl implements TeamService {

	@Autowired
	private TeamRepository teamRepository;

	@Override
	public void teamInsert(TeamEntity teamEntity) {
		// TODO Auto-generated method stub
		teamRepository.save(teamEntity);

	}

	@Override
	public List<TeamEntity> getAllTeam() {
		// TODO Auto-generated method stub

		return teamRepository.findAll();
	}

	@Override
	public List<TopTeamDto> getTopTeamByPoint() {
		// TODO Auto-generated method stub
		return teamRepository.findTopTeam();
	}

	@Override
	public List<TopTeamDto> getTopTeamByApproveCount() {
		// TODO Auto-generated method stub
		return teamRepository.findTopApproveTeam();
	}

	@Override
	public List<TeamMonthCountDto> getNewTeamByMonth() {
		// TODO Auto-generated method stub
		return teamRepository.findApproveTeamByMonth();
	}

	@Override
	public List<TeamEntity> getNotApprovedTeam() {
		// TODO Auto-generated method stub
		return teamRepository.findNotApprovedTeam();
	}

}
