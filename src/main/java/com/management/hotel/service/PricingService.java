package com.management.hotel.service;

import com.management.hotel.model.Plan;
import com.management.hotel.model.Pricing;
import com.management.hotel.model.RoomType;
import com.management.hotel.repository.PlanRepository;
import com.management.hotel.repository.PricingRepository;
import com.management.hotel.repository.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PricingService {
    @Autowired
    private PricingRepository pricingRepository;
    @Autowired
    private PlanRepository planRepository;
    @Autowired
    private RoomTypeRepository roomTypeRepository;
    public Pricing createPricing(Long planId, Long roomTypeId, int price){
        Optional<Plan> optionalPlan = planRepository.findById(planId);
        if(optionalPlan.isEmpty()){
            throw new IllegalArgumentException(String.format("No Plan with Id: %s",planId));
        }
        Optional<RoomType> optionalRoomType = roomTypeRepository.findById(roomTypeId);
        if(optionalRoomType.isEmpty()){
            throw new IllegalArgumentException(String.format("No Room Type with Id: %s",roomTypeId));
        }
        Pricing pricing = new Pricing(optionalRoomType.get(),optionalPlan.get(),price);
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
