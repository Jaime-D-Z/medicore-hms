package com.hms.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.hms.entity.Payment;

public interface PaymentRepo extends JpaRepository<Payment, Integer> {
    List<Payment> findByPatientBillBillId(int billId);
    List<Payment> findByStatus(String status);
    long countByStatus(String status);
}
