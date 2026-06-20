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
import com.hms.entity.Stock;
import com.hms.service.StockService;
import com.hms.service.MedicineService;
import com.hms.service.SupplierService;

@Controller
public class StockController {
    @Autowired private StockService service;
    @Autowired private MedicineService medicineService;
    @Autowired private SupplierService supplierService;

    @GetMapping("/dashboard/stock")
    public String list(Model model) {
        model.addAttribute("stockList", service.getAll());
        model.addAttribute("stock", new Stock());
        model.addAttribute("medicines", medicineService.getAll());
        model.addAttribute("suppliers", supplierService.getAll());
        return "stockList";
    }

    @PostMapping("/dashboard/stockSave")
    public String save(@ModelAttribute Stock s, @RequestParam("medicineId") int medId,
            @RequestParam("supplierId") int supId, RedirectAttributes ra) {
        s.setMedicine(medicineService.getById(medId));
        s.setSupplier(supplierService.getById(supId));
        service.save(s);
        ra.addFlashAttribute("successMessage", "Stock saved successfully");
        return "redirect:/dashboard/stock";
    }

    @GetMapping("/dashboard/editStock/{id}")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("stock", service.getById(id));
        model.addAttribute("stockList", service.getAll());
        model.addAttribute("medicines", medicineService.getAll());
        model.addAttribute("suppliers", supplierService.getAll());
        return "stockList";
    }

    @GetMapping("/dashboard/deleteStock/{id}")
    public String delete(@PathVariable int id, RedirectAttributes ra) {
        service.delete(id);
        ra.addFlashAttribute("successMessage", "Stock entry deleted successfully");
        return "redirect:/dashboard/stock";
    }
}
