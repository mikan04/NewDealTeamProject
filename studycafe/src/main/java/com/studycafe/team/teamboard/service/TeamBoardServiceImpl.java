package com.studycafe.team.teamboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.studycafe.team.teamboard.entity.TeamBoardEntity;
import com.studycafe.team.teamboard.repository.TeamBoardRepository;
import com.studycafe.utils.file.service.S3Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TeamBoardServiceImpl implements TeamBoardService {
	
	@Autowired
	private TeamBoardRepository teamBoardRepository;
	
	@Autowired
	private S3Service s3Service;

	// 업로드 위치에 따른 S3 경로를 나타내줄 변수
	private final String path = "team";

	// 팀 게시글 등록
	@Override
	public void teamBoardRegis(MultipartFile file, TeamBoardEntity teamBoard) {
		
		log.info("팀 등록 글 작성로직 실행");

		// 업로드가 곧 파일 경로.
		String fileUrl =  s3Service.saveFile(file, path);
		
		teamBoard.setFileUrl(fileUrl);
		
		log.info("파일 업로드 완료");
		// 로그에 메소드 자체 넣지 말기
		log.info("경로 : {}", fileUrl);
		
		teamBoardRepository.save(teamBoard);
		
	}

}
