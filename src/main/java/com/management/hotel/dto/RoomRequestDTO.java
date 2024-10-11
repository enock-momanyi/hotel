package com.management.hotel.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RoomRequestDTO {
    private int number;
    private Long roomTypeId;
    @JsonProperty
    private boolean isAvailable;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Long getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Long roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
