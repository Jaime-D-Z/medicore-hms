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
import com.hms.entity.MedicalRecord;
import com.hms.service.MedicalRecordService;
import com.hms.service.PatientService;
import com.hms.service.DoctorService;

@Controller
public class MedicalRecordController {
    @Autowired private MedicalRecordService service;
    @Autowired private PatientService patientService;
    @Autowired private DoctorService doctorService;

    @GetMapping("/dashboard/medicalRecords")
    public String list(Model model) {
        model.addAttribute("records", service.getAll());
        model.addAttribute("record", new MedicalRecord());
        model.addAttribute("patients", patientService.getAllPatients());
        model.addAttribute("doctors", doctorService.getAllDoctors());
        return "medicalRecordList";
    }

    @PostMapping("/dashboard/medicalRecordSave")
    public String save(@ModelAttribute MedicalRecord r, @RequestParam("patientId") int patientId,
            @RequestParam("doctorId") int doctorId, RedirectAttributes ra) {
        r.setPatient(patientService.getPatientById(patientId));
        r.setDoctor(doctorService.getDoctorById(doctorId));
        service.save(r);
        ra.addFlashAttribute("successMessage", "Medical record saved successfully");
        return "redirect:/dashboard/medicalRecords";
    }

    @GetMapping("/dashboard/deleteMedicalRecord/{id}")
    public String delete(@PathVariable int id, RedirectAttributes ra) {
        service.delete(id);
        ra.addFlashAttribute("successMessage", "Medical record deleted successfully");
        return "redirect:/dashboard/medicalRecords";
    }

    @GetMapping("/dashboard/viewMedicalRecord/{id}")
    public String view(@PathVariable int id, Model model) {
        model.addAttribute("record", service.getById(id));
        return "medicalRecordView";
    }
}
