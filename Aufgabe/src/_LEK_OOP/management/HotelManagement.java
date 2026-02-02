package _LEK_OOP.management;

import _LEK_OOP.model.*;

import java.util.ArrayList;
import java.util.List;

public class HotelManagement {

    private final List<Room> rooms = new ArrayList<>();

    public HotelManagement() {
        rooms.add(new SingleRoom(101, 60.0, false));
        rooms.add(new SingleRoom(102, 65.0, true));
        rooms.add(new DoubleRoom(201, 90.0, false));
        rooms.add(new DoubleRoom(202, 95.0, true));
        rooms.add(new SuiteRoom(301, 150.0, true));


        Room demo = findByNumber(202);
        if (demo != null) {
            demo.setOccupied(true);
            demo.setHasPet(true);
        }
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public Room findByNumber(int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) return room;
        }
        return null;
    }


    public void bookRoom(int roomNumber, boolean hasPet) {
        Room room = findByNumber(roomNumber);
        if (room == null) throw new IllegalArgumentException("Room not found.");
        if (room.isOccupied()) throw new IllegalStateException("Room is already occupied.");

        if (hasPet && !room.allowsPet()) {
            throw new IllegalArgumentException("Pets are not allowed for this room.");
        }

        room.setOccupied(true);
        room.setHasPet(hasPet);
    }


    public double checkoutRoom(int roomNumber, int nights) {
        Room room = findByNumber(roomNumber);
        if (room == null) throw new IllegalArgumentException("Room not found.");
        if (!room.isOccupied()) throw new IllegalStateException("Room is not occupied.");

        boolean storedHasPet = room.hasPet();
        double total = room.calculatePrice(nights, storedHasPet); // polymorphism via Room reference

        room.setOccupied(false);
        room.setHasPet(false);
        return total;
    }

    public int countAll() {
        return rooms.size();
    }

    public int countOccupied() {
        int count = 0;
        for (Room r : rooms) {
            if (r.isOccupied()) count++;
        }
        return count;
    }

    public int countByType(RoomType type) {
        int count = 0;
        for (Room r : rooms) {
            if (r.getType() == type) count++;
        }
        return count;
    }
}
