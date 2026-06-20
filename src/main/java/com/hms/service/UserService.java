package com.hms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hms.entity.User;
import com.hms.repository.EmpRepo;

@Service
public class UserService {
    @Autowired
    private EmpRepo repo;

    public void addUser(User u) { repo.save(u); }
    public User findByUserName(String userName) { return repo.findByUserName(userName); }
    public User getById(int id) { return repo.findById(id).orElse(null); }
    public java.util.List<User> getAll() { return repo.findAll(); }
    public void delete(int id) { repo.deleteById(id); }
}
