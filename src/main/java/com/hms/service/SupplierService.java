package com.hms.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hms.entity.Supplier;
import com.hms.repository.SupplierRepo;

@Service
public class SupplierService {
    @Autowired
    private SupplierRepo repo;

    public void save(Supplier s) { repo.save(s); }
    public List<Supplier> getAll() { return repo.findAll(); }
    public Supplier getById(int id) { return repo.findById(id).orElse(null); }
    public void delete(int id) { repo.deleteById(id); }
}
