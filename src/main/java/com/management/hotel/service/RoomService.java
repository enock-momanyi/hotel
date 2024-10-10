package com.management.hotel.service;

import com.management.hotel.model.Room;
import com.management.hotel.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    private RoomRepository roomRepository;

    public Room createRoom(Room room){
        return roomRepository.save(room);
    }
    public Room updateRoom(Long id, Room room){
        return roomRepository.findById(id).map(room1 -> {
            room1.setRoomType(room.getRoomType());
            room1.setAvailable(room.isAvailable());
            room1.setNumber(room.getNumber());
            return roomRepository.save(room1);
        }).orElseThrow(() -> new RuntimeException("Room not found"));
    }
    public void deleteRoom(Long id){
        roomRepository.deleteById(id);
    }
    public List<Room> getAllRooms(){
        return roomRepository.findAll();
    }
    public Optional<Room> getRoomById(Long id){
        return roomRepository.findById(id);
    }
}
