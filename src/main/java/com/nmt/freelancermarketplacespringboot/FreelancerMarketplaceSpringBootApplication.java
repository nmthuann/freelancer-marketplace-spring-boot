package com.nmt.freelancermarketplacespringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// @EnableTransactionManagement
public class FreelancerMarketplaceSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(FreelancerMarketplaceSpringBootApplication.class, args);
		System.out.println("Hell world ....");
	}

}
