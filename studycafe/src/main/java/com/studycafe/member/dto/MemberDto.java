package com.studycafe.member.dto;

import com.studycafe.member.entity.MemberAddressEntity;
import com.studycafe.member.entity.MemberEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class MemberDto {

	private String username; // memberentity, addressentity 공통필드
	private String password;
	private String email;
	private String nickName;
	private String name;

	private int addressIdx;
	private String zipcode;
	private String address1;
	private String address2;

	// 객체 dto 변환작업
	public MemberEntity toMemberEntity(MemberDto memberDto) {

		MemberEntity member = MemberEntity.builder()
				.username(memberDto.getUsername())
				.password(memberDto.getPassword())
				.email(memberDto.getEmail())
				.nickName(memberDto.getNickName())
				.name(memberDto.getName())
				.build();

		return member;

	}

	// 주소 dto
	public MemberAddressEntity toMemberAddressEntity(MemberAddressDto addressDto, MemberEntity memberEntity) {

		MemberAddressEntity addressEntity = MemberAddressEntity.builder()
				.memberEntity(memberEntity)
				.zipcode(addressDto.getZipcode())
				.address1(addressDto.getAddress1())
				.address2(addressDto.getAddress2())
				.build();

		return addressEntity;
	}

	@Builder
	public MemberDto(String username, String password, String email, String nickName, String name, int addressIdx, String zipcode, String address1,
			String address2) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.nickName = nickName;
		this.name = name;
		this.addressIdx = addressIdx;
		this.zipcode = zipcode;
		this.address1 = address1;
		this.address2 = address2;
	}

}
