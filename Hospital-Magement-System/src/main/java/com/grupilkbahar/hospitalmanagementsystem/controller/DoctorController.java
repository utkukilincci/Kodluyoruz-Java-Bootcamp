package com.grupilkbahar.hospitalmanagementsystem.controller;

import java.util.List;

import com.grupilkbahar.hospitalmanagementsystem.entity.Doctor;
import com.grupilkbahar.hospitalmanagementsystem.service.DepartmentService;
import com.grupilkbahar.hospitalmanagementsystem.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
public class DoctorController {

	private final DoctorService doctorService;
	private final DepartmentService departmentService;

	/*public DoctorController(DoctorService doctorService, DepartmentService departmentService) {
		this.doctorService = doctorService;
		this.departmentService = departmentService;
	}*/

	@RequestMapping("/doctors")
	public String findAllDoctors(Model model) {
		final List<Doctor> doctors = doctorService.findAllDoctors();

		model.addAttribute("doctors", doctors);
		return "list-doctors";
	}

	@RequestMapping("/doctor/{id}")
	public String findDoctorById(@PathVariable("id") Long id, Model model) {
		final Doctor doctor = doctorService.findDoctorById(id);

		model.addAttribute("doctor", doctor);
		return "list-doctor";
	}

	@GetMapping("/addDoctor")
	public String showCreateForm(Doctor doctor , Model model) {
		model.addAttribute("departments", departmentService.findAllDepartments());
		return "add-doctor";
	}

	@RequestMapping("/add-doctor")
	public String createDoctor(Doctor doctor, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-doctor";
		}

		doctorService.createDoctor(doctor);
		model.addAttribute("doctor", doctorService.findAllDoctors());
		return "redirect:/doctors";
	}

	@GetMapping("/updateDoctor/{id}")
	public String showUpdateForm(@PathVariable("id") Long id, Model model) {
		final Doctor doctor = doctorService.findDoctorById(id);
		model.addAttribute("departments", departmentService.findAllDepartments());

		model.addAttribute("doctor", doctor);
		return "update-doctor";
	}

	@RequestMapping("/update-doctor/{id}")
	public String updateDoctor(@PathVariable("id") Long id, Doctor doctor, BindingResult result, Model model) {
		if (result.hasErrors()) {
			doctor.setId(id);
			return "update-doctor";
		}

		doctorService.updateDoctor(doctor);
		model.addAttribute("doctor", doctorService.findAllDoctors());
		return "redirect:/doctors";
	}

	@RequestMapping("/remove-doctor/{id}")
	public String deleteDoctor(@PathVariable("id") Long id, Model model) {
		doctorService.deleteDoctor(id);

		model.addAttribute("doctor", doctorService.findAllDoctors());
		return "redirect:/doctors";
	}

}
