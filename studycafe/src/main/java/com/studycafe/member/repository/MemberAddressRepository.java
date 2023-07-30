package com.studycafe.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studycafe.member.entity.MemberAddressEntity;

@Repository
public interface MemberAddressRepository extends JpaRepository<MemberAddressEntity, Integer> {

	MemberAddressEntity findByMemberEntity_Username(String username);

}
