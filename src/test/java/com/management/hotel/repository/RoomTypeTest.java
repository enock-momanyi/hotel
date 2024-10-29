package com.management.hotel.repository;

import com.management.hotel.model.RoomType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
//@RunWith(MockitoJUnitRunner.class)
public class RoomTypeTest {
    @Autowired
    private RoomTypeRepository roomTypeRepository;

    @Test
    public void getAllRoomTypesWhenRoomTypesNotAdded(){
        List<RoomType> rooms = roomTypeRepository.findAll();
        assertEquals(rooms.isEmpty(),true);
    }
    @Test
    public void getAllRoomTypesWhenRoomTypesAdded(){
        roomTypeRepository.save(new RoomType("SINGLE",1));
        List<RoomType> rooms = roomTypeRepository.findAll();
        assertEquals(rooms.size(),1);
    }
    @Test
    public void getAllRoomTypeById(){
        Long roomId = roomTypeRepository.save(new RoomType("SINGLE",1)).getId();
        Optional<RoomType> roomType = roomTypeRepository.findById(roomId);
        assertEquals(roomType.isEmpty(),false);
    }
    @Test
    public void updateRoomTypeName(){
        Long roomId = roomTypeRepository.save(new RoomType("SINGLE",1)).getId();
        Optional<RoomType> roomType = roomTypeRepository.findById(roomId).map(roomType1 -> {
            roomType1.setName("DOUBLE");
            return roomTypeRepository.save(roomType1);
        });
        assertEquals(roomType.isEmpty(),false);
        assertEquals(roomType.get().getName(),"DOUBLE");
    }
    @Test
    public void updateRoomTypeOccupancy(){
        Long roomId = roomTypeRepository.save(new RoomType("SINGLE",1)).getId();
        Optional<RoomType> roomType = roomTypeRepository.findById(roomId).map(roomType1 -> {
            roomType1.setMaxOccupancy(3);
            return roomTypeRepository.save(roomType1);
        });
        assertEquals(roomType.isEmpty(),false);
        assertEquals(roomType.get().getMaxOccupancy(),3);
    }
    @Test
    public void deleteRoomType(){
        Long roomId = roomTypeRepository.save(new RoomType("SINGLE",1)).getId();
        roomTypeRepository.deleteById(roomId);
        Optional<RoomType> roomType = roomTypeRepository.findById(roomId);
        assertEquals(roomType.isEmpty(),true);
    }
}
