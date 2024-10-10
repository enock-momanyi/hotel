package com.management.hotel.service;

import com.management.hotel.model.RoomType;
import com.management.hotel.repository.RoomTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomTypeService {
    private RoomTypeRepository roomTypeRepository;
    public RoomType createRoomType(RoomType roomType){
        return roomTypeRepository.save(roomType);
    }
    public RoomType updateRoomType(Long id, RoomType roomType){
        return roomTypeRepository.findById(id).map(roomType1 -> {
            roomType1.setName(roomType.getName());
            roomType1.setMaxOccupancy(roomType.getMaxOccupancy());
            return roomTypeRepository.save(roomType1);
        }).orElseThrow(() -> new RuntimeException("Room Type not found"));
    }
    public void deleteRoomType(Long id){
        roomTypeRepository.deleteById(id);
    }
    public Optional<RoomType> getRoomType(Long id){
        return roomTypeRepository.findById(id);
    }
    public List<RoomType> getAllRoomTypes(){
        return roomTypeRepository.findAll();
    }
}
