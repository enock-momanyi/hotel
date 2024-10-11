package com.management.hotel.service;

import com.management.hotel.model.Plan;
import com.management.hotel.repository.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanService {
    @Autowired
    private PlanRepository planRepository;
    public Plan createPlan(Plan plan){
        return planRepository.save(plan);
    }
    public Plan updatePlan(Long id, Plan plan){
        return planRepository.findById(id).map(plan1 -> {
            plan1.setName(plan.getName());
            plan1.setDescription(plan.getDescription());
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
