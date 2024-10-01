package com.systex.PlayLottery.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.systex.PlayLottery.model.Member;
import com.systex.PlayLottery.model.MemberRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import com.systex.PlayLottery.Service.LotteryService;

@Controller
public class LotteryMember {
	
	@Autowired
	MemberRepository memberrepository;
	
	//signup
	@GetMapping("/signup")
	public ModelAndView signupMember() {
		return new ModelAndView("Signup" , "signup" , new Member());
	}
	
	//signin
	@GetMapping("/signin")
	public ModelAndView signinMember() {
		return new ModelAndView("Signin" , "command" , new Member());
	}
	
	//signout
	@GetMapping("/signout")
	public String signoutMember(HttpSession session) {
		if (session != null) {
			session.invalidate();
		}
		return "redirect:/index.jsp";
	}
	
	//addmember
	@PostMapping("/signup")
	public String addMember(@ModelAttribute("signup") Member member , Model model 
							, HttpSession session , @RequestParam("account") String account) {
		Optional<Member> result = this.memberrepository.findByAccount(account);
		if (!result.isPresent()) {
			this.memberrepository.save(member);
			session.setAttribute("member" , member);
			return "redirect:/signin";
		}else {
			return "redirect:/signup";
		}
		
	}
	
	//validate member
	@PostMapping("/signin")
	public String validateMember(@RequestParam("account") String account , 
						         @RequestParam("password") String password 
						         , Model model , HttpSession session) {
		Optional<Member> result = this.memberrepository.findByAccount(account);
		if (result.isPresent()) {
			Member member = result.get();
			if (member.getPassword().equals(password)) {
				session.setAttribute("member", member);
	            return "Lottery";
			}else {
				return "redirect:/signin";
			}
		}else {
			return "redirect:/signup";
		}
		
	}
	
	//lottery page
	@GetMapping("/lottery")
	public String lottery() {
		return "Lottery";
	}
	
	//validate lottery param and get lottery num
	@PostMapping("/validandgenlotterynum")
	public String validAndgenLottery(@RequestParam("group") String group,
	                                  @RequestParam("notpicknum") String[] notpickNumber,
	                                  Model model , HttpServletRequest request) {

	    LotteryService lotteryservice = new LotteryService();
	    LinkedList<String> ErrorMsgs = new LinkedList<>();
        Set<Integer> excludes = new HashSet<>();
        
        try {
            // validate group param
            if (group == null || group.trim().isEmpty()) {
                ErrorMsgs.add("請輸入需要產生的樂透組數");
            } else {
                try {
                    int groups = Integer.parseInt(group);

                    if (groups <= 0) {
                        ErrorMsgs.add("請輸入大於0的樂透產生組數");
                    } else {
                        model.addAttribute("group", groups);
                    }
                } catch (NumberFormatException ex) {
                    ErrorMsgs.add("請輸入有效的樂透產生組數");
                }
            }

            // validate notpickNumber param
            if (notpickNumber == null || notpickNumber.length == 0
                    || (notpickNumber.length == 1 && notpickNumber[0].trim().isEmpty())) {
                ErrorMsgs.add("請輸入至少一個不想包含的樂透號碼");
            } else {
                for (String e1 : notpickNumber) {
                    String[] e = e1.trim().split("\\s+");
                    for (String e2 : e) {
                        try {
                            int num = Integer.parseInt(e2);
                            if (num > 0) {
                                excludes.add(num);
                            } else {
                                ErrorMsgs.add("請輸入大於0的數字");
                            }
                        } catch (NumberFormatException ex) {
                            ErrorMsgs.add("請輸入不想包含的【數字】");
                        }
                    }
                }
            }

            //check error is or not empty
            if (!ErrorMsgs.isEmpty()) {
                request.setAttribute("error", ErrorMsgs);
                return "Lottery";
            }
            
            int groups = Integer.parseInt(group);
            ArrayList<Integer> pickLotteryNumber = lotteryservice.getNumber(groups, excludes);

            model.addAttribute("pickLotteryNumber", pickLotteryNumber);
            model.addAttribute("excludes", excludes);
            return "Result";
            
	        } catch (Exception e) {
	            ErrorMsgs.add("發生錯誤：" + e.getMessage());
	            request.setAttribute("error", ErrorMsgs);
	            return "Lottery";
	        }
	    }
}
        

