package com.adidas.email;

import static com.adidas.email.constant.Consts.SUBSCRIPTION_COMPONENT_SCAN_PATH;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan(SUBSCRIPTION_COMPONENT_SCAN_PATH)
public class AdidasEmailApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdidasEmailApiApplication.class, args);
	}

}
