package _LEK_OOP.model;



public abstract class Room implements Bookable {

    public static final double PET_FEE_PER_NIGHT = 12.0;

    private final int roomNumber;
    private final RoomType type;
    private final double basePricePerNight;

    private final boolean petsAllowed;
    private boolean occupied;
    private boolean hasPet;

    protected Room(int roomNumber, RoomType type, double basePricePerNight, boolean petsAllowed) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.basePricePerNight = basePricePerNight;
        this.petsAllowed = petsAllowed;

        this.occupied = false;
        this.hasPet = false;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public RoomType getType() {
        return type;
    }

    public double getBasePricePerNight() {
        return basePricePerNight;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public boolean hasPet() {
        return hasPet;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public void setHasPet(boolean hasPet) {
        this.hasPet = hasPet;
    }

    @Override
    public boolean allowsPet() {
        return petsAllowed;
    }

    @Override
    public double calculatePrice(int nights, boolean hasPet) {
        double total = basePricePerNight * nights;
        if (hasPet) {
            total += PET_FEE_PER_NIGHT * nights;
        }
        return total;
    }

    public String shortInfo() {
        String status = occupied ? "OCCUPIED" : "FREE";
        String petInfo = occupied ? (hasPet ? "Pet: YES" : "Pet: NO") : "Pet: -";
        String petAllowedInfo = petsAllowed ? "Pets OK" : "No pets";

        return String.format("#%d | %-5s | %.2f EUR/night | %s | %s | %s",
                roomNumber, type, basePricePerNight, status, petAllowedInfo, petInfo);
    }
}
