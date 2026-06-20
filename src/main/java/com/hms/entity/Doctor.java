package com.hms.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "DOCTOR")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int doctorId;
    private String dName;
    private String specialization;
    private double consultCharge;
    private double salary;

    @ManyToOne
    @JoinColumn(name = "dept_id")
    private Department department;

    public Doctor() {}

    public int getDoctorId() { return doctorId; }
    public void setDoctorId(int doctorId) { this.doctorId = doctorId; }
    public String getDName() { return dName; }
    public void setDName(String dName) { this.dName = dName; }
    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }
    public double getConsultCharge() { return consultCharge; }
    public void setConsultCharge(double consultCharge) { this.consultCharge = consultCharge; }
    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }
    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }
}
