package com.systex.playlottery.service;

import org.springframework.stereotype.Service;


import java.util.Set;
import java.util.TreeSet;
import java.util.ArrayList;

@Service
public class LotteryService {

	public LotteryService() {
		super();
	}
	
	public ArrayList<Integer> getNumber(int groups , Set<Integer> excludes){
		Set<Integer> temp = new TreeSet<>();
		ArrayList<Integer> picknumgroup = new ArrayList<>();
		int num;
		
		for (int i=0;i<groups;i++) {
			while(temp.size()<6) {
				num = (int)(Math.random()*(49-excludes.size()))+1;    //pick random num
				if (!excludes.contains(num)) {                        //不包含一樣的號碼
					temp.add(num);
				}
			}
			for (int pn : temp) {
				picknumgroup.add(pn);
			}
			temp.clear();
		}
		return picknumgroup;
	}
}
