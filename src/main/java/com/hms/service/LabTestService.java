package com.hms.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hms.entity.LabTest;
import com.hms.repository.LabTestRepo;

@Service
public class LabTestService {
    @Autowired
    private LabTestRepo repo;

    public void save(LabTest t) { repo.save(t); }
    public List<LabTest> getAll() { return repo.findAll(); }
    public LabTest getById(int id) { return repo.findById(id).orElse(null); }
    public void delete(int id) { repo.deleteById(id); }
}
