package com.fullstack.dochub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullstack.dochub.Entity.Admin;
import com.fullstack.dochub.repository.AdminRepository;

@Service
public class AdminService {

	@Autowired
	AdminRepository adminRepo;

	// admin login
	public Admin adminLogin(String email, String password) {
		Admin admin = adminRepo.findByEmailAndPassword(email, password);
		if (admin == null) {
			throw new IllegalArgumentException("Invalid credentials");
		}
		return admin;
	}
}
