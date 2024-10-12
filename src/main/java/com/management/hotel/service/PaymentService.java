package com.management.hotel.service;

import com.management.hotel.model.Booking;
import com.management.hotel.model.Payment;
import com.management.hotel.repository.BookingRepository;
import com.management.hotel.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private BookingRepository bookingRepository;
    public Payment createPayment(Long bookingId, Long amount, Date date, String method){
        Optional<Booking> optionalBooking = bookingRepository.findById(bookingId);
        if(optionalBooking.isEmpty()){
            throw new IllegalArgumentException(String.format("Booking with Id: %s does not exist.", bookingId));
        }
        Payment payment = new Payment(optionalBooking.get(),amount,date, method);
        return paymentRepository.save(payment);
    }
    public Payment updatePayment(Long id, Map<String, Object> updatePayment){
        return paymentRepository.findById(id).map(payment1 -> {
            updatePayment.forEach((key, value) -> {
                if(key.equals("amount")){
                    payment1.setAmount((Long) value);
                } else if (key.equals("date")) {
                    payment1.setDate((Date) value);
                } else if (key.equals("bookingId") && (Long) value != payment1.getBooking().getId()) {
                    Optional<Booking> optionalBooking = bookingRepository.findById((Long) value);
                    if(optionalBooking.isEmpty()){
                        throw new IllegalArgumentException(String.format("Booking with Id: %s does not exist.", (Long) value));
                    }
                    payment1.setBooking(optionalBooking.get());
                } else if (key.equals("method")) {
                    payment1.setMethod((String) value);
                }
            });
            return paymentRepository.save(payment1);
        }).orElseThrow(() -> new RuntimeException("Payment not found"));
    }
    public void deletePayment(Long id){
        paymentRepository.deleteById(id);
    }
    public Optional<Payment> getPayment(Long id){
        return paymentRepository.findById(id);
    }
    public List<Payment> getAllPayments(){
        return paymentRepository.findAll();
    }
}
