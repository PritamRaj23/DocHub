package com.fullstack.dochub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fullstack.dochub.entity.Doctor;
import java.util.Optional;

/**
 * Repository interface for managing {@link Doctor} entities.
 */
public interface DoctorRepository extends JpaRepository<Doctor, String> {

    /**
     * Finds a doctor by email and password.
     * 
     * @param email the doctor's email
     * @param password the doctor's password
     * @return an Optional containing the found doctor or empty if no doctor is found
     */
    Optional<Doctor> findByEmailAndPassword(String email, String password);
}
