package com.fullstack.dochub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fullstack.dochub.entity.Admin;
import java.util.Optional;

/**
 * Repository interface for managing {@link Admin} entities.
 */
public interface AdminRepository extends JpaRepository<Admin, String> {

    /**
     * Finds an admin by email and password.
     * 
     * @param email the admin's email
     * @param password the admin's password
     * @return an Optional containing the found admin or empty if no admin is found
     */
    Optional<Admin> findByEmailAndPassword(String email, String password);
}
