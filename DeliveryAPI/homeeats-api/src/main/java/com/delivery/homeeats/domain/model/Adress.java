package com.delivery.homeeats.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Embeddable
public class Adress {
	
	@Column(name= "adress_postal_code")
	private String postalCode;
	
	@Column(name= "adress_street_adress")
	private String streetAdress;
	
	@Column(name= "adress_number")
	private String number;
	
	@Column(name= "adress_complement")
	private String complement; 
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name= "adress_municipalities_id")
	private Municipalities municipalities;
	

}
