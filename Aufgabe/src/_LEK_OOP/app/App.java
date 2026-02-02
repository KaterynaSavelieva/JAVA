package _LEK_OOP.app;

import _LEK_OOP.management.HotelManagement;

public class App {
    public static void main(String[] args) {
        new ConsoleUi(new HotelManagement()).run();

    }
}

