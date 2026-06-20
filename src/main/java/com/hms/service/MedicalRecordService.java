package com.hms.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hms.entity.MedicalRecord;
import com.hms.repository.MedicalRecordRepo;

@Service
public class MedicalRecordService {
    @Autowired
    private MedicalRecordRepo repo;

    public void save(MedicalRecord r) { repo.save(r); }
    public List<MedicalRecord> getAll() { return repo.findAll(); }
    public MedicalRecord getById(int id) { return repo.findById(id).orElse(null); }
    public void delete(int id) { repo.deleteById(id); }
    public List<MedicalRecord> getByPatient(int patientId) { return repo.findByPatientPatientId(patientId); }
}
