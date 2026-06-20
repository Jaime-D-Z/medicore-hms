package com.hms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.hms.entity.Staff;
import com.hms.service.StaffService;
import com.hms.service.RoleService;
import com.hms.service.DepartmentService;
import com.hms.repository.RoleRepo;

@Controller
public class StaffController {
    @Autowired private StaffService service;
    @Autowired private RoleService roleService;
    @Autowired private DepartmentService departmentService;

    @GetMapping("/dashboard/staff")
    public String list(Model model) {
        model.addAttribute("staffList", service.getAll());
        model.addAttribute("staff", new Staff());
        model.addAttribute("roles", roleService.getAll());
        model.addAttribute("departments", departmentService.getAll());
        return "staffList";
    }

    @PostMapping("/dashboard/staffSave")
    public String save(@ModelAttribute Staff s, @RequestParam("roleId") int roleId,
            @RequestParam("deptId") int deptId, RedirectAttributes ra) {
        s.setRole(roleService.getById(roleId));
        s.setDepartment(departmentService.getById(deptId));
        service.save(s);
        ra.addFlashAttribute("successMessage", "Staff saved successfully");
        return "redirect:/dashboard/staff";
    }

    @GetMapping("/dashboard/editStaff/{id}")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("staff", service.getById(id));
        model.addAttribute("staffList", service.getAll());
        model.addAttribute("roles", roleService.getAll());
        model.addAttribute("departments", departmentService.getAll());
        return "staffList";
    }

    @GetMapping("/dashboard/deleteStaff/{id}")
    public String delete(@PathVariable int id, RedirectAttributes ra) {
        service.delete(id);
        ra.addFlashAttribute("successMessage", "Staff deleted successfully");
        return "redirect:/dashboard/staff";
    }
}
