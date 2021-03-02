package com.cos.myiocdi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


//Component(용도 없음), Configuration(설정파일), Service(서비스), Repository(레파지토리), Bean

@RestController
public class PostController {
	
	@Autowired
	private Robot robot; //DI
	
	@GetMapping("/")
	public String home() {
		return "home"+robot.getName();
	}
}