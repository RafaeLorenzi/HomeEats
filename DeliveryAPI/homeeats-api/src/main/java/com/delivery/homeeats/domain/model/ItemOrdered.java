package com.delivery.homeeats.domain.model;


import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class ItemOrdered {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private BigDecimal unitPrice;
	private BigDecimal totalPrice;
	private Integer quantity;
	private String description;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Orderer orderer;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Item item;
	
	
	
	
	

}
