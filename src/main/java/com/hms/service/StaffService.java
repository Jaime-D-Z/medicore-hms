package com.hms.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hms.entity.Staff;
import com.hms.repository.StaffRepo;

@Service
public class StaffService {
    @Autowired
    private StaffRepo repo;

    public void save(Staff s) { repo.save(s); }
    public List<Staff> getAll() { return repo.findAll(); }
    public Staff getById(int id) { return repo.findById(id).orElse(null); }
    public void delete(int id) { repo.deleteById(id); }
    public long countByRole(int roleId) { return repo.countByRoleRoleId(roleId); }
}
