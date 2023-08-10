package com.delivery.homeeats.domain.model;



import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true )
@Entity
public class Orderer {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private BigDecimal subTotal;
	private BigDecimal deliverFee;
	private BigDecimal total;
	
	@Embedded
	private Adress deliverAdress;
	
	private DeliverStatus status;
	
	@CreationTimestamp
	private LocalDateTime createDate;
	
	private LocalDateTime confirmDate;
	private LocalDateTime cancelDate;
	private LocalDateTime deliverDate;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private PaymentMethod paymentMethod;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Restaurant restaurant;
	
	@ManyToOne
	@JoinColumn(name = "user_client_id", nullable = false)
	private User client;
	
	@OneToMany(mappedBy = "orderer")
	private List<ItemOrdered> itens = new ArrayList<>();
	
}
