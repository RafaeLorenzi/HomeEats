package com.delivery.homeeats.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import com.delivery.homeeats.domain.model.PaymentMethod;
import com.delivery.homeeats.domain.repository.PaymentMethodRepository;

@Component
public class PaymentMethodRepositoryImp implements PaymentMethodRepository{
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<PaymentMethod> list(){
		return manager.createQuery("from payment_method", PaymentMethod.class)
				.getResultList();
	}
	@Override
	@Transactional
	public PaymentMethod add(PaymentMethod paymentMethod) {
		return manager.merge(paymentMethod);
		
	}
	@Override
	public PaymentMethod findById(Long id) {
		return manager.find(PaymentMethod.class, id);
	}
	@Override
	@Transactional
	public void remove(PaymentMethod paymentMethod) {
		paymentMethod = findById(paymentMethod.getId());
		manager.remove(paymentMethod);
		
	}
	

}
