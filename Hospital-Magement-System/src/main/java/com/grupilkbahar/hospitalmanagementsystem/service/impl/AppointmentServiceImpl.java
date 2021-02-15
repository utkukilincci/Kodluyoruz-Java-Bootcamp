package com.grupilkbahar.hospitalmanagementsystem.service.impl;

import java.util.List;

import com.grupilkbahar.hospitalmanagementsystem.entity.Appointment;
import com.grupilkbahar.hospitalmanagementsystem.service.AppointmentService;
import com.grupilkbahar.hospitalmanagementsystem.repository.AppointmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.grupilkbahar.hospitalmanagementsystem.exception.NotFoundException;

@Service
public class AppointmentServiceImpl implements AppointmentService {

	private final AppointmentRepository appointmentRepository;

	public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
		this.appointmentRepository = appointmentRepository;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public List<Appointment> findAllAppointments() {
		return appointmentRepository.findAll();
	}

	@Override
	public Appointment findAppointmentById(Long id) {
		return appointmentRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Appointment not found  with ID %d", id)));
	}

	@Override
	public void createAppointment(Appointment appointment) {
		appointmentRepository.save(appointment);
	}

	@Override
	public void updateAppointment(Appointment appointment) {
		appointmentRepository.save(appointment);
	}

	@Override
	public void deleteAppointment(Long id) {
		final Appointment appointment = appointmentRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Appointment not found  with ID %d", id)));

		appointmentRepository.deleteById(appointment.getId());
	}

}
