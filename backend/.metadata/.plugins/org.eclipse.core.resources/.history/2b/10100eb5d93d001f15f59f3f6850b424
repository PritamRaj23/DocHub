package com.fullstack.dochub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fullstack.dochub.Entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, String> {
	Doctor findByEmailAndPassword(String email, String password);
}
