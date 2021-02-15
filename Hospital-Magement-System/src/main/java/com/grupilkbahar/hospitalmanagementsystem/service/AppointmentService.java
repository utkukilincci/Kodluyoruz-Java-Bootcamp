package com.grupilkbahar.hospitalmanagementsystem.service;

import java.util.List;

import com.grupilkbahar.hospitalmanagementsystem.entity.Appointment;

public interface AppointmentService {

	public List<Appointment> findAllAppointments();

	public Appointment findAppointmentById(Long id);

	public void createAppointment(Appointment appointment);

	public void updateAppointment(Appointment appointment);

	public void deleteAppointment(Long id);

}
