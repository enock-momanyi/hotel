package com.management.hotel.repository;

import com.management.hotel.model.Room;
import com.management.hotel.model.RoomType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class RoomRepositoryTest {
    @Autowired
    private RoomRepository roomRepository;

    @Test
    public void getAllRoomWhenRoomNotAdded(){
        List<Room> rooms = roomRepository.findAll();
        assertEquals(rooms.isEmpty(),true);
    }
    @Test
    public void getAllRoomTypesWhenRoomTypesAdded(){
        roomRepository.save(new Room(402,new RoomType("SINGLE",1),true));
        List<Room> rooms = roomRepository.findAll();
        assertEquals(rooms.size(),1);
    }
    @Test
    public void getRoomById(){
        Long roomId = roomRepository.save(new Room(402,new RoomType("SINGLE",1),true)).getId();
        Optional<Room> room = roomRepository.findById(roomId);
        assertEquals(room.isEmpty(),false);
    }
    @Test
    public void updateRoomNumber(){
        Long roomId = roomRepository.save(new Room(402,new RoomType("SINGLE",1),true)).getId();
        Optional<Room> room = roomRepository.findById(roomId).map(room1 -> {
            room1.setNumber(404);
            return roomRepository.save(room1);
        });
        assertEquals(room.isEmpty(),false);
        assertEquals(room.get().getNumber(),404);
    }
    @Test
    public void updateRoomType(){
        Long roomId = roomRepository.save(new Room(402,new RoomType("SINGLE",1),true)).getId();
        Optional<Room> room = roomRepository.findById(roomId).map(room1 -> {
            room1.getRoomType().setMaxOccupancy(3);
            return roomRepository.save(room1);
        });
        assertEquals(room.isEmpty(),false);
        assertEquals(room.get().getRoomType().getMaxOccupancy(),3);
    }
    @Test
    public void deleteRoomType(){
        Long roomId = roomRepository.save(new Room(402,new RoomType("SINGLE",1),true)).getId();
        roomRepository.deleteById(roomId);
        Optional<Room> room = roomRepository.findById(roomId);
        assertEquals(room.isEmpty(),true);
    }
}
