package org.example.controllers;

import org.example.entities.Booking;
import org.example.services.IBookingService;
import org.example.repositories.IBookingRepository;

public class BookingController {
    private IBookingService bookingService;
    private IBookingRepository bookingRepository;

    public BookingController(IBookingService bookingService, IBookingRepository bookingRepository) {
        this.bookingService = bookingService;
        this.bookingRepository = bookingRepository;
    }

    public void makeReservation(Booking booking) {
        if (bookingService.checkAvailability(booking.getRooms(), null, null)) { // Simplify for example
            double total = bookingService.calculateTotal(booking);
            bookingService.applyDiscount(booking);
            booking.setTotalAmount(total);
            bookingRepository.save(booking);
            System.out.println("Reservation made for booking: " + booking.getId());
        } else {
            System.out.println("Rooms not available.");
        }
    }

    public void cancelReservation(String bookingId) {
        bookingRepository.delete(bookingId);
        System.out.println("Reservation cancelled: " + bookingId);
    }
}
