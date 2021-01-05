package com.aeterna.game;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class GameApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(GameApplication.class, args);
		System.out.println("Started......");
	}

}
