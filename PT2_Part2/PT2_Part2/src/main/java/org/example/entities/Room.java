package org.example.entities;

public class Room {
    private String id;
    private RoomType roomType;
    private double price;

    public Room(String id, RoomType roomType, double price) {
        this.id = id;
        this.roomType = roomType;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public double getPrice() {
        return price;
    }
}
