package com.hms.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ROOM")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roomId;
    private String roomNumber;
    private String roomType;
    private double chargePerDay;
    private boolean isAvailable;

    @ManyToOne
    @JoinColumn(name = "dept_id")
    private Department department;

    public Room() {
        this.isAvailable = true;
    }

    public int getRoomId() { return roomId; }
    public void setRoomId(int roomId) { this.roomId = roomId; }
    public String getRoomNumber() { return roomNumber; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }
    public String getRoomType() { return roomType; }
    public void setRoomType(String roomType) { this.roomType = roomType; }
    public double getChargePerDay() { return chargePerDay; }
    public void setChargePerDay(double chargePerDay) { this.chargePerDay = chargePerDay; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }
    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }
}
