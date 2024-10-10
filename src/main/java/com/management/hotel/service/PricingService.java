package com.management.hotel.service;

import com.management.hotel.model.Pricing;
import com.management.hotel.repository.PricingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PricingService {
    private PricingRepository pricingRepository;
    public Pricing createPricing(Pricing pricing){
        return pricingRepository.save(pricing);
    }
    public Pricing updatePricing(Long id, Pricing pricing){
        return pricingRepository.findById(id).map(pricing1 -> {
            pricing1.setPrice(pricing.getPrice());
            pricing1.setPlan(pricing.getPlan());
            pricing1.setRoomType(pricing.getRoomType());
            return pricingRepository.save(pricing1);
        }).orElseThrow(() -> new RuntimeException("Pricing not found"));
    }
    public void deletePricing(Long id){
        pricingRepository.deleteById(id);
    }
    public Optional<Pricing> getPricing(Long id){
        return pricingRepository.findById(id);
    }
    public List<Pricing> getAllPrices(){
        return pricingRepository.findAll();
    }
}
