package com.hms.controller;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.hms.service.PatientService;
import com.hms.service.DoctorService;
import com.hms.service.PrescriptionService;
import com.hms.service.PatientBillService;
import com.hms.service.AppointmentService;
import com.hms.service.RoomService;
import com.hms.service.BedService;

@Controller
public class DashboardController {
    @Autowired private PatientService patientService;
    @Autowired private DoctorService doctorService;
    @Autowired private PrescriptionService prescriptionService;
    @Autowired private PatientBillService patientBillService;
    @Autowired private AppointmentService appointmentService;
    @Autowired private RoomService roomService;
    @Autowired private BedService bedService;

    @GetMapping("/dashboard")
    public String dashboard(Model model, Authentication auth) {
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        boolean isAdmin = authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        boolean isDoctor = authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_DOCTOR"));
        boolean isReceptionist = authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_RECEPTIONIST"));
        boolean isNurse = authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_NURSE"));

        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("isDoctor", isDoctor);
        model.addAttribute("isReceptionist", isReceptionist);
        model.addAttribute("isNurse", isNurse);

        if (isAdmin || isReceptionist) {
            model.addAttribute("totalPatients", patientService.count());
            model.addAttribute("totalDoctors", doctorService.count());
            model.addAttribute("totalPrescriptions", prescriptionService.count());
            model.addAttribute("totalBills", patientBillService.count());
            model.addAttribute("pendingBills", patientBillService.countByStatus("UNPAID"));
            model.addAttribute("paidBills", patientBillService.countByStatus("PAID"));
            model.addAttribute("scheduledAppointments", appointmentService.countByStatus("SCHEDULED"));
            model.addAttribute("completedAppointments", appointmentService.countByStatus("COMPLETED"));
            model.addAttribute("cancelledAppointments", appointmentService.countByStatus("CANCELLED"));
            model.addAttribute("availableRooms", roomService.countAvailable());
            model.addAttribute("totalRooms", roomService.countTotal());
            model.addAttribute("availableBeds", bedService.countAvailable());
            model.addAttribute("occupiedBeds", bedService.countOccupied());
        } else if (isDoctor) {
            model.addAttribute("totalPatients", patientService.count());
            model.addAttribute("totalPrescriptions", prescriptionService.count());
            model.addAttribute("scheduledAppointments", appointmentService.countByStatus("SCHEDULED"));
            model.addAttribute("completedAppointments", appointmentService.countByStatus("COMPLETED"));
            model.addAttribute("cancelledAppointments", appointmentService.countByStatus("CANCELLED"));
        } else if (isNurse) {
            model.addAttribute("totalPatients", patientService.count());
            model.addAttribute("availableRooms", roomService.countAvailable());
            model.addAttribute("totalRooms", roomService.countTotal());
            model.addAttribute("availableBeds", bedService.countAvailable());
            model.addAttribute("occupiedBeds", bedService.countOccupied());
            model.addAttribute("scheduledAppointments", appointmentService.countByStatus("SCHEDULED"));
            model.addAttribute("completedAppointments", appointmentService.countByStatus("COMPLETED"));
            model.addAttribute("cancelledAppointments", appointmentService.countByStatus("CANCELLED"));
        }
        return "dashboard";
    }
}
