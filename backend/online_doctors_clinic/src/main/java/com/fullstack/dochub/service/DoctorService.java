package com.fullstack.dochub.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.fullstack.dochub.Entity.Doctor;
import com.fullstack.dochub.repository.DoctorRepository;

@Service
public class DoctorService {

	@Autowired
	DoctorRepository doctorRepo;

	// doctor registration
	public Doctor addNewDoctor(Doctor doctor) {
		return doctorRepo.save(doctor);
	}

	// doctor login
//	client login
	public Doctor doctorLogin(String email, String password) {
		Doctor doctor = doctorRepo.findByEmailAndPassword(email, password);
		if (doctor == null) {
			throw new IllegalArgumentException("Invalid credentials");
		}
		return doctor;
	}

	public Doctor findByEmail(String email) {
		return doctorRepo.findById(email).orElse(null);
	}

}
