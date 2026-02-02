package _LEK_OOP.model;

public class SingleRoom extends Room {

    public SingleRoom(int roomNumber, double basePricePerNight, boolean petsAllowed) {
        super(roomNumber, RoomType.SINGLE, basePricePerNight, petsAllowed);
    }
}
