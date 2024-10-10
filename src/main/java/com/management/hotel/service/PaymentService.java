package com.management.hotel.service;

import com.management.hotel.model.Payment;
import com.management.hotel.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PaymentService {
    private PaymentRepository paymentRepository;
    public Payment createPayment(Payment payment){
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
