package com.hms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import com.hms.entity.Role;
import com.hms.entity.User;
import com.hms.repository.EmpRepo;
import com.hms.repository.RoleRepo;

@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private EmpRepo userRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (roleRepo.count() == 0) {
            roleRepo.save(new Role("ADMIN"));
            roleRepo.save(new Role("DOCTOR"));
            roleRepo.save(new Role("NURSE"));
            roleRepo.save(new Role("RECEPTIONIST"));
        }

        if (userRepo.findByUserName("admin") == null) {
            User admin = new User();
            admin.setUserName("admin");
            admin.setFullName("Administrator");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setEmail("admin@hospital.com");
            admin.setDesignation("System Administrator");
            admin.setEnabled(true);
            admin.getRoles().add(roleRepo.findByRoleName("ADMIN"));
            userRepo.save(admin);
        }
    }
}
