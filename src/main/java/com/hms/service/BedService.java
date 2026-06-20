package com.hms.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hms.entity.Bed;
import com.hms.repository.BedRepo;

@Service
public class BedService {
    @Autowired
    private BedRepo repo;

    public void save(Bed b) { repo.save(b); }
    public List<Bed> getAll() { return repo.findAll(); }
    public Bed getById(int id) { return repo.findById(id).orElse(null); }
    public void delete(int id) { repo.deleteById(id); }
    public long countAvailable() { return repo.countByStatus("AVAILABLE"); }
    public long countOccupied() { return repo.countByStatus("OCCUPIED"); }
}
