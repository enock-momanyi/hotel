package com.management.hotel.controller;


import com.management.hotel.model.RoomType;
import com.management.hotel.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/room_types")
public class RoomTypeController {
    @Autowired
    private RoomTypeService roomTypeService;

    @GetMapping
    public List<RoomType> getAllRoomTypes(){
        return roomTypeService.getAllRoomTypes();
    }
    @GetMapping("/{id}")
    public ResponseEntity<RoomType> getRoomTypeById(@PathVariable Long id){
        return roomTypeService.getRoomType(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<?> createRoomType(@RequestBody RoomType roomType){
        try {
            RoomType newRoomType = roomTypeService.createRoomType(roomType);
            return ResponseEntity.ok(newRoomType);
        }catch (DataIntegrityViolationException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Room type exists");
        }

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoomType(@PathVariable Long id){
        roomTypeService.deleteRoomType(id);
        return ResponseEntity.noContent().build();
    }
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateRoomType(@PathVariable Long id, @RequestBody Map<String, Object> roomTypeUpdate){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(roomTypeService.updateRoomType(id, roomTypeUpdate));
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(e.getMessage());
        }
    }
}
