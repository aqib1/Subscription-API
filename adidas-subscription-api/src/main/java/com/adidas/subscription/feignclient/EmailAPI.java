package com.adidas.subscription.feignclient;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.adidas.subscription.constant.Consts;
import com.example.model.EmailRequest;
import com.example.model.EmailResponse;

@FeignClient(name = Consts.URL_EMAIL_SENDER)
@RibbonClient(name = Consts.URL_EMAIL_SENDER)
public interface EmailAPI {
	@PostMapping
	EmailResponse sendEmail(@RequestBody EmailRequest request);
}
