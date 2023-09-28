package com.delivery.homeeats.api.model.mixin;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.delivery.homeeats.Groups;
import com.delivery.homeeats.domain.model.Adress;
import com.delivery.homeeats.domain.model.Item;
import com.delivery.homeeats.domain.model.Kitchen;
import com.delivery.homeeats.domain.model.PaymentMethod;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

public class restaurantMixin {
	
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
