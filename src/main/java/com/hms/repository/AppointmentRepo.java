package com.hms.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.hms.entity.Appointment;

public interface AppointmentRepo extends JpaRepository<Appointment, Integer> {
    List<Appointment> findByPatientPatientId(int patientId);
    List<Appointment> findByDoctorDoctorId(int doctorId);
    List<Appointment> findByStatus(String status);
    List<Appointment> findByAppointmentDate(LocalDate date);
    long countByStatus(String status);
}
