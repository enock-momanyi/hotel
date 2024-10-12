package com.management.hotel.service;

import com.management.hotel.model.Plan;
import com.management.hotel.repository.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PlanService {
    @Autowired
    private PlanRepository planRepository;
    public Plan createPlan(Plan plan){
        return planRepository.save(plan);
    }
    public Plan updatePlan(Long id, Map<String, Object> updatedPlan){
        return planRepository.findById(id).map(plan1 -> {
            updatedPlan.forEach((key, value) -> {
                if(key.equals("name")) {
                    plan1.setName((String) value);
                } else if (key.equals("description")) {
                    plan1.setDescription((String) value);
                }
            });
            return planRepository.save(plan1);
        }).orElseThrow(() -> new RuntimeException("Plan not found"));
    }
    public void deletePlan(Long id){
        planRepository.deleteById(id);
    }
    public Optional<Plan> getPlan(Long id){
        return planRepository.findById(id);
    }
    public List<Plan> getAllPlans(){
        return planRepository.findAll();
    }
}
