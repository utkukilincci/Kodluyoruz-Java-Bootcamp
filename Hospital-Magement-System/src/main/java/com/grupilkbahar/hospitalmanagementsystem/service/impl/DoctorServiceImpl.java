package com.grupilkbahar.hospitalmanagementsystem.service.impl;

import java.util.List;

import com.grupilkbahar.hospitalmanagementsystem.entity.Doctor;
import com.grupilkbahar.hospitalmanagementsystem.repository.DoctorRepository;
import com.grupilkbahar.hospitalmanagementsystem.service.DoctorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.grupilkbahar.hospitalmanagementsystem.exception.NotFoundException;

@Service
public class DoctorServiceImpl implements DoctorService {

	private final DoctorRepository doctorRepository;

	public DoctorServiceImpl(DoctorRepository doctorRepository) {
		this.doctorRepository = doctorRepository;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public List<Doctor> findAllDoctors() {
		return doctorRepository.findAll();
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public Doctor findDoctorById(Long id) {
		return doctorRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Doctor not found with ID %d", id)));
	}

	@Override
	public void createDoctor(Doctor doctor) {
		doctorRepository.save(doctor);
	}

	@Override
	public void updateDoctor(Doctor doctor) {
		doctorRepository.save(doctor);
	}

	@Override
	public void deleteDoctor(Long id) {
		final Doctor doctor = doctorRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Doctor not found with ID %d", id)));

		doctorRepository.deleteById(doctor.getId());
	}

}
