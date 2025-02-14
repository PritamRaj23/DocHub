package com.fullstack.dochub.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fullstack.dochub.entity.Admin;
import com.fullstack.dochub.entity.Appointments;
import com.fullstack.dochub.entity.Client;
import com.fullstack.dochub.entity.Doctor;
import com.fullstack.dochub.service.AdminService;
import com.fullstack.dochub.service.AppointmentsService;
import com.fullstack.dochub.service.ClientService;
import com.fullstack.dochub.service.DoctorService;

import jakarta.servlet.http.HttpSession;

/**
 * REST controller for handling API requests related to clients, doctors,
 * admins, and appointments.
 */
@CrossOrigin
@RestController
@RequestMapping("/api")
public class Controller {

	@Autowired
	private AppointmentsService appointmentsService;

	@Autowired
	private ClientService clientService;

	@Autowired
	private DoctorService doctorService;

	@Autowired
	private AdminService adminService;

	/**
	 * Registers a new client.
	 * 
	 * @param client  the client information
	 * @param session the HTTP session
	 * @return ResponseEntity with the registered client or error status
	 */
	@PostMapping("/clientRegistration")
	public ResponseEntity<Client> clientRegistration(@RequestBody Client client, HttpSession session) {
		if (client == null || client.getEmail() == null || client.getEmail().isEmpty()) {
			return ResponseEntity.badRequest().build();
		}
		Client newClient = clientService.addNewClient(client);
		session.setAttribute("data", newClient);
		return ResponseEntity.status(HttpStatus.CREATED).body(newClient);
	}

	/**
	 * Logs in a client.
	 * 
	 * @param client the client login details
	 * @return ResponseEntity with the logged-in client or error status
	 */
	@PostMapping("/clientLogin")
	public ResponseEntity<Client> clientLogin(@RequestBody Client client) {
		if (client == null || client.getPassword() == null || client.getEmail() == null) {
			return ResponseEntity.badRequest().build();
		}
		Client loggedInClient = clientService.clientLogin(client.getEmail(), client.getPassword());
		return loggedInClient != null ? ResponseEntity.ok(loggedInClient)
				: ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}

	/**
	 * Registers a new doctor.
	 * 
	 * @param doctor  the doctor information
	 * @param session the HTTP session
	 * @return ResponseEntity with the registered doctor or error status
	 */
	@PostMapping("/doctorRegistration")
	public ResponseEntity<Doctor> doctorRegistration(@RequestBody Doctor doctor, HttpSession session) {
		if (doctor == null || doctor.getEmail() == null || doctor.getEmail().isEmpty()) {
			return ResponseEntity.badRequest().build();
		}
		Doctor newDoctor = doctorService.addNewDoctor(doctor);
		session.setAttribute("data", newDoctor);
		return ResponseEntity.status(HttpStatus.CREATED).body(newDoctor);
	}

	/**
	 * Logs in a doctor.
	 * 
	 * @param doctor the doctor login details
	 * @return ResponseEntity with the logged-in doctor or error status
	 */
	@PostMapping("/doctorLogin")
	public ResponseEntity<Doctor> doctorLogin(@RequestBody Doctor doctor) {
		if (doctor == null || doctor.getPassword() == null || doctor.getEmail() == null) {
			return ResponseEntity.badRequest().build();
		}
		Doctor loggedInDoctor = doctorService.doctorLogin(doctor.getEmail(), doctor.getPassword());
		return loggedInDoctor != null ? ResponseEntity.ok(loggedInDoctor)
				: ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}

	/**
	 * Logs in an admin.
	 * 
	 * @param admin the admin login details
	 * @return ResponseEntity with the logged-in admin or error status
	 */
	@PostMapping("/adminLogin")
	public ResponseEntity<Admin> adminLogin(@RequestBody Admin admin) {
		if (admin == null || admin.getPassword() == null || admin.getEmail() == null) {
			return ResponseEntity.badRequest().build();
		}
		Admin loggedInAdmin = adminService.adminLogin(admin.getEmail(), admin.getPassword());
		return loggedInAdmin != null ? ResponseEntity.ok(loggedInAdmin)
				: ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}

	/**
	 * Books a new appointment.
	 * 
	 * @param clientEmail the client's email
	 * @param doctorEmail the doctor's email
	 * @param appointment the appointment details
	 * @return ResponseEntity with the booked appointment or error status
	 */
	@PostMapping("/client/bookAppointments/{clientEmail}/{doctorEmail}")
	public ResponseEntity<Appointments> bookAppointment(@PathVariable String clientEmail,
			@PathVariable String doctorEmail, @RequestBody Appointments appointment) {
		try {
			if (clientEmail == null || clientEmail.isEmpty() || doctorEmail == null || doctorEmail.isEmpty()
					|| appointment == null) {
				return ResponseEntity.badRequest().build();
			}
			Appointments bookedAppointment = appointmentsService.bookAppointment(clientEmail, doctorEmail, appointment);
			return ResponseEntity.status(HttpStatus.CREATED).body(bookedAppointment);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	/**
	 * Retrieves appointments for a given client.
	 * 
	 * @param clientEmail the email of the client
	 * @return ResponseEntity with the list of appointments or error status
	 */
	@GetMapping("/client/appointments/{clientEmail}")
	public ResponseEntity<List<Appointments>> getClientAppointments(@PathVariable String clientEmail) {
		try {
			if (clientEmail == null || clientEmail.isEmpty()) {
				return ResponseEntity.badRequest().build();
			}
			List<Appointments> appointmentList = appointmentsService.getClientAppointments(clientEmail);
			return appointmentList.isEmpty() ? ResponseEntity.status(HttpStatus.NO_CONTENT).build()
					: ResponseEntity.ok(appointmentList);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	/**
	 * Retrieves appointments for a given doctor.
	 * 
	 * @param doctorEmail the email of the doctor
	 * @return ResponseEntity with the list of appointments or error status
	 */
	@GetMapping("/doctor/appointments/{doctorEmail}")
	public ResponseEntity<List<Appointments>> getDoctorAppointments(@PathVariable String doctorEmail) {
		try {
			if (doctorEmail == null || doctorEmail.isEmpty()) {
				return ResponseEntity.badRequest().build();
			}
			List<Appointments> appointmentList = appointmentsService.getDoctorAppointments(doctorEmail);
			return appointmentList.isEmpty() ? ResponseEntity.status(HttpStatus.NO_CONTENT).build()
					: ResponseEntity.ok(appointmentList);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	/**
	 * Updates the status of an appointment.
	 * 
	 * @param appointment the appointment with updated status
	 * @return ResponseEntity with the updated appointment or error status
	 */
	@PutMapping("/doctor/editAppointmentStatus")
	public ResponseEntity<Appointments> updateAppointmentStatus(@RequestBody Appointments appointment) {
		try {
			if (appointment == null || appointment.getId() <= 0) {
				return ResponseEntity.badRequest().build();
			}
			Appointments updatedAppointment = appointmentsService.updateAppointmentStatus(appointment);
			return ResponseEntity.ok(updatedAppointment);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	/**
	 * Retrieves all clients.
	 * 
	 * @return ResponseEntity with the list of clients or error status
	 */
	@GetMapping("/admin/getClient")
	public ResponseEntity<List<Client>> getAllClient() {
		List<Client> clientList = adminService.getAllClient();
		return clientList.isEmpty() ? ResponseEntity.status(HttpStatus.NO_CONTENT).build()
				: ResponseEntity.ok(clientList);
	}

	/**
	 * Updates the profile of a doctor.
	 * 
	 * @param doctor the doctor with updated details
	 * @return ResponseEntity with the updated doctor or error status
	 */
	@PutMapping("/doctor/profile")
	public ResponseEntity<Doctor> updateDoctorProfile(@RequestBody Doctor doctor) {
		try {
			if (doctor == null || doctor.getEmail() == null || doctor.getEmail().isEmpty()) {
				return ResponseEntity.badRequest().build();
			}

			Doctor updatedDoctor = doctorService.updateDoctorProfile(doctor);
			return ResponseEntity.ok(updatedDoctor);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
