package org.example.entities;

import java.util.List;
import java.time.LocalDate;

public class Booking {
    private String id;
    private Guest guest;
    private List<Room> rooms;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private double totalAmount;

    public Booking(String id, Guest guest, List<Room> rooms, LocalDate checkIn, LocalDate checkOut) {
        this.id = id;
        this.guest = guest;
        this.rooms = rooms;
        this.checkInDate = checkIn;
        this.checkOutDate = checkOut;
    }

    public String getId() {
        return id;
    }

    public List<Room> getRooms() {
        return rooms;
    }
    
    public void setTotalAmount(double total) {
        this.totalAmount = total;
    }
    
    public double getTotalAmount() {
        return totalAmount;
    }
}
