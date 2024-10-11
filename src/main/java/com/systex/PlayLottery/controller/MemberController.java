package com.systex.playlottery.controller;

import java.util.ArrayList;


import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.systex.playlottery.model.Member;
import com.systex.playlottery.model.MemberRepository;
import com.systex.playlottery.service.LotteryService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class MemberController {
	
	//signup
	@GetMapping("/signup")
	public ModelAndView signupMember() {
		return new ModelAndView("signup" , "signup" , new Member());
	}
	
	//signin
	@GetMapping("/signin")
	public ModelAndView signinMember() {
		return new ModelAndView("signin" , "command" , new Member());
	}
	
	//ajaxsignin
	@GetMapping("/ajaxsignin")
	public String AJAXsigninMember() {
		return "ajaxsignin";
	}
	
	//lottery page
	@GetMapping("/lottery")
	public String lottery() {
		return "lottery";
	}
	
	//signout
	@GetMapping("/signout")
	public String signoutMember(HttpSession session) {
		if (session != null) {
			session.invalidate();
		}
		return "redirect:/signin";
	}
	
	@PostMapping("/signup")
	public String addMember() {
		return "signupsuccess";
	}
	
	//validate member success return lottery page
	@PostMapping("/signin")
	public String validateMember() {
		return "redirect:/lottery";
	}
	
}
        

