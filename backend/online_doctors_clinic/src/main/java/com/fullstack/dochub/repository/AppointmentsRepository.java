package com.fullstack.dochub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fullstack.dochub.Entity.Appointments;
import java.util.List;


public interface AppointmentsRepository extends JpaRepository<Appointments, Integer> {
	List<Appointments> findByClient_Email(String client_Email);
	List<Appointments> findByDoctor_Email(String doctor_Email);
}
