package com.delivery.homeeats.domain.repository;

import java.util.List;

import com.delivery.homeeats.domain.model.PaymentMethod;

public interface PaymentMethodRepository {
	
	List<PaymentMethod> list();
	PaymentMethod findById(Long id);
	PaymentMethod add(PaymentMethod paymentMethod);
	void remove(PaymentMethod paymentMethod);

}
