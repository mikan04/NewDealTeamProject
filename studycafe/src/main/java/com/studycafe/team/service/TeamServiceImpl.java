package com.studycafe.team.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.studycafe.team.entity.TeamEntity;
import com.studycafe.team.repository.TeamRepository;

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
	public List<TeamEntity> getTopTeamByPoint() {
		// TODO Auto-generated method stub
		return teamRepository.findTopTeam();
	}

	@Override
	public List<TeamEntity> getTopTeamByApproveCount() {
		// TODO Auto-generated method stub
		return teamRepository.findTopApproveTeam();
	}

}
