package com.systex.playlottery.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.systex.playlottery.model.Member;
import com.systex.playlottery.model.MemberRepository;

import jakarta.servlet.http.HttpSession;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UserService {
	
	//BCryptPasswordEncoder Object,加密強度預設10
	private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
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
	        
	        //使用者存在 , 獲取使用者
			Member member = result.get();
				
			//使用者是否輸入正確帳密
			if (encoder.matches(password, member.getPassword())) {
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
			
			//如果使用者註冊過
			if (result.isPresent()) {
				return setError(session, "帳號已存在 , 請選擇別的帳號名稱");
			}
			
			//沒有註冊過就加入Member資料
			Member member = new Member();
	        member.setAccount(account);
	        
	        //加密password
	        String ePassword = encryptPassword(password);
	        
	        member.setPassword(ePassword);        
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
	
	//密碼加密
	public String encryptPassword(String password) {		
		//加密過後的密碼
		return encoder.encode(password);	
	}

    

}
