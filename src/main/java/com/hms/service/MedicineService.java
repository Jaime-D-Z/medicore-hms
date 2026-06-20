package com.hms.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hms.entity.Medicine;
import com.hms.repository.MedicineRepo;

@Service
public class MedicineService {
    @Autowired
    private MedicineRepo repo;

    public void save(Medicine m) { repo.save(m); }
    public List<Medicine> getAll() { return repo.findAll(); }
    public Medicine getById(int id) { return repo.findById(id).orElse(null); }
    public void delete(int id) { repo.deleteById(id); }
    public List<Medicine> search(String name) { return repo.findByNameContaining(name); }
}
