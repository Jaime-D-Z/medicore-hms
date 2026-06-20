package com.hms.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "PATIENT")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int patientId;
    private String patientName;
    private int age;
    private String gender;
    private String phone;
    private String email;
    private String address;
    private Date admissionDate;
    private double patientAmt;

    public Patient() {}

    public int getPatientId() { return patientId; }
    public void setPatientId(int patientId) { this.patientId = patientId; }
    public String getPatientName() { return patientName; }
    public void setPatientName(String patientName) { this.patientName = patientName; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public Date getAdmissionDate() { return admissionDate; }
    public void setAdmissionDate(Date admissionDate) { this.admissionDate = admissionDate; }
    public double getPatientAmt() { return patientAmt; }
    public void setPatientAmt(double patientAmt) { this.patientAmt = patientAmt; }
}
