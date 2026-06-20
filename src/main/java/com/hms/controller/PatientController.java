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
import com.hms.entity.Patient;
import com.hms.service.PatientService;
import com.hms.service.MedicalRecordService;

@Controller
public class PatientController {
    @Autowired
    private PatientService patientService;

    @Autowired
    private MedicalRecordService medicalRecordService;

    @GetMapping("/dashboard/pregister")
    public String showPatientRegistrationForm(Model model) {
        List<Patient> latestPatients = patientService.getLatestPatients(5);
        model.addAttribute("latestPatients", latestPatients);
        model.addAttribute("patient", new Patient());
        return "patientRegistration";
    }

    @PostMapping("/dashboard/pregistersubmit")
    public String registerPatient(@ModelAttribute Patient patient, RedirectAttributes redirectAttributes) {
        patientService.addPatient(patient);
        redirectAttributes.addFlashAttribute("successMessage", "Patient registered successfully");
        return "redirect:/dashboard/pregister";
    }

    @GetMapping("/dashboard/editPatient/{id}")
    public String editPatient(@PathVariable int id, Model model) {
        Patient patient = patientService.getPatientById(id);
        model.addAttribute("patient", patient);
        model.addAttribute("latestPatients", patientService.getLatestPatients(5));
        return "patientRegistration";
    }

    @GetMapping("/dashboard/deletePatient/{id}")
    public String deletePatient(@PathVariable int id, RedirectAttributes redirectAttributes) {
        patientService.delete(id);
        redirectAttributes.addFlashAttribute("successMessage", "Patient deleted successfully");
        return "redirect:/dashboard/pregister";
    }

    @GetMapping("/dashboard/patientList")
    public String patientList(Model model) {
        model.addAttribute("patients", patientService.getAllPatients());
        return "patientList";
    }

    @GetMapping("/dashboard/searchPatient")
    public String searchPatient(@RequestParam("name") String name, Model model) {
        model.addAttribute("patients", patientService.search(name));
        return "patientList";
    }

    @GetMapping("/dashboard/history")
    public String viewPatientHistory(@RequestParam(value = "id", required = false) Integer id, Model model) {
        model.addAttribute("patients", patientService.getAllPatients());
        if (id != null) {
            model.addAttribute("patient", patientService.getPatientById(id));
            model.addAttribute("records", medicalRecordService.getByPatient(id));
        }
        return "patient_history";
    }
}
