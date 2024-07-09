package com.fullstack.dochub.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fullstack.dochub.entity.Appointments;
import com.fullstack.dochub.entity.Client;
import com.fullstack.dochub.entity.Doctor;
import com.fullstack.dochub.repository.AppointmentsRepository;
import com.fullstack.dochub.repository.ClientRepository;
import com.fullstack.dochub.repository.DoctorRepository;

/**
 * Service class for managing {@link Appointments} entities.
 */
@Service
public class AppointmentsService {

    @Autowired
    private AppointmentsRepository appointmentsRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    /**
     * Books a new appointment.
     * 
     * @param clientEmail the email of the client
     * @param doctorEmail the email of the doctor
     * @param appointment the appointment details
     * @return the booked appointment
     * @throws IllegalArgumentException if client or doctor is not found
     */
    public Appointments bookAppointment(String clientEmail, String doctorEmail, Appointments appointment) {
        if (clientEmail == null || clientEmail.isEmpty() || doctorEmail == null || doctorEmail.isEmpty() || appointment == null) {
            throw new IllegalArgumentException("Client email, doctor email, and appointment details must not be null or empty.");
        }

        Client client = clientRepository.findById(clientEmail)
                .orElseThrow(() -> new IllegalArgumentException("Client not found"));
        Doctor doctor = doctorRepository.findById(doctorEmail)
                .orElseThrow(() -> new IllegalArgumentException("Doctor not found"));

        appointment.setClient(client);
        appointment.setDoctor(doctor);

        return appointmentsRepository.save(appointment);
    }

    /**
     * Retrieves the list of appointments for a given client.
     * 
     * @param clientEmail the email of the client
     * @return the list of appointments
     * @throws IllegalArgumentException if no appointments are found for the client
     */
    public List<Appointments> getClientAppointments(String clientEmail) {
        if (clientEmail == null || clientEmail.isEmpty()) {
            throw new IllegalArgumentException("Client email must not be null or empty.");
        }

        List<Appointments> appointmentList = appointmentsRepository.findByClientEmail(clientEmail);
        if (appointmentList == null || appointmentList.isEmpty()) {
            throw new IllegalArgumentException("No appointments found for the client");
        }
        return appointmentList;
    }

    /**
     * Retrieves the list of appointments for a given doctor.
     * 
     * @param doctorEmail the email of the doctor
     * @return the list of appointments
     * @throws IllegalArgumentException if no appointments are found for the doctor
     */
    public List<Appointments> getDoctorAppointments(String doctorEmail) {
        if (doctorEmail == null || doctorEmail.isEmpty()) {
            throw new IllegalArgumentException("Doctor email must not be null or empty.");
        }

        List<Appointments> appointmentList = appointmentsRepository.findByDoctorEmail(doctorEmail);
        if (appointmentList == null || appointmentList.isEmpty()) {
            throw new IllegalArgumentException("No appointments found for the doctor");
        }
        return appointmentList;
    }

    /**
     * Updates the status of an appointment.
     * 
     * @param appointment the appointment with the updated status
     * @return the updated appointment
     * @throws IllegalArgumentException if appointment ID is invalid or not found
     */
    public Appointments updateAppointmentStatus(Appointments appointment) {
        if (appointment == null || appointment.getId() <= 0 || !appointmentsRepository.existsById(appointment.getId())) {
            throw new IllegalArgumentException("Invalid appointment ID for updating status.");
        }
        return appointmentsRepository.save(appointment);
    }
}
