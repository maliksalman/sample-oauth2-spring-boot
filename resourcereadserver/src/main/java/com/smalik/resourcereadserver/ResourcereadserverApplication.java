package com.smalik.resourcereadserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ResourcereadserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResourcereadserverApplication.class, args);
	}

}
