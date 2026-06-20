package com.hms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.hms.entity.Supplier;
import com.hms.service.SupplierService;

@Controller
public class SupplierController {
    @Autowired private SupplierService service;

    @GetMapping("/dashboard/suppliers")
    public String list(Model model) {
        model.addAttribute("suppliers", service.getAll());
        model.addAttribute("supplier", new Supplier());
        return "supplierList";
    }

    @PostMapping("/dashboard/supplierSave")
    public String save(@ModelAttribute Supplier s, RedirectAttributes ra) {
        service.save(s);
        ra.addFlashAttribute("successMessage", "Supplier saved successfully");
        return "redirect:/dashboard/suppliers";
    }

    @GetMapping("/dashboard/editSupplier/{id}")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("supplier", service.getById(id));
        model.addAttribute("suppliers", service.getAll());
        return "supplierList";
    }

    @GetMapping("/dashboard/deleteSupplier/{id}")
    public String delete(@PathVariable int id, RedirectAttributes ra) {
        service.delete(id);
        ra.addFlashAttribute("successMessage", "Supplier deleted successfully");
        return "redirect:/dashboard/suppliers";
    }
}
