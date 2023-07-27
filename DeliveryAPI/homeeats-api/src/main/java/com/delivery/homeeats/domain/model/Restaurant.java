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
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Restaurant {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private BigDecimal deliveryFee;
	
	
	@JsonIgnoreProperties("hibernateLazyInitializer")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "kitchen_id", nullable = false)
	private Kitchen kitchen;
	
	@JsonIgnore
	@Embedded
	private Adress adress;
	
	@JsonIgnore
	@CreationTimestamp
	@Column(nullable =  false, columnDefinition = "datetime")
	private LocalDateTime registerDate;
	
	@JsonIgnore
	@UpdateTimestamp
	@Column(nullable =  false, columnDefinition = "datetime")
	private LocalDateTime updateDate;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "restaurant_payment_method",
		joinColumns = @JoinColumn(name = "restaurant_id" ),
		inverseJoinColumns = @JoinColumn(name = "payment_method_id"))
	private List<PaymentMethod> paymentMethods = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "restaurant")
	private List<Item> items = new ArrayList<>();
	

	
	
	
	

}
