package _LEK_OOP.model;

public class DoubleRoom extends Room {

    public DoubleRoom(int roomNumber, double basePricePerNight, boolean petsAllowed) {
        super(roomNumber, RoomType.DOUBLE, basePricePerNight, petsAllowed);
    }
}
