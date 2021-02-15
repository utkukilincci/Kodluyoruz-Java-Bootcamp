package com.grupilkbahar.hospitalmanagementsystem.service.impl;

import java.util.List;

import com.grupilkbahar.hospitalmanagementsystem.entity.Patient;
import com.grupilkbahar.hospitalmanagementsystem.repository.PatientRepository;
import com.grupilkbahar.hospitalmanagementsystem.service.PatientService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.grupilkbahar.hospitalmanagementsystem.exception.NotFoundException;

@Service
public class PatientServiceImpl implements PatientService {

	private final PatientRepository patientRepository;

	public PatientServiceImpl(PatientRepository patientRepository) {
		this.patientRepository = patientRepository;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public List<Patient> findAllPatients() {
		return patientRepository.findAll();
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public List<Patient> searchPatients(String keyword) {
		if (keyword != null) {
			return patientRepository.search(keyword);
		}
		return patientRepository.findAll();
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public Patient findPatientById(Long id) {
		return patientRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Patient not found with ID %d", id)));
	}

	@Override
	public void createPatient(Patient patient) {
		patientRepository.save(patient);
	}

	@Override
	public void updatePatient(Patient patient) {
		patientRepository.save(patient);
	}

	@Override
	public void deletePatient(Long id) {
		final Patient patient = patientRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Patient not found with ID %d", id)));

		patientRepository.deleteById(patient.getId());
	}

}
