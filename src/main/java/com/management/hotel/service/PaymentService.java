package com.management.hotel.service;

import com.management.hotel.model.Booking;
import com.management.hotel.model.Payment;
import com.management.hotel.repository.BookingRepository;
import com.management.hotel.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
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
    public Payment updatePayment(Long id,Payment payment){
        return paymentRepository.findById(id).map(payment1 -> {
            payment1.setAmount(payment.getAmount());
            payment1.setDate(payment.getDate());
            payment1.setBooking(payment.getBooking());
            payment1.setMethod(payment.getMethod());
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
