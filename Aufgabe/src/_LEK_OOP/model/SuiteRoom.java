package _LEK_OOP.model;

public class SuiteRoom extends Room {

    public SuiteRoom(int roomNumber, double basePricePerNight, boolean petsAllowed) {
        super(roomNumber, RoomType.SUITE, basePricePerNight, petsAllowed);
    }
}
