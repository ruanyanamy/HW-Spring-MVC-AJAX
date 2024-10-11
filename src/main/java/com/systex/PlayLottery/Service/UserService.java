package com.systex.playlottery.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.systex.playlottery.model.Member;
import com.systex.playlottery.model.MemberRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class UserService {
	
	@Autowired
	private MemberRepository memberrepository;
	public String ErrorMsgs = "";
	
	//登入驗證
	public String validateMember(String account , String password ,  HttpSession session) {
		
		session.removeAttribute("ErrorMsgs");
		ErrorMsgs="";
		
		if (account == null || account.trim().isEmpty() || password == null || password.trim().isEmpty()) {
			ErrorMsgs = "請輸入帳號和密碼";
			session.setAttribute("ErrorMsgs", ErrorMsgs);
			return "error";
		}
		
		Optional<Member> result = this.memberrepository.findByAccount(account);
		
		if (result.isPresent()) {
			Member member = result.get();
			if (member.getPassword().equals(password)) {
				session.setAttribute("member", member);
				return "lottery";
			}else {
				ErrorMsgs = "帳號或密碼輸入錯誤 , 請重新輸入";
				session.setAttribute("ErrorMsgs", ErrorMsgs);
				return "error";
			}
		}else {
			ErrorMsgs = "帳號不存在 , 請先註冊";
			session.setAttribute("ErrorMsgs", ErrorMsgs);
			return "error";
		}
	
	}
	
	//註冊驗證
	public String addMember(@RequestParam("account") String account , 
			@RequestParam("password") String password , @RequestParam("name") String name  , HttpSession session) {
		

		ErrorMsgs = "";
		
		if (account == null || account.trim().isEmpty() || password == null || password.trim().isEmpty() || 
			name == null || name.trim().isEmpty()) {
			ErrorMsgs = "請輸入要註冊的資訊";
			session.setAttribute("ErrorMsgs", ErrorMsgs);
			return "error";
		}
		
		Optional<Member> result = this.memberrepository.findByAccount(account);
		
		if (!result.isPresent()) {
			Member member = new Member();
	        member.setAccount(account);
	        member.setPassword(password);
	        member.setName(name);
			this.memberrepository.save(member);
			return "success";
		} else {
			ErrorMsgs = "帳號已存在 , 請選擇別的帳號名稱";
			session.setAttribute("ErrorMsgs", ErrorMsgs);
			return "error";
		}
	}
    

}
