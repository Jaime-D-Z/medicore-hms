package com.hms.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "BED")
public class Bed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bedId;
    private String bedNumber;
    private String status;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @OneToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    public Bed() {
        this.status = "AVAILABLE";
    }

    public int getBedId() { return bedId; }
    public void setBedId(int bedId) { this.bedId = bedId; }
    public String getBedNumber() { return bedNumber; }
    public void setBedNumber(String bedNumber) { this.bedNumber = bedNumber; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Room getRoom() { return room; }
    public void setRoom(Room room) { this.room = room; }
    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }
}
