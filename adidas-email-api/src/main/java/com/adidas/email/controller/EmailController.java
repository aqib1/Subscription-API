package com.adidas.email.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.adidas.email.business.EmailBusiness;

@RestController
public class EmailController {

	@Autowired
	private EmailBusiness emailBusiness;
}
