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
import com.hms.entity.Payment;
import com.hms.service.PaymentService;
import com.hms.service.PatientBillService;

@Controller
public class PaymentController {
    @Autowired private PaymentService service;
    @Autowired private PatientBillService billService;

    @GetMapping("/dashboard/payments")
    public String list(Model model) {
        model.addAttribute("payments", service.getAll());
        model.addAttribute("payment", new Payment());
        model.addAttribute("bills", billService.getAll());
        return "paymentList";
    }

    @PostMapping("/dashboard/paymentSave")
    public String save(@ModelAttribute Payment p, @RequestParam("billId") int billId, RedirectAttributes ra) {
        p.setPatientBill(billService.getById(billId));
        service.save(p);
        ra.addFlashAttribute("successMessage", "Payment recorded successfully");
        return "redirect:/dashboard/payments";
    }

    @GetMapping("/dashboard/deletePayment/{id}")
    public String delete(@PathVariable int id, RedirectAttributes ra) {
        service.delete(id);
        ra.addFlashAttribute("successMessage", "Payment deleted successfully");
        return "redirect:/dashboard/payments";
    }
}
