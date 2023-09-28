package com.delivery.homeeats.api.model.mixin;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.delivery.homeeats.domain.model.Adress;
import com.delivery.homeeats.domain.model.Item;
import com.delivery.homeeats.domain.model.Kitchen;
import com.delivery.homeeats.domain.model.PaymentMethod;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class RestaurantMixin {
	
	@JsonIgnoreProperties(value = "name", allowGetters = true)
	private Kitchen kitchen;
	
	
	@JsonIgnore
	private Adress adress;
	

	@JsonIgnore
	private LocalDateTime registerDate;
	
	
	@JsonIgnore
	private LocalDateTime updateDate;
	
	
	@JsonIgnore
	private List<PaymentMethod> paymentMethods = new ArrayList<>();
	
	
	@JsonIgnore
	private List<Item> items = new ArrayList<>();
	

}
