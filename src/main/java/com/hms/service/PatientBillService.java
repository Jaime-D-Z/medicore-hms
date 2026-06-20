package com.hms.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hms.entity.PatientBill;
import com.hms.repository.PatientBillRepo;

@Service
public class PatientBillService {
    @Autowired
    private PatientBillRepo patientBillRepo;

    public void addPatientBill(PatientBill patientBill) {
        patientBill.setTotalCost(patientBill.getMedicineCost() + patientBill.getRoomCharge()
            + patientBill.getServiceCharge() + patientBill.getTestCharge());
        patientBillRepo.save(patientBill);
    }

    public List<PatientBill> getPatientBillList(int count) { return patientBillRepo.findTop5ByOrderByBillIdDesc(); }
    public List<PatientBill> getAll() { return patientBillRepo.findAll(); }
    public PatientBill getById(int id) { return patientBillRepo.findById(id).orElse(null); }
    public void delete(int id) { patientBillRepo.deleteById(id); }
    public long count() { return patientBillRepo.count(); }
    public long countByStatus(String status) { return patientBillRepo.countByStatus(status); }
}
