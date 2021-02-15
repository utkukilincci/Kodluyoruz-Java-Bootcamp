package com.grupilkbahar.hospitalmanagementsystem.service.impl;

import java.util.List;

import com.grupilkbahar.hospitalmanagementsystem.entity.Department;
import com.grupilkbahar.hospitalmanagementsystem.repository.DepartmentRepository;
import com.grupilkbahar.hospitalmanagementsystem.service.DepartmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.grupilkbahar.hospitalmanagementsystem.exception.NotFoundException;

@Service
public class DeparmentServiceImpl implements DepartmentService {

	private final DepartmentRepository departmentRepository;

	public DeparmentServiceImpl(DepartmentRepository departmentRepository) {
		this.departmentRepository = departmentRepository;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public List<Department> findAllDepartments() {
		return departmentRepository.findAll();
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public Department findDepartmentById(Long id) {
		return departmentRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Department not found  with ID %d", id)));
	}

	@Override
	public void createDepartment(Department department) {
		departmentRepository.save(department);
	}

	@Override
	public void updateDepartment(Department department) {
		departmentRepository.save(department);
	}

	@Override
	public void deleteDepartment(Long id) {
		final Department department = departmentRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Department not found  with ID %d", id)));

		departmentRepository.deleteById(department.getId());
	}

}
