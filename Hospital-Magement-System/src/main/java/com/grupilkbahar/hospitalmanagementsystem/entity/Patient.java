package com.grupilkbahar.hospitalmanagementsystem.entity;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
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
@Table(name = "patients")
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "tcNumber", length = 15, nullable = false, unique = true)
	private String tcNumber;

	@Column(name = "name", length = 100, nullable = false)
	private String name;

	@Column(name = "phoneNumber", length = 15, nullable = false)
	private String phoneNumber;

	@Column(name = "complaint", length = 250, nullable = false)
	private String complaint;

	@Temporal(TemporalType.DATE)
	@Column(name = "registerDate", nullable = false, updatable = false)
	private Date registerDate = new Date();

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate birthDate;




	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "patients_doctors", joinColumns = { @JoinColumn(name = "patient_id") }, inverseJoinColumns = {
			@JoinColumn(name = "doctor_id") })
	private Set<Doctor> doctors = new HashSet<Doctor>();

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "patients_departments", joinColumns = { @JoinColumn(name = "patient_id") }, inverseJoinColumns = {
			@JoinColumn(name = "department_id") })
	private Set<Department> departments = new HashSet<Department>();

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE , CascadeType.REMOVE}, mappedBy = "patients")
	private Set<Appointment> appointments = new HashSet<Appointment>();

	public Patient(String tcNumber, String name, String phoneNumber, String complaint) {
		this.tcNumber = tcNumber;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.complaint = complaint;
	}

	public void addDoctors(Doctor doctor) {
		this.doctors.add(doctor);
		doctor.getPatients().add(this);
	}

	public void removeDoctors(Doctor doctor) {
		this.doctors.remove(doctor);
		doctor.getPatients().remove(this);
	}

	public void addDepartments(Department department) {
		this.departments.add(department);
		department.getPatients().add(this);
	}

	public void removeDepartments(Department department) {
		this.departments.remove(department);
		department.getPatients().remove(this);
	}

	public void addAppointments(Appointment appointment) {
		this.appointments.add(appointment);
		appointment.getPatients().add(this);
	}

	public void removeAppointments(Appointment appointment) {
		this.appointments.remove(appointment);
		appointment.getPatients().remove(this);
	}


}
