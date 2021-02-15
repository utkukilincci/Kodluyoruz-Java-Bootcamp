package com.grupilkbahar.hospitalmanagementsystem.controller;

import java.util.List;

import com.grupilkbahar.hospitalmanagementsystem.entity.Appointment;
import com.grupilkbahar.hospitalmanagementsystem.service.AppointmentService;
import com.grupilkbahar.hospitalmanagementsystem.service.DoctorService;
import com.grupilkbahar.hospitalmanagementsystem.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
public class AppointmentController {

	private final AppointmentService appointmentService;
	private final PatientService patientService;
	private final DoctorService doctorService;

	/*public AppointmentController(AppointmentService appointmentService, PatientService patientService, DoctorService doctorService) {
		this.appointmentService = appointmentService;
		this.patientService = patientService;
		this.doctorService = doctorService;
	}*/

	@RequestMapping("/appointments")
	public String findAllAppointments(Model model) {
		final List<Appointment> appointments = appointmentService.findAllAppointments();

		model.addAttribute("appointments", appointments);
		return "list-appointments";
	}

	@RequestMapping("/appointment/{id}")
	public String findAppointmentById(@PathVariable("id") Long id, Model model) {
		final Appointment appointment = appointmentService.findAppointmentById(id);

		model.addAttribute("appointment", appointment);
		return "list-appointment";
	}

	@GetMapping("/addAppointment")
	public String showCreateForm(Appointment appointment , Model model) {
		model.addAttribute("patients", patientService.findAllPatients());
		model.addAttribute("doctors", doctorService.findAllDoctors());
		return "add-appointment";
	}

	@RequestMapping("/add-appointment")
	public String createAppointment(Appointment appointment, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-appointment";
		}

		appointmentService.createAppointment(appointment);
		model.addAttribute("appointment", appointmentService.findAllAppointments());
		return "redirect:/appointments";
	}

	@GetMapping("/updateAppointment/{id}")
	public String showUpdateForm(@PathVariable("id") Long id, Model model) {
		final Appointment appointment = appointmentService.findAppointmentById(id);
		model.addAttribute("patients", patientService.findAllPatients());
		model.addAttribute("doctors", doctorService.findAllDoctors());

		model.addAttribute("appointment", appointment);
		return "update-appointment";
	}

	@RequestMapping("/update-appointment/{id}")
	public String updateAppointment(@PathVariable("id") Long id, Appointment appointment, BindingResult result, Model model) {
		if (result.hasErrors()) {
			appointment.setId(id);
			return "update-appointments";
		}

		appointmentService.updateAppointment(appointment);
		model.addAttribute("appointment", appointmentService.findAllAppointments());
		return "redirect:/appointments";
	}

	@RequestMapping("/remove-appointment/{id}")
	public String deleteAppointment(@PathVariable("id") Long id, Model model) {
		appointmentService.deleteAppointment(id);

		model.addAttribute("appointment", appointmentService.findAllAppointments());
		return "redirect:/appointments";
	}

}
