package com.fullstack.dochub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fullstack.dochub.entity.Appointments;
import java.util.List;

/**
 * Repository interface for managing {@link Appointments} entities.
 */
public interface AppointmentsRepository extends JpaRepository<Appointments, Integer> {

    /**
     * Finds appointments by client email.
     * 
     * @param clientEmail the email of the client
     * @return a list of appointments for the given client email
     */
    List<Appointments> findByClientEmail(String clientEmail);

    /**
     * Finds appointments by doctor email.
     * 
     * @param doctorEmail the email of the doctor
     * @return a list of appointments for the given doctor email
     */
    List<Appointments> findByDoctorEmail(String doctorEmail);
}
