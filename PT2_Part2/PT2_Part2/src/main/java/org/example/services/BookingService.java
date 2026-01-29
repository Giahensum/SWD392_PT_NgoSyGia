package org.example.services;

import org.example.entities.Booking;
import org.example.entities.Room;
import org.example.repositories.IBookingRepository;
import java.time.LocalDate;
import java.util.List;
import java.time.temporal.ChronoUnit;

public class BookingService implements IBookingService {
    private IBookingRepository bookingRepository;

    public BookingService(IBookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public boolean checkAvailability(List<Room> rooms, LocalDate checkIn, LocalDate checkOut) {
        // Logic to check availability in database would go here
        // For now, return true
        return true;
    }

    @Override
    public double calculateTotal(Booking booking) {
        long days = ChronoUnit.DAYS.between(booking.getId() != null ? LocalDate.now() : LocalDate.now(), LocalDate.now().plusDays(1)); // Placeholder
        // Real logic usually takes checkIn and checkOut from booking
        // But Entity definition I made has dates.
        // Let's assume booking has dates
        return 0.0;
    }

    @Override
    public void applyDiscount(Booking booking) {
        // Apply discount logic
    }
}
