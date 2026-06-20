package com.hms.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hms.entity.Payment;
import com.hms.repository.PaymentRepo;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepo repo;

    public void save(Payment p) { repo.save(p); }
    public List<Payment> getAll() { return repo.findAll(); }
    public Payment getById(int id) { return repo.findById(id).orElse(null); }
    public void delete(int id) { repo.deleteById(id); }
    public long countPaid() { return repo.countByStatus("PAID"); }
    public long countPending() { return repo.countByStatus("PENDING"); }
}
