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
@Table(name = "TEST_RESULT")
public class TestResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int resultId;

    @ManyToOne
    @JoinColumn(name = "test_id")
    private LabTest labTest;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    private String result;
    private String referenceRange;
    private LocalDate testDate;
    private String notes;

    public TestResult() {
        this.testDate = LocalDate.now();
    }

    public int getResultId() { return resultId; }
    public void setResultId(int resultId) { this.resultId = resultId; }
    public LabTest getLabTest() { return labTest; }
    public void setLabTest(LabTest labTest) { this.labTest = labTest; }
    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }
    public Doctor getDoctor() { return doctor; }
    public void setDoctor(Doctor doctor) { this.doctor = doctor; }
    public String getResult() { return result; }
    public void setResult(String result) { this.result = result; }
    public String getReferenceRange() { return referenceRange; }
    public void setReferenceRange(String referenceRange) { this.referenceRange = referenceRange; }
    public LocalDate getTestDate() { return testDate; }
    public void setTestDate(LocalDate testDate) { this.testDate = testDate; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
