package com.studycafe.team.service;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studycafe.member.entity.MemberEntity;
import com.studycafe.member.repository.MemberRepository;
import com.studycafe.team.dto.TeamMonthCountDto;
import com.studycafe.team.dto.TopTeamDto;
import com.studycafe.team.entity.ResultAuthEntity;
import com.studycafe.team.entity.TeamEntity;
import com.studycafe.team.repository.ResultAuthRepository;
import com.studycafe.team.repository.TeamRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("teamService")
public class TeamServiceImpl implements TeamService {

	@Autowired
	private TeamRepository teamRepository;

	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private ResultAuthRepository resultAuthRepository;

	@Override
	public TeamEntity teamInsert(TeamEntity teamEntity) {
		// TODO Auto-generated method stub
		return teamRepository.save(teamEntity);

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
	public List<TeamMonthCountDto> getTeamByMonth() {
		// TODO Auto-generated method stub
		return teamRepository.findTeamByMonth();
	}

	@Override
	public List<TeamEntity> getNotApprovedTeam() {
		// TODO Auto-generated method stub
		return teamRepository.findNotApprovedTeam();
	}

	@Override
	public void approveTeam(Long teamId) {
		// teamRepository.approveTeam(teamId);
		TeamEntity teamEntity = teamRepository.findById(teamId).orElseThrow(IllegalArgumentException::new);
		teamEntity.setApproveDate(LocalDate.now());
		teamRepository.saveAndFlush(teamEntity);

	}

	@Override
	public void disapproveTeam(Long teamId) {
		// teamRepository.disapproveTeam(teamId);
		TeamEntity teamEntity = teamRepository.findById(teamId).orElseThrow(IllegalArgumentException::new);
		teamEntity.setApproveDate(null);
		teamRepository.saveAndFlush(teamEntity);

	}

	@Override
	public boolean findTeamByName(String name) {
		// TODO Auto-generated method stub
		return teamRepository.existsByTeamName(name);
	}

	@Override
	public TeamEntity findTeamById(Long id) {
		// TODO Auto-generated method stub
		return teamRepository.findById(id).orElseThrow(IllegalArgumentException::new);
	}

	@Override
	public TeamEntity getMyTeam(long teamNumber) {
		TeamEntity team = teamRepository.findByTeamNumber(teamNumber);
		return team;
	}

	@Override
	public void deleteTeam(long teamNumber) {
		teamRepository.deleteById(teamNumber);

	}

	@Override
	public List<TeamEntity> getRanking() {

		return teamRepository.findTop5ByOrderByPointDesc();
	}

	@Override
	@Transactional
	public void updatePoint(ResultAuthEntity resultAuthEntity) {
		
		String nickName = resultAuthEntity.getResultAuthWriter(); // 글쓴이
		
		MemberEntity member = memberRepository.findByNickName(nickName); // 글쓴이의 정보를 찾음
		
		String username = member.getUsername(); // 글쓴이의 아이디 추출
		
		TeamEntity team = teamRepository.findByTeamHead(username); // 팀의 정보를 찾음
		
		int newPoint = resultAuthEntity.getResultAuthScore();
		
		int oldPoint = team.getPoint();
		
		team.setPoint(oldPoint + newPoint);
		
		teamRepository.save(team);
		
	}

}
