package com.grupilkbahar.hospitalmanagementsystem.controller;

import java.util.List;

import com.grupilkbahar.hospitalmanagementsystem.entity.Patient;
import com.grupilkbahar.hospitalmanagementsystem.service.DoctorService;
import com.grupilkbahar.hospitalmanagementsystem.service.PatientService;
import com.grupilkbahar.hospitalmanagementsystem.service.DepartmentService;
import com.grupilkbahar.hospitalmanagementsystem.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
public class PatientController {

	private final PatientService patientService;
	private final DoctorService doctorService;
	private final DepartmentService departmentService;
	private final AppointmentService appointmentService;

	/*@Autowired
	public PatientController(PatientService patientService, DoctorService doctorService, DepartmentService departmentService,
							 AppointmentService appointmentService) {
		this.patientService = patientService;
		this.doctorService = doctorService;
		this.departmentService = departmentService;
		this.appointmentService = appointmentService;
	}*/

	@RequestMapping("/patients")
	public String findAllPatients(Model model) {
		final List<Patient> patients = patientService.findAllPatients();

		model.addAttribute("patients", patients);
		return "list-patients";
	}

	@RequestMapping("/searchPatient")
	public String searchPatient(@Param("keyword") String keyword, Model model) {
		final List<Patient> patients = patientService.searchPatients(keyword);

		model.addAttribute("patients", patients);
		model.addAttribute("keyword", keyword);
		return "list-patients";
	}

	@RequestMapping("/patient/{id}")
	public String findPatientById(@PathVariable("id") Long id, Model model) {
		final Patient patient = patientService.findPatientById(id);

		model.addAttribute("patient", patient);
		return "list-patient";
	}

	@GetMapping("/add")
	public String showCreateForm(Patient patient, Model model) {
		model.addAttribute("departments", departmentService.findAllDepartments());
		model.addAttribute("doctors", doctorService.findAllDoctors());
		return "add-patient";
	}

	@RequestMapping("/add-patient")
	public String createPatient(Patient patient, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-patient";
		}

		patientService.createPatient(patient);
		model.addAttribute("patient", patientService.findAllPatients());
		return "redirect:/patients";
	}

	@GetMapping("/update/{id}")
	public String showUpdateForm(@PathVariable("id") Long id, Model model) {
		final Patient patient = patientService.findPatientById(id);
		model.addAttribute("departments", departmentService.findAllDepartments());
		model.addAttribute("doctors", doctorService.findAllDoctors());

		model.addAttribute("patient", patient);
		return "update-patient";
	}

	@RequestMapping("/update-patient/{id}")
	public String updatePatient(@PathVariable("id") Long id, Patient patient, BindingResult result, Model model) {
		if (result.hasErrors()) {
			patient.setId(id);
			return "update-patient";
		}

		patientService.updatePatient(patient);
		model.addAttribute("patient", patientService.findAllPatients());
		return "redirect:/patients";
	}

	@RequestMapping("/remove-patient/{id}")
	public String deletePatient(@PathVariable("id") Long id, Model model) {
		patientService.deletePatient(id);

		model.addAttribute("patient", patientService.findAllPatients());
		return "redirect:/patients";
	}

}
