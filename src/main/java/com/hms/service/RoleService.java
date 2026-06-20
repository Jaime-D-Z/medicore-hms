package com.hms.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hms.entity.Role;
import com.hms.repository.RoleRepo;

@Service
public class RoleService {
    @Autowired
    private RoleRepo repo;

    public List<Role> getAll() { return repo.findAll(); }
    public Role getById(int id) { return repo.findById(id).orElse(null); }
    public Role findByName(String name) { return repo.findByRoleName(name); }
}
