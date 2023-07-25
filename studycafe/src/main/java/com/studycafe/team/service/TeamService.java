package com.studycafe.team.service;

import java.util.List;

import com.studycafe.team.entity.TeamEntity;

public interface TeamService {
    public void teamInsert(TeamEntity teamEntity);
    public List<TeamEntity> getAllTeam();
    public List<TeamEntity> getTopTeamByPoint();
    public List<TeamEntity> getTopTeamByApproveCount();    

}
