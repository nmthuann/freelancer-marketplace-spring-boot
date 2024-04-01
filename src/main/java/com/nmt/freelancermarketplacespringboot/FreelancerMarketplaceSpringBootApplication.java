package com.nmt.freelancermarketplacespringboot;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
// @Log4j2
// @EnableTransactionManagement
//
public class FreelancerMarketplaceSpringBootApplication {
	public static void main(String[] args) {
		SpringApplication.run(FreelancerMarketplaceSpringBootApplication.class, args);
		System.out.println("Hell world ....");
	}

}
