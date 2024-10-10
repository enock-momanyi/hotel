package com.management.hotel.service;

import com.management.hotel.model.Booking;
import com.management.hotel.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class BookingService {
    private BookingRepository bookingRepository;
    public Booking createBooking(Booking booking){
        return bookingRepository.save(booking);
    }
    public Booking updateBooking(Long id, Booking booking){
        return bookingRepository.findById(id).map(booking1 -> {
            booking1.setAmount(booking.getAmount());
            booking1.setCheckinDate(booking.getCheckinDate());
            booking1.setCheckoutDate(booking.getCheckoutDate());
            booking1.setStatus(booking.getStatus());
            booking1.setCustomer(booking.getCustomer());
            booking1.setPlan(booking.getPlan());
            booking1.setRoom(booking.getRoom());
            return bookingRepository.save(booking1);
        }).orElseThrow(() -> new RuntimeException("Booking not found"));
    }
    public void deleteBooking(Long id){
        bookingRepository.deleteById(id);
    }
    public Optional<Booking> getBooking(Long id){
        return bookingRepository.findById(id);
    }
    public List<Booking> getAllBookings(){
        return bookingRepository.findAll();
    }
}
