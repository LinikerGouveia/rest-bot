package br.com.skytef.rest_robot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {
	
	@RequestMapping("/apiHello")
	public String hello() {
		return "Hello World";
	}

}
