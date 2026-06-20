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
import com.hms.entity.Patient;
import com.hms.entity.Prescription;
import com.hms.service.DoctorService;
import com.hms.service.PatientService;
import com.hms.service.PrescriptionService;

@Controller
public class PrescriptionController {
    @Autowired
    private PrescriptionService prService;
    @Autowired
    private PatientService patientService;
    @Autowired
    private DoctorService doctorService;

    @GetMapping("/dashboard/prsRegistration")
    public String showPrescriptionRegistration(Model model) {
        model.addAttribute("patientList", patientService.getAllPatients());
        model.addAttribute("doctorList", doctorService.getAllDoctors());
        model.addAttribute("latestPrescriptions", prService.getLatestPrescriptions(5));
        model.addAttribute("prescription", new Prescription());
        return "prescriptionRegistration";
    }

    @PostMapping("/dashboard/prsregistersubmit")
    public String registerPrescription(@ModelAttribute Prescription prescription,
            @RequestParam("doctorId") int doctorId, @RequestParam("patientId") int patientId,
            RedirectAttributes redirectAttributes) {
        Doctor doctor = doctorService.getDoctorById(doctorId);
        Patient patient = patientService.getPatientById(patientId);
        prescription.setDoctor(doctor);
        prescription.setPatient(patient);
        prService.addPrescription(prescription);
        redirectAttributes.addFlashAttribute("successMessage", "Prescription registered successfully");
        return "redirect:/dashboard/prsRegistration";
    }

    @GetMapping("/dashboard/editPrescription/{id}")
    public String editPrescription(@PathVariable int id, Model model) {
        Prescription prescription = prService.getPrescriptionById(id);
        model.addAttribute("prescription", prescription);
        model.addAttribute("patientList", patientService.getAllPatients());
        model.addAttribute("doctorList", doctorService.getAllDoctors());
        model.addAttribute("latestPrescriptions", prService.getLatestPrescriptions(5));
        return "prescriptionRegistration";
    }

    @GetMapping("/dashboard/deletePrescription/{id}")
    public String deletePrescription(@PathVariable int id, RedirectAttributes redirectAttributes) {
        prService.delete(id);
        redirectAttributes.addFlashAttribute("successMessage", "Prescription deleted successfully");
        return "redirect:/dashboard/prsRegistration";
    }

    @GetMapping("/dashboard/prescriptionList")
    public String prescriptionList(Model model) {
        model.addAttribute("prescriptions", prService.getAllPrescriptions());
        return "prescriptionList";
    }
}
