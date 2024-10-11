package com.systex.playlottery.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GeneralErrorController implements ErrorController {
	@GetMapping("/error")
	public String errorreport() {
		return "error";
	}
}
