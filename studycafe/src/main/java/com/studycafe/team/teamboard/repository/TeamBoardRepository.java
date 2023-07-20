package com.studycafe.team.teamboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studycafe.team.teamboard.entity.TeamBoardEntity;

public interface TeamBoardRepository extends JpaRepository<TeamBoardEntity, Long>{

}
