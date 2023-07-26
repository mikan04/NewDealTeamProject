package com.studycafe.member.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studycafe.member.entity.MemberAddressEntity;
import com.studycafe.member.entity.MemberEntity;
import com.studycafe.member.repository.MemberAddressRepository;
import com.studycafe.member.repository.MemberRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberRepository memRe;

	@Autowired
	private MemberAddressRepository memberAddRe;

	@Override
	@Transactional
	public int insertMember(MemberEntity memberEntity, MemberAddressEntity memberAddressEntity) {

		try {
			MemberEntity insert1 = memRe.save(memberEntity);
			log.info("insert1 : {}", insert1);
			if (insert1 != null) {
				try {
					memberAddressEntity.setMemberEntity(memberEntity);
					MemberAddressEntity insert2 = memberAddRe.save(memberAddressEntity);
					log.info("insert2 : {}", insert2);
					if (insert2 != null) {
						return 1;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public boolean idCheck(String username) {

		try {
			MemberEntity idcheck = memRe.findById(username).get();
			if (idcheck.getUsername() != null) {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}

	@Override
	public boolean nickCheck(String nickName) {

		try {
			MemberEntity nickCheck = memRe.findByNickName(nickName);
			if (nickCheck.getUsername() != null) {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public int getNewMemberCount() {
		// TODO Auto-generated method stub
		return memRe.findNewUser();
	}

	@Override
	public List<MemberEntity> getAllMember() {
		// TODO Auto-generated method stub
		return memRe.findAll();
	}

}
