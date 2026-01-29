package org.example.entities;

import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private String id;
    private String name;
    private List<Room> rooms;

    public Hotel(String id, String name) {
        this.id = id;
        this.name = name;
        this.rooms = new ArrayList<>();
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public void removeRoom(Room room) {
        rooms.remove(room);
    }

    public List<Room> getRooms() {
        return rooms;
    }
    
    public String getId() {
        return id;
    }
}
