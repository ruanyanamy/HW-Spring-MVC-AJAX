package com.systex.playlottery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

//@SpringBootApplication(exclude = {ErrorMvcAutoConfiguration.class})
@SpringBootApplication
public class PlayLotteryApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlayLotteryApplication.class, args);
	}

}
