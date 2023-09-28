package com.delivery.homeeats.domain.model;

import java.math.BigDecimal;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.groups.ConvertGroup;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.delivery.homeeats.Groups;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Restaurant {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue
	private Long id;
	
	
	@NotBlank(message = "The name is mandatory")
	@Column(nullable = false)
	private String name;
	
	//@DecimalMin("0")
	@PositiveOrZero
	@Column(nullable = false)
	private BigDecimal deliveryFee;
	
	
	@Valid
	@ConvertGroup(from = Default.class, to = Groups.KitchenId.class)
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "kitchen_id", nullable = false)
	private Kitchen kitchen;
	
	
	@Embedded
	private Adress adress;
	

	@CreationTimestamp
	@Column(nullable =  false, columnDefinition = "datetime")
	private LocalDateTime registerDate;
	
	
	@UpdateTimestamp
	@Column(nullable =  false, columnDefinition = "datetime")
	private LocalDateTime updateDate;
	
	
	@ManyToMany
	@JoinTable(name = "restaurant_payment_method",
		joinColumns = @JoinColumn(name = "restaurant_id" ),
		inverseJoinColumns = @JoinColumn(name = "payment_method_id"))
	private List<PaymentMethod> paymentMethods = new ArrayList<>();
	
	
	@OneToMany(mappedBy = "restaurant")
	private List<Item> items = new ArrayList<>();
	

	
	
	
	

}
