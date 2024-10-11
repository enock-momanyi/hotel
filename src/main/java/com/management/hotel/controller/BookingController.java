package com.management.hotel.controller;

import com.management.hotel.dto.BookingRequestDTO;
import com.management.hotel.model.Booking;
import com.management.hotel.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @GetMapping
    public List<Booking> getAllBookings(){
        return bookingService.getAllBookings();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Long id){
        return bookingService.getBooking(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<?> createBooking(@RequestBody BookingRequestDTO bookingRequestDTO){
        try {
            Booking newBooking = bookingService.createBooking(bookingRequestDTO.getCustomerId(),
                    bookingRequestDTO.getRoomId(), bookingRequestDTO.getPlanId(), bookingRequestDTO.getCheckinDate(),
                    bookingRequestDTO.getCheckoutDate(), bookingRequestDTO.getAmount(), bookingRequestDTO.getStatus());
            return ResponseEntity.ok(newBooking);
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id){
        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }
}
