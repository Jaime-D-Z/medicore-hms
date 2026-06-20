package com.hms.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "INSURANCE")
public class Insurance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int insuranceId;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    private String providerName;
    private String policyNumber;
    private String coverageType;
    private LocalDate startDate;
    private LocalDate endDate;

    public Insurance() {}

    public int getInsuranceId() { return insuranceId; }
    public void setInsuranceId(int insuranceId) { this.insuranceId = insuranceId; }
    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }
    public String getProviderName() { return providerName; }
    public void setProviderName(String providerName) { this.providerName = providerName; }
    public String getPolicyNumber() { return policyNumber; }
    public void setPolicyNumber(String policyNumber) { this.policyNumber = policyNumber; }
    public String getCoverageType() { return coverageType; }
    public void setCoverageType(String coverageType) { this.coverageType = coverageType; }
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
}
