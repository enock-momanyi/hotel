package com.management.hotel.service;

import com.management.hotel.model.Room;
import com.management.hotel.model.RoomType;
import com.management.hotel.repository.RoomRepository;
import com.management.hotel.repository.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private RoomTypeRepository roomTypeRepository;

    public Room createRoom(int number,Long roomTypeId, boolean isAvailable){
        Optional<RoomType> optionalRoomType = roomTypeRepository.findById(roomTypeId);
        if(optionalRoomType.isEmpty()){
            throw new IllegalArgumentException(String.format("No room type with Id: %s", roomTypeId));
        }
        Room room = new Room(number,optionalRoomType.get(),isAvailable);
        return roomRepository.save(room);
    }
    public Room updateRoom(Long id, Map<String, Object> updatedRoom){
        return roomRepository.findById(id).map(room1 -> {
            updatedRoom.forEach((key, value) -> {
                if(key.equals("roomTypeId") && (Long) value != room1.getRoomType().getId()){
                    Optional<RoomType> optionalRoomType = roomTypeRepository.findById((Long) value);
                    if(optionalRoomType.isEmpty()){
                        throw new IllegalArgumentException(String.format("No room type with Id: %s", (Long) value));
                    }
                    room1.setRoomType(optionalRoomType.get());
                } else if (key.equals("isAvailable")) {
                    room1.setAvailable((boolean) value);
                } else if (key.equals("number")) {
                    room1.setNumber((int) value);
                }
            });
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
