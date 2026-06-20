package com.hms.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hms.entity.Patient;
import com.hms.repository.PatientRepo;

@Service
public class PatientService {
    @Autowired
    private PatientRepo patientRepository;

    public void addPatient(Patient patient) { patientRepository.save(patient); }
    public List<Patient> getAllPatients() { return patientRepository.findAll(); }
    public List<Patient> getLatestPatients(int count) { return patientRepository.findTop5ByOrderByAdmissionDateDesc(); }
    public Patient getPatientById(int patientId) { return patientRepository.findById(patientId).orElse(null); }
    public void delete(int id) { patientRepository.deleteById(id); }
    public long count() { return patientRepository.count(); }
    public List<Patient> search(String name) { return patientRepository.findByPatientNameContaining(name); }
}
