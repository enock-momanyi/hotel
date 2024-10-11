package com.management.hotel.service;

import com.management.hotel.model.Booking;
import com.management.hotel.model.Customer;
import com.management.hotel.model.Plan;
import com.management.hotel.model.Room;
import com.management.hotel.repository.BookingRepository;
import com.management.hotel.repository.CustomerRepository;
import com.management.hotel.repository.PlanRepository;
import com.management.hotel.repository.RoomRepository;
import com.management.hotel.util.DateTimeAdjuster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private PlanRepository planRepository;
    public Booking createBooking(Long customerId, Long roomId, Long planId,
                                 Date checkinDate, Date checkoutDate, Long amount, String status){
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if(optionalCustomer.isEmpty()){
            throw new IllegalArgumentException(String.format("Customer with Id: %s does not exist", customerId));
        }
        Optional<Room> optionalRoom = roomRepository.findById(roomId);
        if(optionalRoom.isEmpty()){
            throw new IllegalArgumentException(String.format("Room with Id: %s does not exist", roomId));
        }
        if(!optionalRoom.get().isAvailable()){
            throw  new IllegalArgumentException(String.format("Room with Id: %s is not available", roomId));
        }
        Optional<Plan> optionalPlan = planRepository.findById(planId);
        if(optionalPlan.isEmpty()){
            throw new IllegalArgumentException(String.format("Plan with id: %s does not exist", planId));
        }
        Room room = optionalRoom.get();
        room.setAvailable(false);
        Booking booking = new Booking(optionalCustomer.get(),room,
                optionalPlan.get(), DateTimeAdjuster.checkinDate(checkinDate),
                DateTimeAdjuster.checkoutDate(checkoutDate),amount,status);
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
