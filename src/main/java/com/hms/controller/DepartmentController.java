package com.hms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.hms.entity.Department;
import com.hms.service.DepartmentService;

@Controller
public class DepartmentController {
    @Autowired
    private DepartmentService service;

    @GetMapping("/dashboard/departments")
    public String list(Model model) {
        model.addAttribute("departments", service.getAll());
        model.addAttribute("department", new Department());
        return "departmentList";
    }

    @PostMapping("/dashboard/departmentSave")
    public String save(@ModelAttribute Department dept, RedirectAttributes ra) {
        service.save(dept);
        ra.addFlashAttribute("successMessage", "Department saved successfully");
        return "redirect:/dashboard/departments";
    }

    @GetMapping("/dashboard/editDepartment/{id}")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("department", service.getById(id));
        model.addAttribute("departments", service.getAll());
        return "departmentList";
    }

    @GetMapping("/dashboard/deleteDepartment/{id}")
    public String delete(@PathVariable int id, RedirectAttributes ra) {
        service.delete(id);
        ra.addFlashAttribute("successMessage", "Department deleted successfully");
        return "redirect:/dashboard/departments";
    }
}
