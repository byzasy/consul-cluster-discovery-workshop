package com.turkcell.consulstudentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ConsulStudentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsulStudentServiceApplication.class, args);
	}
}
