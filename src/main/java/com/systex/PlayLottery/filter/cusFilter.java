//package com.systex.PlayLottery.filter;
//
//import java.io.IOException;
//
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//
//@Component
//public class cusFilter extends OncePerRequestFilter{
//
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		
//		String requestUri = request.getRequestURI();
//	    System.out.println("Request URI: " + requestUri); 
//	    
//	    if (requestUri.equals(request.getContextPath() + "/h2-console") ||
//            requestUri.equals(request.getContextPath() + "/h2-console/") ||
//            requestUri.startsWith(request.getContextPath() + "/h2-console/")) {
//
//            filterChain.doFilter(request, response);
//        } else if (requestUri.equals(request.getContextPath() + "/signin") ||
//                   requestUri.equals(request.getContextPath() + "/signup")) {
//            filterChain.doFilter(request, response);
//        } else {
//            response.sendRedirect(request.getContextPath() + "/signin");
//        }
//
//	}
//
//}
