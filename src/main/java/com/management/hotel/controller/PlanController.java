package com.management.hotel.controller;

import com.management.hotel.model.Plan;
import com.management.hotel.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/plans")
public class PlanController {
    @Autowired
    private PlanService planService;

    @GetMapping
    public List<Plan> getAllPlans(){
        return planService.getAllPlans();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Plan> getPlanById(@PathVariable Long id){
        return planService.getPlan(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<Plan> createPlan(@RequestBody Plan plan){
        Plan newPlan = planService.createPlan(plan);
        return ResponseEntity.ok(newPlan);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlan(@PathVariable Long id){
        planService.deletePlan(id);
        return ResponseEntity.noContent().build();
    }
}
