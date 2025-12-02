package LE_02_01;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Bitte geben Sie Ihren Vornamen ein: ");
        String vorname = scanner.nextLine();

        System.out.print("Bitte geben Sie Ihren Nachnamen ein: ");
        String nachname = scanner.nextLine();

        System.out.println("Ausgabe: Hallo " + vorname + " " + nachname + "!");
    }
}
