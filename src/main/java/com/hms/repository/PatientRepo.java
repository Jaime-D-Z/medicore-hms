package com.hms.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hms.entity.Patient;

@Repository
public interface PatientRepo extends JpaRepository<Patient, Integer> {
    List<Patient> findTop5ByOrderByAdmissionDateDesc();
    List<Patient> findByPatientNameContaining(String name);
}
