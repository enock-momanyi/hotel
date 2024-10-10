package com.management.hotel.model;

import jakarta.persistence.*;

@Entity
public class Pricing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="room_type_id", referencedColumnName = "id")
    private RoomType roomType;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="plan_id", referencedColumnName = "id")
    private Plan plan;
    private int price;

    public Pricing(RoomType roomType, Plan plan, int price) {
        this.roomType = roomType;
        this.plan = plan;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Pricing{" +
                "id=" + id +
                ", roomTypeId=" + roomType.getId() +
                ", planId=" + plan.getId() +
                ", price=" + price +
                '}';
    }
}
