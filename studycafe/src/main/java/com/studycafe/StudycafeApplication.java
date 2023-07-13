package com.studycafe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class StudycafeApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudycafeApplication.class, args);
		log.info("팀프로젝트 실행 완료");
	}

}
