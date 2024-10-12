package com.management.hotel.controller;

import com.management.hotel.model.Plan;
import com.management.hotel.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    public ResponseEntity<?> createPlan(@RequestBody Plan plan){
        try {
            Plan newPlan = planService.createPlan(plan);
            return ResponseEntity.ok(newPlan);
        }catch (DataIntegrityViolationException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Plan name exist");
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlan(@PathVariable Long id){
        planService.deletePlan(id);
        return ResponseEntity.noContent().build();
    }
    @PatchMapping("/{id}")
    public ResponseEntity<?> updatePlan(@PathVariable Long id, @RequestBody Map<String, Object> planUpdate){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(planService.updatePlan(id, planUpdate));
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(e.getMessage());
        }
    }
}
