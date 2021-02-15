package com.grupilkbahar.hospitalmanagementsystem.service;

import java.util.List;

import com.grupilkbahar.hospitalmanagementsystem.entity.Patient;

public interface PatientService {

	public List<Patient> findAllPatients();
	
	public List<Patient> searchPatients(String keyword);

	public Patient findPatientById(Long id);

	public void createPatient(Patient patient);

	public void updatePatient(Patient patient);

	public void deletePatient(Long id);

}
