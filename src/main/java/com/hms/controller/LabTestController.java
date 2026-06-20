package com.hms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.hms.entity.LabTest;
import com.hms.service.LabTestService;

@Controller
public class LabTestController {
    @Autowired private LabTestService service;

    @GetMapping("/dashboard/labtests")
    public String list(Model model) {
        model.addAttribute("labTests", service.getAll());
        model.addAttribute("labTest", new LabTest());
        return "labTestList";
    }

    @PostMapping("/dashboard/labTestSave")
    public String save(@ModelAttribute LabTest t, RedirectAttributes ra) {
        service.save(t);
        ra.addFlashAttribute("successMessage", "Lab test saved successfully");
        return "redirect:/dashboard/labtests";
    }

    @GetMapping("/dashboard/editLabTest/{id}")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("labTest", service.getById(id));
        model.addAttribute("labTests", service.getAll());
        return "labTestList";
    }

    @GetMapping("/dashboard/deleteLabTest/{id}")
    public String delete(@PathVariable int id, RedirectAttributes ra) {
        service.delete(id);
        ra.addFlashAttribute("successMessage", "Lab test deleted successfully");
        return "redirect:/dashboard/labtests";
    }
}
