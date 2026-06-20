package com.hms.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "PATIENT_BILL")
public class PatientBill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int billId;

    @OneToOne
    @JoinColumn(name = "prescription_id")
    private Prescription prescription;

    private double medicineCost;
    private double roomCharge;
    private double serviceCharge;
    private double testCharge;
    private double totalCost;
    private LocalDate billDate;
    private String status;

    public PatientBill() {
        this.billDate = LocalDate.now();
        this.status = "UNPAID";
    }

    public int getBillId() { return billId; }
    public void setBillId(int billId) { this.billId = billId; }
    public Prescription getPrescription() { return prescription; }
    public void setPrescription(Prescription prescription) { this.prescription = prescription; }
    public double getMedicineCost() { return medicineCost; }
    public void setMedicineCost(double medicineCost) { this.medicineCost = medicineCost; }
    public double getRoomCharge() { return roomCharge; }
    public void setRoomCharge(double roomCharge) { this.roomCharge = roomCharge; }
    public double getServiceCharge() { return serviceCharge; }
    public void setServiceCharge(double serviceCharge) { this.serviceCharge = serviceCharge; }
    public double getTestCharge() { return testCharge; }
    public void setTestCharge(double testCharge) { this.testCharge = testCharge; }
    public double getTotalCost() { return totalCost; }
    public void setTotalCost(double totalCost) { this.totalCost = totalCost; }
    public LocalDate getBillDate() { return billDate; }
    public void setBillDate(LocalDate billDate) { this.billDate = billDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
