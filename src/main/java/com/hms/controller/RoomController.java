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
import com.hms.entity.Room;
import com.hms.service.RoomService;
import com.hms.service.DepartmentService;

@Controller
public class RoomController {
    @Autowired private RoomService service;
    @Autowired private DepartmentService departmentService;

    @GetMapping("/dashboard/rooms")
    public String list(Model model) {
        model.addAttribute("rooms", service.getAll());
        model.addAttribute("room", new Room());
        model.addAttribute("departments", departmentService.getAll());
        return "roomList";
    }

    @PostMapping("/dashboard/roomSave")
    public String save(@ModelAttribute Room r, @RequestParam(value = "deptId", required = false) Integer deptId, RedirectAttributes ra) {
        if (deptId != null) r.setDepartment(departmentService.getById(deptId));
        service.save(r);
        ra.addFlashAttribute("successMessage", "Room saved successfully");
        return "redirect:/dashboard/rooms";
    }

    @GetMapping("/dashboard/editRoom/{id}")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("room", service.getById(id));
        model.addAttribute("rooms", service.getAll());
        model.addAttribute("departments", departmentService.getAll());
        return "roomList";
    }

    @GetMapping("/dashboard/deleteRoom/{id}")
    public String delete(@PathVariable int id, RedirectAttributes ra) {
        service.delete(id);
        ra.addFlashAttribute("successMessage", "Room deleted successfully");
        return "redirect:/dashboard/rooms";
    }
}
