package com.grupilkbahar.hospitalmanagementsystem.repository;

import com.grupilkbahar.hospitalmanagementsystem.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

}
