package com.fullstack.dochub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullstack.dochub.Entity.Client;
import com.fullstack.dochub.repository.ClientRepository;

@Service
public class ClientService {

	@Autowired
	ClientRepository clientRepo;

	// client registration
	public Client addNewClient(Client client) {
		return clientRepo.save(client);
	}

//	client login
	public Client clientLogin(String email, String password) {
		Client client = clientRepo.findByEmailAndPassword(email, password);
		if (client == null) {
			throw new IllegalArgumentException("Invalid credentials");
		}
		return client;
	}

	public Client findByEmail(String email) {
		return clientRepo.findById(email).orElse(null);
	}

}
