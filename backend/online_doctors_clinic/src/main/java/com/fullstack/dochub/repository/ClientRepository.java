package com.fullstack.dochub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fullstack.dochub.Entity.Client;

public interface ClientRepository extends JpaRepository<Client, String> {
	Client findByEmailAndPassword(String email, String password);
}
