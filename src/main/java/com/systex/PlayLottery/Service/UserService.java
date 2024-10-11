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
	
	//登入驗證
	public boolean validateMember(String account , String password ,  HttpSession session) {
		
		//如果使用者沒有輸入帳密
		if (account == null || account.trim().isEmpty() || password == null || password.trim().isEmpty()) {
			return setError(session, "請輸入帳號和密碼");
		}
		
		//嘗試尋找使用者
		try{
			Optional<Member> result = this.memberrepository.findByAccount(account);		
			
			// 如果帳號不存在
	        if (!result.isPresent()) {
	            return setError(session, "帳號不存在 , 請先註冊");
	        }
	        
			Member member = result.get();
				
			//使用者是否輸入正確帳密
			if (member.getPassword().equals(password)) {
				session.setAttribute("member", member);
				return true;
			}else {
				return setError(session, "帳號或密碼輸入錯誤 , 請重新輸入");
			}
			
		}catch (Exception e) {
			return setError(session, "系統錯誤，請稍後再試");	
		}
	}
	
	
	//註冊驗證
	public boolean addMember(@RequestParam("account") String account , 
			@RequestParam("password") String password , @RequestParam("name") String name  , HttpSession session) {
		
		//如果使用者沒有輸入註冊資訊
		if (account == null || account.trim().isEmpty() || password == null || password.trim().isEmpty() || 
			name == null || name.trim().isEmpty()) {
			return setError(session, "請輸入要註冊的資訊");
		}
		
		//使用者是否註冊過
		try {
			Optional<Member> result = this.memberrepository.findByAccount(account);
			
			if (result.isPresent()) {
				return setError(session, "帳號已存在 , 請選擇別的帳號名稱");
			}
			
			//沒有註冊過就加入Member
			Member member = new Member();
	        member.setAccount(account);
	        member.setPassword(password);
	        member.setName(name);
			this.memberrepository.save(member);
			return true;
			
		}catch (Exception e) {
			return setError(session, "系統錯誤，請稍後再試");
		}
	}
	
	//錯誤設定
	public boolean setError(HttpSession session , String errmsg) {
		session.removeAttribute("ErrorMsgs");
		session.setAttribute("ErrorMsgs" , errmsg);
		return false;
	}
    

}
