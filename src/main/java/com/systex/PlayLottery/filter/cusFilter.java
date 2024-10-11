package com.systex.playlottery.filter;

import java.io.BufferedReader;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.systex.playlottery.model.Member;
import com.systex.playlottery.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@Component
public class CusFilter extends OncePerRequestFilter{
	
	@Autowired
	UserService userservice;
	
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, 
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        
        //不建立新的session
        HttpSession session = request.getSession(false);  
        
        //ajax登入驗證
        if (requestURI.endsWith("/ajaxsignin") && (request.getMethod().equals("POST")))
	        if ("application/json".equals(request.getContentType())) {
	        	
	            StringBuilder jsonBuilder = new StringBuilder();
	            
	            //讀取JSON Data
	            try (BufferedReader reader = request.getReader()) {
	                String line;
	                while ((line = reader.readLine()) != null) {
	                    jsonBuilder.append(line);
	                }
	            }
	            
	            ObjectMapper objectMapper = new ObjectMapper();
	            
	            //JSON Data to Java Object
	            Member data = objectMapper.readValue(jsonBuilder.toString(), Member.class);
	            
	            //獲取要驗證的資料
	            String account = data.getAccount();
	            String password = data.getPassword();
	            
	            //驗證
	            boolean res = userservice.validateMember(account, password, session);
	            
	            //決定去往成功或失敗(夾帶資料)
	            if (!res) {
	            	response.setContentType("application/json;charset=UTF-8");
					String errmsg = (String) session.getAttribute("ErrorMsgs");
					response.getWriter().write("{\"status\":\"error\", \"message\":\"" + errmsg + "\"}");
					return;
	            }else {
	                response.setContentType("application/json;charset=UTF-8");
	                response.getWriter().write("{\"status\":\"success\"}");
					return;
				}
	        }
        
        //原本的登入驗證
        if ((requestURI.endsWith("/signin")) && request.getMethod().equals("POST")) { 	
        	validate_handle(request, response, session, filterChain);
        	return;
        }
        
        //註冊驗證
        if (requestURI.endsWith("/signup") && request.getMethod().equals("POST")) {
        	validate_handle(request, response, session, filterChain);
        	return;
        }
              
        //不攔h2-console , sigin-get , signup-get , error page
        if (requestURI.contains("/h2-console") || requestURI.endsWith("/signin") || requestURI.endsWith("/ajaxsignin") ||
            requestURI.endsWith("/signup") || requestURI.endsWith("/error")) {           
            filterChain.doFilter(request, response);
            return;
        }
        
        //攔截沒登入的使用者
        if (session == null || session.getAttribute("member") == null) {
            response.sendRedirect(request.getContextPath() + "/signin");
            return;
        } else {
            filterChain.doFilter(request, response);
        }
    }
   
    //驗證處理
    public void validate_handle(HttpServletRequest request, HttpServletResponse response, 
            					HttpSession session ,FilterChain filterChain) throws ServletException, IOException {
    	
    	//獲取要驗證的資料
    	String account = request.getParameter("account");
    	String password = request.getParameter("password");
    	String name = request.getParameter("name");
    	boolean res;
    	
    	//驗證
    	if (name != null) {
    		res = userservice.addMember(account, password, name, session);
    	}else {
    		res = userservice.validateMember(account, password, session);
    	}
    	
    	//決定去往成功或失敗
    	if (!res) {
    		response.sendRedirect(request.getContextPath() + "/error");
			return;
    	}else {
			filterChain.doFilter(request, response);
			return;
		}
    }

}
