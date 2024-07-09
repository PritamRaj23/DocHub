package com.fullstack.dochub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fullstack.dochub.entity.Doctor;
import com.fullstack.dochub.repository.DoctorRepository;

/**
 * Service class for managing {@link Doctor} entities.
 */
@Service
public class DoctorService {

	@Autowired
	private DoctorRepository doctorRepo;

	/**
	 * Registers a new doctor.
	 * 
	 * @param doctor the doctor to be registered
	 * @return the registered doctor
	 * @throws IllegalArgumentException if doctor information is incomplete
	 */
	public Doctor addNewDoctor(Doctor doctor) {
		if (doctor == null || doctor.getEmail() == null || doctor.getEmail().isEmpty()) {
			throw new IllegalArgumentException("Doctor information is missing or incomplete.");
		}
		return doctorRepo.save(doctor);
	}

	/**
	 * Authenticates a doctor using email and password.
	 * 
	 * @param email    the doctor's email
	 * @param password the doctor's password
	 * @return the authenticated doctor
	 * @throws IllegalArgumentException if credentials are invalid
	 */
	public Doctor doctorLogin(String email, String password) {
		if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
			throw new IllegalArgumentException("Email or password is missing.");
		}
		return doctorRepo.findByEmailAndPassword(email, password)
				.orElseThrow(() -> new IllegalArgumentException("Invalid credentials."));
	}

	/**
	 * Retrieves a doctor by email.
	 * 
	 * @param email the email of the doctor
	 * @return the doctor or null if not found
	 * @throws IllegalArgumentException if email is invalid
	 */
	public Doctor findByEmail(String email) {
		if (email == null || email.isEmpty()) {
			throw new IllegalArgumentException("Email is missing.");
		}
		return doctorRepo.findById(email).orElse(null);
	}

	/**
	 * Updates the profile of an existing doctor.
	 * 
	 * @param updatedDoctor the doctor with updated details
	 * @return the updated doctor
	 * @throws IllegalArgumentException if doctor information is invalid
	 */
	public Doctor updateDoctorProfile(Doctor updatedDoctor) {
		if (updatedDoctor == null || updatedDoctor.getEmail() == null || updatedDoctor.getEmail().isEmpty()) {
			throw new IllegalArgumentException("Invalid doctor information.");
		}

		Doctor existingDoctor = doctorRepo.findById(updatedDoctor.getEmail())
				.orElseThrow(() -> new IllegalArgumentException("Doctor not found."));

		// Update fields
		existingDoctor.setName(updatedDoctor.getName());
		existingDoctor.setPhone(updatedDoctor.getPhone());
		existingDoctor.setAddress(updatedDoctor.getAddress());
		existingDoctor.setGender(updatedDoctor.getGender());
		existingDoctor.setDepartment(updatedDoctor.getDepartment());
		existingDoctor.setSchedule(updatedDoctor.getSchedule());

		return doctorRepo.save(existingDoctor);
	}

}
