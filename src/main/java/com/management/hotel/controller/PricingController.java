package com.management.hotel.controller;

import com.management.hotel.model.Pricing;
import com.management.hotel.service.PricingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<Pricing> createPricing(@RequestBody Pricing pricing){
        Pricing newPricing = pricingService.createPricing(pricing);
        return ResponseEntity.ok(newPricing);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePricing(@PathVariable Long id){
        pricingService.deletePricing(id);
        return ResponseEntity.noContent().build();
    }
}
