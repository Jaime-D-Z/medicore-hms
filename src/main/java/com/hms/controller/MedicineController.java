package com.hms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.hms.entity.Medicine;
import com.hms.service.MedicineService;

@Controller
public class MedicineController {
    @Autowired private MedicineService service;

    @GetMapping("/dashboard/medicines")
    public String list(Model model) {
        model.addAttribute("medicines", service.getAll());
        model.addAttribute("medicine", new Medicine());
        return "medicineList";
    }

    @PostMapping("/dashboard/medicineSave")
    public String save(@ModelAttribute Medicine m, RedirectAttributes ra) {
        service.save(m);
        ra.addFlashAttribute("successMessage", "Medicine saved successfully");
        return "redirect:/dashboard/medicines";
    }

    @GetMapping("/dashboard/editMedicine/{id}")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("medicine", service.getById(id));
        model.addAttribute("medicines", service.getAll());
        return "medicineList";
    }

    @GetMapping("/dashboard/deleteMedicine/{id}")
    public String delete(@PathVariable int id, RedirectAttributes ra) {
        service.delete(id);
        ra.addFlashAttribute("successMessage", "Medicine deleted successfully");
        return "redirect:/dashboard/medicines";
    }
}
