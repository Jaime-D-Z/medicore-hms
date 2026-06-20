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
import com.hms.entity.PatientBill;
import com.hms.entity.Prescription;
import com.hms.service.PatientBillService;
import com.hms.service.PrescriptionService;

@Controller
public class PatientBillController {
    @Autowired
    private PatientBillService patientBillService;
    @Autowired
    private PrescriptionService prescriptionService;

    @GetMapping("/dashboard/patientbillRegistration")
    public String showBillRegistration(Model model) {
        model.addAttribute("prescriptionList", prescriptionService.getAllPrescriptions());
        model.addAttribute("latestpatientsBill", patientBillService.getPatientBillList(5));
        model.addAttribute("patientBill", new PatientBill());
        return "patientbillRegistration";
    }

    @PostMapping("/dashboard/billregistersubmit")
    public String registerBill(@ModelAttribute PatientBill patientBill,
            @RequestParam("prescriptionId") int pid, RedirectAttributes redirectAttributes) {
        Prescription prescription = prescriptionService.getPrescriptionById(pid);
        patientBill.setPrescription(prescription);
        patientBillService.addPatientBill(patientBill);
        redirectAttributes.addFlashAttribute("successMessage", "Patient bill registered successfully");
        return "redirect:/dashboard/patientbillRegistration";
    }

    @GetMapping("/dashboard/editBill/{id}")
    public String editBill(@PathVariable int id, Model model) {
        PatientBill bill = patientBillService.getById(id);
        model.addAttribute("patientBill", bill);
        model.addAttribute("prescriptionList", prescriptionService.getAllPrescriptions());
        model.addAttribute("latestpatientsBill", patientBillService.getPatientBillList(5));
        return "patientbillRegistration";
    }

    @GetMapping("/dashboard/deleteBill/{id}")
    public String deleteBill(@PathVariable int id, RedirectAttributes redirectAttributes) {
        patientBillService.delete(id);
        redirectAttributes.addFlashAttribute("successMessage", "Bill deleted successfully");
        return "redirect:/dashboard/patientbillRegistration";
    }

    @GetMapping("/dashboard/billList")
    public String billList(Model model) {
        model.addAttribute("bills", patientBillService.getAll());
        return "billList";
    }
}
