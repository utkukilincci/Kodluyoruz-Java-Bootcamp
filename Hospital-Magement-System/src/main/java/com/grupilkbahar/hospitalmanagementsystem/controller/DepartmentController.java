package com.grupilkbahar.hospitalmanagementsystem.controller;

import java.util.List;

import com.grupilkbahar.hospitalmanagementsystem.entity.Department;
import com.grupilkbahar.hospitalmanagementsystem.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
public class DepartmentController {

	private final DepartmentService departmentService;

	/*public DepartmentController(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}*/

	@RequestMapping("/departments")
	public String findAllCategories(Model model) {
		final List<Department> categories = departmentService.findAllDepartments();

		model.addAttribute("departments", categories);
		return "list-departments";
	}

	@RequestMapping("/category/{id}")
	public String findCategoryById(@PathVariable("id") Long id, Model model) {
		final Department department = departmentService.findDepartmentById(id);

		model.addAttribute("department", department);
		return "list-department";
	}

	@GetMapping("/addDepartment")
	public String showCreateForm(Department department) {
		return "add-department";
	}

	@RequestMapping("/add-department")
	public String createCategory(Department department, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-department";
		}

		departmentService.createDepartment(department);
		model.addAttribute("department", departmentService.findAllDepartments());
		return "redirect:/departments";
	}

	@GetMapping("/updateDepartment/{id}")
	public String showUpdateForm(@PathVariable("id") Long id, Model model) {
		final Department department = departmentService.findDepartmentById(id);

		model.addAttribute("department", department);
		return "update-department";
	}

	@RequestMapping("/update-department/{id}")
	public String updateCategory(@PathVariable("id") Long id, Department department, BindingResult result, Model model) {
		if (result.hasErrors()) {
			department.setId(id);
			return "update-department";
		}
//

		departmentService.updateDepartment(department);
		model.addAttribute("department", departmentService.findAllDepartments());
		return "redirect:/departments";
	}

	@RequestMapping("/remove-department/{id}")
	public String deleteDepartment(@PathVariable("id") Long id, Model model) {
		departmentService.deleteDepartment(id);

		model.addAttribute("department", departmentService.findAllDepartments());
		return "redirect:/departments";
	}

}
