package com.logicalenigma.springboot.servicehostinfo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HostInfoController {

	@RequestMapping("/")
	public String getInfo() {

		return "hello world";
	}

}
