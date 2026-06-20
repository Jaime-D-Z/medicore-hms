package com.hms.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hms.entity.TestResult;
import com.hms.repository.TestResultRepo;

@Service
public class TestResultService {
    @Autowired
    private TestResultRepo repo;

    public void save(TestResult r) { repo.save(r); }
    public List<TestResult> getAll() { return repo.findAll(); }
    public TestResult getById(int id) { return repo.findById(id).orElse(null); }
    public void delete(int id) { repo.deleteById(id); }
    public List<TestResult> getByPatient(int patientId) { return repo.findByPatientPatientId(patientId); }
}
