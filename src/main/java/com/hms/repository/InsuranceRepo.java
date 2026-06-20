package com.hms.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.hms.entity.Insurance;

public interface InsuranceRepo extends JpaRepository<Insurance, Integer> {
    List<Insurance> findByPatientPatientId(int patientId);
}
