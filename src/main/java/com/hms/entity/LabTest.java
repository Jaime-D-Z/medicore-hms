package com.hms.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "LAB_TEST")
public class LabTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int testId;
    private String testName;
    private String category;
    private String description;
    private double price;

    public LabTest() {}

    public int getTestId() { return testId; }
    public void setTestId(int testId) { this.testId = testId; }
    public String getTestName() { return testName; }
    public void setTestName(String testName) { this.testName = testName; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}
