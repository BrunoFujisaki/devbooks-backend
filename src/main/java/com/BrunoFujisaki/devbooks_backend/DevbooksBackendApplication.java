package com.BrunoFujisaki.devbooks_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
public class DevbooksBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevbooksBackendApplication.class, args);
	}

}
