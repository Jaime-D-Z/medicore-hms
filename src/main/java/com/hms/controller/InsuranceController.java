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
import com.hms.entity.Insurance;
import com.hms.service.InsuranceService;
import com.hms.service.PatientService;

@Controller
public class InsuranceController {
    @Autowired private InsuranceService service;
    @Autowired private PatientService patientService;

    @GetMapping("/dashboard/insurances")
    public String list(Model model) {
        model.addAttribute("insurances", service.getAll());
        model.addAttribute("insurance", new Insurance());
        model.addAttribute("patients", patientService.getAllPatients());
        return "insuranceList";
    }

    @PostMapping("/dashboard/insuranceSave")
    public String save(@ModelAttribute Insurance i, @RequestParam("patientId") int patientId, RedirectAttributes ra) {
        i.setPatient(patientService.getPatientById(patientId));
        service.save(i);
        ra.addFlashAttribute("successMessage", "Insurance saved successfully");
        return "redirect:/dashboard/insurances";
    }

    @GetMapping("/dashboard/editInsurance/{id}")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("insurance", service.getById(id));
        model.addAttribute("insurances", service.getAll());
        model.addAttribute("patients", patientService.getAllPatients());
        return "insuranceList";
    }

    @GetMapping("/dashboard/deleteInsurance/{id}")
    public String delete(@PathVariable int id, RedirectAttributes ra) {
        service.delete(id);
        ra.addFlashAttribute("successMessage", "Insurance deleted successfully");
        return "redirect:/dashboard/insurances";
    }
}
