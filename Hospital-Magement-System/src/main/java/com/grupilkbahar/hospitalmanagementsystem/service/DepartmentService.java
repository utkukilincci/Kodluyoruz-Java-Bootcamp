package com.grupilkbahar.hospitalmanagementsystem.service;

import java.util.List;

import com.grupilkbahar.hospitalmanagementsystem.entity.Department;

public interface DepartmentService {

	public List<Department> findAllDepartments();

	public Department findDepartmentById(Long id);

	public void createDepartment(Department department);

	public void updateDepartment(Department department);

	public void deleteDepartment(Long id);

}
