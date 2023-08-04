package com.studycafe.member.oauth2.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studycafe.member.oauth2.entity.Oauth2UserEntity;

@Repository
public interface Oauth2UserRepository extends JpaRepository<Oauth2UserEntity, Long>{
	
	Optional<Oauth2UserEntity> findByOauth2Id(String email);
}
