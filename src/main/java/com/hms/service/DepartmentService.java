package com.hms.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hms.entity.Department;
import com.hms.repository.DepartmentRepo;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepo repo;

    public void save(Department dept) { repo.save(dept); }
    public List<Department> getAll() { return repo.findAll(); }
    public Department getById(int id) { return repo.findById(id).orElse(null); }
    public void delete(int id) { repo.deleteById(id); }
}
