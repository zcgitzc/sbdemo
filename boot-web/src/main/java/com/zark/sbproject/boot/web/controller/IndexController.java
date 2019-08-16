package com.zark.sbproject.boot.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

	@RequestMapping("hello")
	public String hello() {
		return "Hello world!!";
	}
}
