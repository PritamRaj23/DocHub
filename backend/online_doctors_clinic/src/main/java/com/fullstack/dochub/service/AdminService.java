package com.fullstack.dochub.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fullstack.dochub.entity.Admin;
import com.fullstack.dochub.entity.Client;
import com.fullstack.dochub.repository.AdminRepository;
import com.fullstack.dochub.repository.ClientRepository;

/**
 * Service class for managing {@link Admin} and {@link Client} entities.
 */
@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepo;

    @Autowired
    private ClientRepository clientRepo;

    /**
     * Authenticates an admin using email and password.
     * 
     * @param email the admin's email
     * @param password the admin's password
     * @return the authenticated admin
     * @throws IllegalArgumentException if credentials are invalid
     */
    public Admin adminLogin(String email, String password) {
        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Email and password must not be null or empty.");
        }

        return adminRepo.findByEmailAndPassword(email, password)
                .orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));
    }

    /**
     * Retrieves all clients.
     * 
     * @return a list of clients
     */
    public List<Client> getAllClient() {
        return clientRepo.findAll();
    }
}
