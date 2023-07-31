package com.studycafe.member.dto;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

import com.studycafe.member.entity.MemberEntity;
import com.studycafe.member.entity.Role;

@Component
public class MemberMapper {
	public MemberSafeDto memberSafeDto(MemberEntity memberEntity) {

		String username = memberEntity.getUsername();
		String name = memberEntity.getName();
		String nickName = memberEntity.getNickName();
		String email = memberEntity.getEmail();
		Role role = memberEntity.getRole();
		Long teamNumber;
		if (memberEntity.getTeamNumber() != null) {
			teamNumber = memberEntity.getTeamNumber().getTeamNumber();
		} else {
			teamNumber = 0L;
		}
		Timestamp createdAt = memberEntity.getCreatedAt();
		return new MemberSafeDto(username, name, nickName, email, role, teamNumber, createdAt);
	
	}

}
