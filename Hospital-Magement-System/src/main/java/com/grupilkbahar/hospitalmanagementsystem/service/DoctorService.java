package com.grupilkbahar.hospitalmanagementsystem.service;

import java.util.List;

import com.grupilkbahar.hospitalmanagementsystem.entity.Doctor;

public interface DoctorService {

	public List<Doctor> findAllDoctors();

	public Doctor findDoctorById(Long id);

	public void createDoctor(Doctor doctor);

	public void updateDoctor(Doctor doctor);

	public void deleteDoctor(Long id);

}
