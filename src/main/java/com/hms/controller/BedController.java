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
import com.hms.entity.Bed;
import com.hms.service.BedService;
import com.hms.service.RoomService;
import com.hms.service.PatientService;

@Controller
public class BedController {
    @Autowired private BedService service;
    @Autowired private RoomService roomService;
    @Autowired private PatientService patientService;

    @GetMapping("/dashboard/beds")
    public String list(Model model) {
        model.addAttribute("beds", service.getAll());
        model.addAttribute("bed", new Bed());
        model.addAttribute("rooms", roomService.getAll());
        model.addAttribute("patients", patientService.getAllPatients());
        return "bedList";
    }

    @PostMapping("/dashboard/bedSave")
    public String save(@ModelAttribute Bed b, @RequestParam("roomId") int roomId,
            @RequestParam(value = "patientId", required = false) Integer patientId,
            RedirectAttributes ra) {
        b.setRoom(roomService.getById(roomId));
        if (patientId != null) {
            b.setPatient(patientService.getPatientById(patientId));
            b.setStatus("OCCUPIED");
        }
        service.save(b);
        ra.addFlashAttribute("successMessage", "Bed saved successfully");
        return "redirect:/dashboard/beds";
    }

    @GetMapping("/dashboard/editBed/{id}")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("bed", service.getById(id));
        model.addAttribute("beds", service.getAll());
        model.addAttribute("rooms", roomService.getAll());
        model.addAttribute("patients", patientService.getAllPatients());
        return "bedList";
    }

    @GetMapping("/dashboard/deleteBed/{id}")
    public String delete(@PathVariable int id, RedirectAttributes ra) {
        service.delete(id);
        ra.addFlashAttribute("successMessage", "Bed deleted successfully");
        return "redirect:/dashboard/beds";
    }

    @GetMapping("/dashboard/vacateBed/{id}")
    public String vacate(@PathVariable int id, RedirectAttributes ra) {
        Bed b = service.getById(id);
        if (b != null) { b.setPatient(null); b.setStatus("AVAILABLE"); service.save(b); }
        ra.addFlashAttribute("successMessage", "Bed vacated successfully");
        return "redirect:/dashboard/beds";
    }
}
