package com.studycafe.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studycafe.member.entity.MemberEntity;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, String> {

	public boolean existsByUsername(String userName);
	public boolean existsByNickName(String nickName);
}
