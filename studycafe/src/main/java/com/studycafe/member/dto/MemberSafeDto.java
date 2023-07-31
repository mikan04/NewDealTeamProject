package com.studycafe.member.dto;

import java.sql.Timestamp;

import com.studycafe.member.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberSafeDto {
	
	private String username;
	private String name;
	private String nickName;
    private String email;
    private Role role;
    private Long teamNumber;
    private Timestamp createdAt; 
  
}
