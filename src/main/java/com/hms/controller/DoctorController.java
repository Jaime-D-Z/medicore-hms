package com.hms.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.hms.entity.Doctor;
import com.hms.service.DoctorService;
import com.hms.service.DepartmentService;

@Controller
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/dashboard/dRegistration")
    public String showDoctorRegistrationForm(Model model) {
        List<Doctor> latestDoctors = doctorService.getLatestDoctors(5);
        model.addAttribute("latestDoctors", latestDoctors);
        model.addAttribute("doctor", new Doctor());
        model.addAttribute("departments", departmentService.getAll());
        return "doctorRegistration";
    }

    @PostMapping("/dashboard/dregistersubmit")
    public String registerDoctor(@ModelAttribute Doctor doctor,
            @RequestParam(value = "deptId", required = false) Integer deptId,
            RedirectAttributes redirectAttributes) {
        if (deptId != null) {
            doctor.setDepartment(departmentService.getById(deptId));
        }
        doctorService.addDoctor(doctor);
        redirectAttributes.addFlashAttribute("successMessage", "Doctor registered successfully");
        return "redirect:/dashboard/dRegistration";
    }

    @GetMapping("/dashboard/editDoctor/{id}")
    public String editDoctor(@PathVariable int id, Model model) {
        Doctor doctor = doctorService.getDoctorById(id);
        model.addAttribute("doctor", doctor);
        model.addAttribute("latestDoctors", doctorService.getLatestDoctors(5));
        model.addAttribute("departments", departmentService.getAll());
        return "doctorRegistration";
    }

    @GetMapping("/dashboard/deleteDoctor/{id}")
    public String deleteDoctor(@PathVariable int id, RedirectAttributes redirectAttributes) {
        doctorService.delete(id);
        redirectAttributes.addFlashAttribute("successMessage", "Doctor deleted successfully");
        return "redirect:/dashboard/dRegistration";
    }

    @GetMapping("/dashboard/doctorList")
    public String doctorList(Model model) {
        model.addAttribute("doctors", doctorService.getAllDoctors());
        return "doctorList";
    }
}
