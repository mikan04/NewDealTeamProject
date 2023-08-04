package com.studycafe.member.oauth2.dto;

import java.io.Serializable;

import com.studycafe.member.entity.MemberEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@NoArgsConstructor
public class SessionUser implements Serializable{

	/**
	 * @serial SessionUser
	 */
	private static final long serialVersionUID = -1519444696997111509L;
	
	private String name;
	
	private String email;

	
	public SessionUser(MemberEntity oauth2) {
		
		log.info("SessionUser 들어온 값 : {}", oauth2.getUsername(), oauth2.getName());
	
		this.name = oauth2.getName();
		this.email = oauth2.getUsername();
	}
	
	
}
