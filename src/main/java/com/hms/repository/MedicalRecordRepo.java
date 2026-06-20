package com.hms.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.hms.entity.MedicalRecord;

public interface MedicalRecordRepo extends JpaRepository<MedicalRecord, Integer> {
    List<MedicalRecord> findByPatientPatientId(int patientId);
    List<MedicalRecord> findByDoctorDoctorId(int doctorId);
}
