package com.studycafe.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studycafe.member.entity.MemberEntity;

public interface KakaoRepository  extends JpaRepository<MemberEntity, Long> {
    Optional<MemberEntity> findByUsername(String username);
    Optional<MemberEntity> findByKakaoId(Long kakaoId);
}
