package com.studycafe.team.service;

import java.util.List;

import com.studycafe.team.dto.TeamMonthCountDto;
import com.studycafe.team.dto.TopTeamDto;
import com.studycafe.team.entity.TeamEntity;

public interface TeamService {
    public void teamInsert(TeamEntity teamEntity);
    public List<TeamEntity> getAllTeam();
    public List<TeamEntity> getNotApprovedTeam();
    public List<TopTeamDto> getTopTeamByPoint();
    public List<TopTeamDto> getTopTeamByApproveCount();    
    public List<TeamMonthCountDto> getNewTeamByMonth();
   

}
