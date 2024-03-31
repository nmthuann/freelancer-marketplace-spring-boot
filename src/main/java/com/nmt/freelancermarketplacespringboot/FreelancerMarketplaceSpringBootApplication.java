package com.nmt.freelancermarketplacespringboot;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@Log4j2
// @EnableTransactionManagement
public class FreelancerMarketplaceSpringBootApplication {

	// private static final Logger LOGGER = (Logger) LogManager.getLogger(FreelancerMarketplaceSpringBootApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(FreelancerMarketplaceSpringBootApplication.class, args);

		System.out.println("Hell world ....");
	}

}
