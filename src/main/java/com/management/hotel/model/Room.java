package com.management.hotel.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true,nullable = false)
    private int number;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "room_type_id", referencedColumnName = "id",nullable = false)
    private RoomType roomType;
    private boolean isAvailable;
    @OneToMany(mappedBy = "room",cascade = CascadeType.ALL)
    private List<Booking> bookings;

    public Room() {
    }

    public Room(int number, RoomType roomType, boolean isAvailable) {
        this.number = number;
        this.roomType = roomType;
        this.isAvailable = isAvailable;
    }

    public Long getId() {
        return id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", number=" + number +
                ", roomTypeId=" + roomType.getId() +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
