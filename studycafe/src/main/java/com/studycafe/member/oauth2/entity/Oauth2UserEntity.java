package com.studycafe.member.oauth2.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.studycafe.member.entity.Role;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Oauth2UserEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String oauth2Id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public Oauth2UserEntity(String name, String email, Role role){
        this.name = name;
        this.oauth2Id = email;
        this.role = role;
    }

    public Oauth2UserEntity oauth2UserUpdate(String name, String oauth2Id){
        this.name = name;
        this.oauth2Id = oauth2Id;

        return this;
    }
}
	
