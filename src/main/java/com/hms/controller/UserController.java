package com.hms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.hms.entity.Role;
import com.hms.entity.User;
import com.hms.repository.RoleRepo;
import com.hms.service.DoctorService;
import com.hms.service.UserService;

@Controller
public class UserController {
    @Autowired
    private UserService srv;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private DoctorService doctorService;

    @GetMapping("/")
    public String home() { return "index"; }

    @GetMapping("/about")
    public String about() { return "about"; }

    @GetMapping("/service")
    public String service() { return "service"; }

    @GetMapping("/signin")
    public String signin() { return "signin"; }

    @GetMapping("/contact")
    public String contact() { return "contact"; }

    @GetMapping("/appointment")
    public String appointment(Model model) {
        model.addAttribute("doctors", doctorService.getAllDoctors());
        return "appointment";
    }

    @GetMapping("/feature")
    public String feature() { return "feature"; }

    @GetMapping("/team")
    public String team(Model model) {
        model.addAttribute("doctors", doctorService.getAllDoctors());
        return "team";
    }

    @GetMapping("/testimonial")
    public String testimonial() { return "testimonial"; }

    @GetMapping("/404")
    public String error404() { return "404"; }

    @GetMapping("/createaccount")
    public String createAccount(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleRepo.findAll());
        return "createaccount";
    }

    @PostMapping("/createuser")
    public String createuser(@ModelAttribute User u, Model model) {
        u.setPassword(passwordEncoder.encode(u.getPassword()));
        u.setEnabled(true);
        Role defaultRole = roleRepo.findByRoleName("RECEPTIONIST");
        u.getRoles().add(defaultRole);
        srv.addUser(u);
        model.addAttribute("msg", "Account created successfully");
        return "redirect:/signin";
    }
}
