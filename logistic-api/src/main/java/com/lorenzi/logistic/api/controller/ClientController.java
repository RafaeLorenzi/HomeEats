package com.lorenzi.logistic.api.controller;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lorenzi.logistic.domain.model.Client;
import com.lorenzi.logistic.domain.repository.ClientRepository;

import lombok.AllArgsConstructor;


@RestController
public class ClientController {
	
	@Autowired
	private ClientRepository clientRepository;
	
	

	@GetMapping("/clients")
	public List<Client> list() {
	
		return clientRepository.findAll();
	}

}
