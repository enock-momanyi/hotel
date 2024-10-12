package com.management.hotel.controller;

import com.management.hotel.dto.PricingRequestDTO;
import com.management.hotel.model.Pricing;
import com.management.hotel.service.PricingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/pricings")
public class PricingController {
    @Autowired
    private PricingService pricingService;

    @GetMapping
    public List<Pricing> getAllPricings(){
        return pricingService.getAllPrices();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Pricing> getPricingById(@PathVariable Long id){
        return pricingService.getPricing(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<?> createPricing(@RequestBody PricingRequestDTO pricingRequestDTO){
        try {
            Pricing newPricing = pricingService.createPricing(pricingRequestDTO.getPlanId(),
                    pricingRequestDTO.getRoomTypeId(), pricingRequestDTO.getPrice());
            return ResponseEntity.ok(newPricing);
        }catch (DataIntegrityViolationException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePricing(@PathVariable Long id){
        pricingService.deletePricing(id);
        return ResponseEntity.noContent().build();
    }
    @PatchMapping("/{id}")
    public ResponseEntity<?> updatePricing(@PathVariable Long id, @RequestBody Map<String, Object> pricingUpdate){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(pricingService.updatePricing(id, pricingUpdate));
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(e.getMessage());
        }
    }
}
