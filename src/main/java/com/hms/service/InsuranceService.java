package com.hms.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hms.entity.Insurance;
import com.hms.repository.InsuranceRepo;

@Service
public class InsuranceService {
    @Autowired
    private InsuranceRepo repo;

    public void save(Insurance i) { repo.save(i); }
    public List<Insurance> getAll() { return repo.findAll(); }
    public Insurance getById(int id) { return repo.findById(id).orElse(null); }
    public void delete(int id) { repo.deleteById(id); }
    public List<Insurance> getByPatient(int patientId) { return repo.findByPatientPatientId(patientId); }
}
