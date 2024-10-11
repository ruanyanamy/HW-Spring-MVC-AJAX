package com.systex.playlottery.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.systex.playlottery.service.LotteryService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class LotteryController {
	
	public LinkedList<String> ErrorMsgs = new LinkedList<>();
	
	//validate lottery param and get lottery num
	@PostMapping("/validandgenlotterynum")
	public String validAndgenLottery(@RequestParam("group") String group,
	                                  @RequestParam("notpicknum") String[] notpickNumber,
	                                  Model model , HttpServletRequest request) {
		ErrorMsgs.clear();
	    LotteryService lotteryservice = new LotteryService();
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
                //request.setAttribute("error", ErrorMsgs);
            	model.addAttribute("ErrorMsgs", ErrorMsgs);
    			return "error";
            }
            
            int groups = Integer.parseInt(group);
            ArrayList<Integer> pickLotteryNumber = lotteryservice.getNumber(groups, excludes);

            model.addAttribute("pickLotteryNumber", pickLotteryNumber);
            model.addAttribute("excludes", excludes);
            return "result";
            
	        } catch (Exception e) {
	            ErrorMsgs.add("發生錯誤：" + e.getMessage());
	            model.addAttribute("ErrorMsgs", ErrorMsgs);
				return "error";
	        }
	    }

}
