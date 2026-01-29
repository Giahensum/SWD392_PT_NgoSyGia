package org.example.services;

import org.example.entities.Booking;
import org.example.entities.Room;
import java.time.LocalDate;
import java.util.List;

public interface IBookingService {
    boolean checkAvailability(List<Room> rooms, LocalDate checkIn, LocalDate checkOut);
    double calculateTotal(Booking booking);
    void applyDiscount(Booking booking);
}
