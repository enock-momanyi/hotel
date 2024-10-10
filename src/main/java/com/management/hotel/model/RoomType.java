package com.management.hotel.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class RoomType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int maxOccupancy;
    @OneToOne(mappedBy = "room_type",cascade = CascadeType.ALL)
    private Pricing pricing;
    @OneToMany(mappedBy = "room_type", cascade = CascadeType.ALL)
    private List<Room> rooms;

    public RoomType(String name, int maxOccupancy) {
        this.name = name;
        this.maxOccupancy = maxOccupancy;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxOccupancy() {
        return maxOccupancy;
    }

    public void setMaxOccupancy(int maxOccupancy) {
        this.maxOccupancy = maxOccupancy;
    }

    @Override
    public String toString() {
        return "RoomType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", maxOccupancy=" + maxOccupancy +
                '}';
    }
}
