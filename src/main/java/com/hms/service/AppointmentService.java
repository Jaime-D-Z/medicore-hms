package com.hms.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hms.entity.Appointment;
import com.hms.repository.AppointmentRepo;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepo repo;

    public void save(Appointment a) { repo.save(a); }
    public List<Appointment> getAll() { return repo.findAll(); }
    public Appointment getById(int id) { return repo.findById(id).orElse(null); }
    public void delete(int id) { repo.deleteById(id); }
    public List<Appointment> getByPatient(int patientId) { return repo.findByPatientPatientId(patientId); }
    public List<Appointment> getByDoctor(int doctorId) { return repo.findByDoctorDoctorId(doctorId); }
    public List<Appointment> getByStatus(String status) { return repo.findByStatus(status); }
    public long countByStatus(String status) { return repo.countByStatus(status); }
}
