package com.lorenzi.logistic.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lorenzi.logistic.domain.model.Delivery;
import com.lorenzi.logistic.domain.service.DeliveryRequestService;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {

	@Autowired
	private DeliveryRequestService requestService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Delivery request(@RequestBody Delivery delivery) {
		return requestService.request(delivery);
		
	}
}
