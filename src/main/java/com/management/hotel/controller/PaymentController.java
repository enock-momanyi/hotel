package com.management.hotel.controller;

import com.management.hotel.dto.PaymentRequestDTO;
import com.management.hotel.model.Payment;
import com.management.hotel.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @GetMapping
    public List<Payment> getAllPayments(){
        return paymentService.getAllPayments();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long id){
        return paymentService.getPayment(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<?> createPayment(@RequestBody PaymentRequestDTO paymentRequestDTO){
        try {
            Payment newPayment = paymentService.createPayment(paymentRequestDTO.getBookingId(),
                    paymentRequestDTO.getAmount(), paymentRequestDTO.getDate(), paymentRequestDTO.getMethod());
            return ResponseEntity.ok(newPayment);
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id){
        paymentService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }
    @PatchMapping("/{id}")
    public ResponseEntity<?> updatePayment(@PathVariable Long id, @RequestBody Map<String, Object> paymentUpdate){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.updatePayment(id, paymentUpdate));
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(e.getMessage());
        }
    }
}
