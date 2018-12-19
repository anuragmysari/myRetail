package com.myRetail.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductErrorController implements ErrorController {
	
	@RequestMapping(value="/error")
	String error(HttpServletRequest request, HttpServletResponse response) {
		return "Product does not exist";
		
	}

	public String getErrorPath() {
		return "/error";
	}

}
