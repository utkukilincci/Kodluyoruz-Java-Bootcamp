package com.grupilkbahar.hospitalmanagementsystem.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "appointments")
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "operation", length = 100, nullable = false, unique = true)
	private String operation;

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate appointmentDate;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "appointments_doctors", joinColumns = { @JoinColumn(name = "appointment_id") }, inverseJoinColumns = {
			@JoinColumn(name = "doctor_id") })
	private Set<Doctor> doctors = new HashSet<Doctor>();

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "appointments_patients", joinColumns = { @JoinColumn(name = "appointment_id") }, inverseJoinColumns = {
			@JoinColumn(name = "doctor_id") })
	private Set<Patient> patients = new HashSet<Patient>();

	public Appointment(String operation) {
		this.operation = operation;
	}

}
