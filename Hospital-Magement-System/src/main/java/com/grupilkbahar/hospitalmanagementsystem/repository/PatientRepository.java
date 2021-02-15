package com.grupilkbahar.hospitalmanagementsystem.repository;

import java.util.List;

import com.grupilkbahar.hospitalmanagementsystem.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PatientRepository extends JpaRepository<Patient, Long> {

	@Query("SELECT b FROM Patient b WHERE b.name LIKE %?1%" + " OR b.tcNumber LIKE %?1%" + " OR b.id LIKE %?1%")
	public List<Patient> search(String keyword);
}
