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
import java.util.Map;
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
    public Booking updateBooking(Long id, Map<String, Object> bookingUpdate){
        return bookingRepository.findById(id).map(booking1 -> {
            bookingUpdate.forEach((key, value) -> {
                if(key.equals("amount")){
                    booking1.setAmount((Long) value);
                } else if (key.equals("checkinDate")) {
                    booking1.setCheckinDate(DateTimeAdjuster.checkinDate((Date) value));
                } else if (key.equals("checkoutDate")) {
                    booking1.setCheckoutDate(DateTimeAdjuster.checkoutDate((Date) value));
                } else if (key.equals("customerId") && (Long) value != booking1.getCustomer().getId()) {
                    Optional<Customer> optionalCustomer = customerRepository.findById((Long) value);
                    if(optionalCustomer.isEmpty()){
                        throw new IllegalArgumentException(String.format("Customer with Id: %s does not exist", (Long) value));
                    }
                    booking1.setCustomer(optionalCustomer.get());
                } else if (key.equals("planId") && (Long) value != booking1.getPlan().getId()) {
                    Optional<Plan> optionalPlan = planRepository.findById((Long) value);
                    if(optionalPlan.isEmpty()){
                        throw new IllegalArgumentException(String.format("Plan with id: %s does not exist", (Long) value));
                    }
                    booking1.setPlan(optionalPlan.get());
                } else if (key.equals("roomId") && (Long) value != booking1.getRoom().getId()) {
                    Optional<Room> optionalRoom = roomRepository.findById((Long) value);
                    if(optionalRoom.isEmpty()){
                        throw new IllegalArgumentException(String.format("Room with Id: %s does not exist", (Long) value));
                    }
                    if(!optionalRoom.get().isAvailable()){
                        throw  new IllegalArgumentException(String.format("Room with Id: %s is not available", (Long) value));
                    }
                    booking1.setRoom(optionalRoom.get());
                }
            });
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
