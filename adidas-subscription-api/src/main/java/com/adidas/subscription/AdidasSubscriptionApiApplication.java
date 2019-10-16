package com.adidas.subscription;

import static com.adidas.subscription.constant.Consts.SUBSCRIPTION_COMPONENT_SCAN_PATH;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@ComponentScan(SUBSCRIPTION_COMPONENT_SCAN_PATH)
public class AdidasSubscriptionApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdidasSubscriptionApiApplication.class, args);
	}
}
