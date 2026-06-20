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
import com.hms.entity.Appointment;
import com.hms.service.AppointmentService;
import com.hms.service.PatientService;
import com.hms.service.DoctorService;

@Controller
public class AppointmentController {
    @Autowired private AppointmentService service;
    @Autowired private PatientService patientService;
    @Autowired private DoctorService doctorService;

    @GetMapping("/dashboard/appointments")
    public String list(Model model) {
        model.addAttribute("appointments", service.getAll());
        model.addAttribute("appointment", new Appointment());
        model.addAttribute("patients", patientService.getAllPatients());
        model.addAttribute("doctors", doctorService.getAllDoctors());
        return "appointmentList";
    }

    @PostMapping("/dashboard/appointmentSave")
    public String save(@ModelAttribute Appointment a,
            @RequestParam("patientId") int patientId, @RequestParam("doctorId") int doctorId,
            RedirectAttributes ra) {
        a.setPatient(patientService.getPatientById(patientId));
        a.setDoctor(doctorService.getDoctorById(doctorId));
        service.save(a);
        ra.addFlashAttribute("successMessage", "Appointment saved successfully");
        return "redirect:/dashboard/appointments";
    }

    @GetMapping("/dashboard/editAppointment/{id}")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("appointment", service.getById(id));
        model.addAttribute("appointments", service.getAll());
        model.addAttribute("patients", patientService.getAllPatients());
        model.addAttribute("doctors", doctorService.getAllDoctors());
        return "appointmentList";
    }

    @GetMapping("/dashboard/deleteAppointment/{id}")
    public String delete(@PathVariable int id, RedirectAttributes ra) {
        service.delete(id);
        ra.addFlashAttribute("successMessage", "Appointment deleted successfully");
        return "redirect:/dashboard/appointments";
    }

    @GetMapping("/dashboard/completeAppointment/{id}")
    public String complete(@PathVariable int id, RedirectAttributes ra) {
        Appointment a = service.getById(id);
        if (a != null) { a.setStatus("COMPLETED"); service.save(a); }
        ra.addFlashAttribute("successMessage", "Appointment completed");
        return "redirect:/dashboard/appointments";
    }

    @GetMapping("/dashboard/cancelAppointment/{id}")
    public String cancel(@PathVariable int id, RedirectAttributes ra) {
        Appointment a = service.getById(id);
        if (a != null) { a.setStatus("CANCELLED"); service.save(a); }
        ra.addFlashAttribute("successMessage", "Appointment cancelled");
        return "redirect:/dashboard/appointments";
    }
}
