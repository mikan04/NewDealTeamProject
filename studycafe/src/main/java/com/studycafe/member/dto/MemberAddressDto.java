package com.studycafe.member.dto;

import com.studycafe.member.entity.MemberAddressEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class MemberAddressDto {

	private int addressIdx;
	private String zipcode;
	private String address1;
	private String address2;

	public MemberAddressEntity toAddressEntity(MemberAddressEntity addressEntity) {

		MemberAddressEntity memberAddress = MemberAddressEntity.builder()
				
				.memberEntity(addressEntity.getMemberEntity())
				
				.zipcode(addressEntity.getZipcode())
				.address1(addressEntity.getAddress1())
				.address2(addressEntity.getAddress2())
				.build();

		return memberAddress;
	}
	
	@Builder
	public MemberAddressDto(int addressIdx, String zipcode, String address1, String address2) {
		
		this.addressIdx = addressIdx;
		this.zipcode = zipcode;
		this.address1 = address1;
		this.address2 = address2;
	}
	
	
}
