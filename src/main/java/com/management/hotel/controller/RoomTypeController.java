package com.management.hotel.controller;


import com.management.hotel.model.RoomType;
import com.management.hotel.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<RoomType> createRoomType(@RequestBody RoomType roomType){
        RoomType newRoomType = roomTypeService.createRoomType(roomType);
        return ResponseEntity.ok(newRoomType);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoomType(@PathVariable Long id){
        roomTypeService.deleteRoomType(id);
        return ResponseEntity.noContent().build();
    }
}
