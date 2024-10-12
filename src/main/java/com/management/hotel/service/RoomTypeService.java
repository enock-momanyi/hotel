package com.management.hotel.service;

import com.management.hotel.model.RoomType;
import com.management.hotel.repository.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RoomTypeService {
    @Autowired
    private RoomTypeRepository roomTypeRepository;
    public RoomType createRoomType(RoomType roomType){
        return roomTypeRepository.save(roomType);
    }
    public RoomType updateRoomType(Long id, Map<String, Object> updatedRoomType){
        return roomTypeRepository.findById(id).map(roomType1 -> {
            updatedRoomType.forEach((key, value) -> {
                if(key.equals("name")){
                    roomType1.setName((String) value);
                } else if (key.equals("maxOccupancy")) {
                    roomType1.setMaxOccupancy((int) value);
                }
            });
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
