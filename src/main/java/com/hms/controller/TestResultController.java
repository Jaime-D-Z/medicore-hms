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
import com.hms.entity.TestResult;
import com.hms.service.TestResultService;
import com.hms.service.LabTestService;
import com.hms.service.PatientService;
import com.hms.service.DoctorService;

@Controller
public class TestResultController {
    @Autowired private TestResultService service;
    @Autowired private LabTestService labTestService;
    @Autowired private PatientService patientService;
    @Autowired private DoctorService doctorService;

    @GetMapping("/dashboard/testResults")
    public String list(Model model) {
        model.addAttribute("results", service.getAll());
        model.addAttribute("testResult", new TestResult());
        model.addAttribute("labTests", labTestService.getAll());
        model.addAttribute("patients", patientService.getAllPatients());
        model.addAttribute("doctors", doctorService.getAllDoctors());
        return "testResultList";
    }

    @PostMapping("/dashboard/testResultSave")
    public String save(@ModelAttribute TestResult r, @RequestParam("testId") int testId,
            @RequestParam("patientId") int patientId, @RequestParam("doctorId") int doctorId,
            RedirectAttributes ra) {
        r.setLabTest(labTestService.getById(testId));
        r.setPatient(patientService.getPatientById(patientId));
        r.setDoctor(doctorService.getDoctorById(doctorId));
        service.save(r);
        ra.addFlashAttribute("successMessage", "Test result saved successfully");
        return "redirect:/dashboard/testResults";
    }

    @GetMapping("/dashboard/editTestResult/{id}")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("testResult", service.getById(id));
        model.addAttribute("results", service.getAll());
        model.addAttribute("labTests", labTestService.getAll());
        model.addAttribute("patients", patientService.getAllPatients());
        model.addAttribute("doctors", doctorService.getAllDoctors());
        return "testResultList";
    }

    @GetMapping("/dashboard/deleteTestResult/{id}")
    public String delete(@PathVariable int id, RedirectAttributes ra) {
        service.delete(id);
        ra.addFlashAttribute("successMessage", "Test result deleted successfully");
        return "redirect:/dashboard/testResults";
    }
}
