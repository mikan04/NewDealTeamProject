package com.studycafe.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studycafe.member.entity.MemberEntity;



public interface AdminRepository extends JpaRepository<MemberEntity, String>  {
	
}
	