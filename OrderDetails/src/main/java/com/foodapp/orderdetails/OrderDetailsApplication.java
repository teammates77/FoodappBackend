package com.foodapp.orderdetails;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient

public class OrderDetailsApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderDetailsApplication.class, args);
	}

}
