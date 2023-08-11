package com.studycafe.member.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class MemberAddressEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int addressIdx;

	@ManyToOne
	@JoinColumn(name = "username")
	private MemberEntity memberEntity;

	@NotNull
	private String zipcode; // 우편번호

	@NotNull
	private String address1; // 상세주소1, 시, 도 등등

	@Column(nullable = true)
	private String address2; // 상세주소2, 나머지 주소. null 가능

	@Builder
	public MemberAddressEntity(int addressIdx, MemberEntity memberEntity, @NotNull String zipcode, @NotNull String address1, String address2) {
		
		this.addressIdx = addressIdx;
		this.memberEntity = memberEntity;
		this.zipcode = zipcode;
		this.address1 = address1;
		this.address2 = address2;
	}

}
