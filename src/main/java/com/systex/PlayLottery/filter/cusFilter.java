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
        HttpSession session = request.getSession(false);
        
        //ajax登入驗證
        if (requestURI.endsWith("/ajaxsignin") && (request.getMethod().equals("POST")))
	        if ("application/json".equals(request.getContentType())) {
	        	
	            StringBuilder jsonBuilder = new StringBuilder();
	            try (BufferedReader reader = request.getReader()) {
	                String line;
	                while ((line = reader.readLine()) != null) {
	                    jsonBuilder.append(line);
	                }
	            }
	
	            ObjectMapper objectMapper = new ObjectMapper();
	            Member data = objectMapper.readValue(jsonBuilder.toString(), Member.class);
	            
	            String account = data.getAccount();
	            String password = data.getPassword();
	            	            
	            String res = userservice.validateMember(account, password, session);
	            
	            if (res.equals("error")) {
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
        
        //original登入驗證
        if ((requestURI.endsWith("/signin")) && request.getMethod().equals("POST")) { 	
        	
			String account = request.getParameter("account");
			String password = request.getParameter("password");
             
			String res = userservice.validateMember(account, password, session);
			 
			if (res.equals("error")) {
				response.sendRedirect(request.getContextPath() + "/error");
				return;
			}else {
				filterChain.doFilter(request, response);
				return;
			}
        }
        
        //註冊驗證
        if (requestURI.endsWith("/signup") && request.getMethod().equals("POST")) {
        	String account = request.getParameter("account");
			String password = request.getParameter("password");
			String name = request.getParameter("name");
			
			String res = userservice.addMember(account, password, name, session);
			
			if (res.equals("error")) {
				response.sendRedirect(request.getContextPath() + "/error");
				return;
			}else {
				filterChain.doFilter(request, response);
				return;
			}
        }
        
        
        //不攔h2-console , sigin-get , signup-get , error page
        if (requestURI.startsWith(request.getContextPath() + "/h2-console") ||
            requestURI.equals(request.getContextPath() + "/signin") ||
            requestURI.equals(request.getContextPath() + "/ajaxsignin") ||
            requestURI.equals(request.getContextPath() + "/signup") ||
            requestURI.equals(request.getContextPath() + "/error")) {
            
            filterChain.doFilter(request, response);
            return;
        }
        
       
        if (session == null || session.getAttribute("member") == null) {
            response.sendRedirect(request.getContextPath() + "/signin");
        } else {
            filterChain.doFilter(request, response);
        }
    }


}
