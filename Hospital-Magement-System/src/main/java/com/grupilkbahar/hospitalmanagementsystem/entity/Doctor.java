package com.grupilkbahar.hospitalmanagementsystem.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "doctors")
public class Doctor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", length = 100, nullable = false,unique = true)
	private String name;

	@Column(name = "address", length = 250, nullable = false)
	private String address;

	@Column(name = "phone", length = 20, nullable = false)
	private String phone;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE , CascadeType.REMOVE}, mappedBy = "doctors")
	private Set<Patient> patients = new HashSet<Patient>();

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE , CascadeType.REMOVE}, mappedBy = "doctors")
	private Set<Appointment> appointmentss = new HashSet<Appointment>();

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "doctors_departments", joinColumns = { @JoinColumn(name = "doctor_id") }, inverseJoinColumns = {
			@JoinColumn(name = "department_id") })
	private Set<Department> departments = new HashSet<Department>();

	public Doctor(String name, String address , String phone) {
		this.name = name;
		this.address = address;
		this.phone = phone.toString();
	}

	public void addDepartments(Department department) {
		this.departments.add(department);
		department.getDoctors().add(this);
	}

	public void removeDepartments(Department department) {
		this.departments.remove(department);
		department.getDoctors().remove(this);
	}

}
