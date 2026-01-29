package org.example.repositories;

import org.example.entities.Booking;
import java.util.List;

public interface IBookingRepository {
    void save(Booking booking);
    void delete(String bookingId);
    Booking findById(String bookingId);
    List<Booking> findAll();
}
