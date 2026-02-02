package _LEK_OOP.app;

import _LEK_OOP.management.HotelManagement;
import _LEK_OOP.model.Room;
import _LEK_OOP.util.InputHelper;
import _LEK_OOP.model.RoomType;


import java.util.Scanner;

public class ConsoleUi {

    private final Scanner scanner = new Scanner(System.in);
    private final HotelManagement management;

    public ConsoleUi(HotelManagement management) {
        this.management = management;
    }

    public void run(){
        while(true){
            //MenuPrinter.printHeader();
            MenuPrinter.printTitle("HOTEL CONTINENTAL");
            MenuPrinter.printMenu();

            int choice = InputHelper.readNonNegativeInt(scanner, "Choose option: ");

            switch (choice){
                case 1: showAllRooms(); InputHelper.pressEnter(scanner); break;
                case 2: bookRoom();InputHelper.pressEnter(scanner);break;
                case 3: checkoutRoom(); InputHelper.pressEnter(scanner);break;
                case 4: showStatistics(); InputHelper.pressEnter(scanner);break;
                case 0:
                    System.out.println("0: Quit"); return;
                default: System.out.println("Invalid choice");

            }
        }
    }

    private void showAllRooms() {
        System.out.println();
        MenuPrinter.printTitle("All Rooms");
        MenuPrinter.printSeparator2();
        System.out.println("| Room |   Type   | Price/night |  Status  | Pets | Pet in room |");
        MenuPrinter.printSeparator2();


        for (Room room : management.getRooms()) {
            System.out.printf(
                    "| %-4d | %-8s | %11.2f | %-8s | %-4s | %-11s |%n",
                    room.getRoomNumber(),
                    room.getType(),
                    room.getBasePricePerNight(),
                    room.isOccupied() ? "OCCUPIED" : "FREE",
                    room.allowsPet() ? "YES" : "NO",
                    room.isOccupied() ? (room.hasPet() ? "YES" : "NO") : "-"
            );
        }

        MenuPrinter.printSeparator2();
    }


    private void bookRoom() {
        System.out.println();
        MenuPrinter.printTitle("=== BOOK ROOM ===");

        int roomNumber = InputHelper.readPositiveInt(scanner, "Room number: ");
        Room room = management.findByNumber(roomNumber);

        if (room == null) {
            System.out.println("Room not found.");
            return;
        }
        if (room.isOccupied()) {
            System.out.println("Room is already occupied.");
            return;
        }

        boolean hasPet = InputHelper.readYesNo(scanner, "Do you have a pet?");
        if (hasPet && !room.allowsPet()) {
            System.out.println("Pets are not allowed for this room.");
            return;
        }

        management.bookRoom(roomNumber, hasPet);
        System.out.println("Room booked successfully.");
    }


    private void checkoutRoom() {
        System.out.println();
        MenuPrinter.printTitle("=== CHECKOUT ===");

        int roomNumber = InputHelper.readPositiveInt(scanner, "Room number:      ");
        Room room = management.findByNumber(roomNumber);

        if (room == null) {
            System.out.println("Room not found.");
            return;
        }
        if (!room.isOccupied()) {
            System.out.println("Room is not occupied.");
            return;
        }

        int nights = InputHelper.readPositiveInt(scanner, "Number of nights: ");

        double total = management.checkoutRoom(roomNumber, nights);

        MenuPrinter.printSeparator();
        System.out.println("Room:        |    " + roomNumber);
        System.out.println("Nights:      |    " + nights);
        System.out.println("Pet:         |    " + (room.hasPet() ? "YES" : "NO"));
        System.out.printf("Total price: | %.2f EUR%n", total);
        MenuPrinter.printSeparator();
    }


    private void showStatistics() {
        System.out.println();
        MenuPrinter.printTitle("=== STATISTICS ===");
        MenuPrinter.printSeparator();

        System.out.println("Total rooms:    |   " + management.countAll());
        System.out.println("Occupied rooms: |   " + management.countOccupied());
        System.out.println("Free rooms:     |   " + (management.countAll() - management.countOccupied()));

        System.out.println("Single rooms:   |   " + management.countByType(RoomType.SINGLE));
        System.out.println("Double rooms:   |   " + management.countByType(RoomType.DOUBLE));
        System.out.println("Suite rooms:    |   " + management.countByType(RoomType.SUITE));
        MenuPrinter.printSeparator();
    }
}




