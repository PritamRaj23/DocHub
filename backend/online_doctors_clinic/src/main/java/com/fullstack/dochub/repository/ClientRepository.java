package com.fullstack.dochub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fullstack.dochub.entity.Client;
import java.util.Optional;

/**
 * Repository interface for managing {@link Client} entities.
 */
public interface ClientRepository extends JpaRepository<Client, String> {

    /**
     * Finds a client by email and password.
     * 
     * @param email the client's email
     * @param password the client's password
     * @return an Optional containing the found client or empty if no client is found
     */
    Optional<Client> findByEmailAndPassword(String email, String password);
}
