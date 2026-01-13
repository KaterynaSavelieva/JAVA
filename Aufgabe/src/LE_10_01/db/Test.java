package LE_10_01.db;

import LE_10_01.db.DBConnection;
import java.sql.Connection;

public class Test {

    public static void main(String[] args) {

        try (Connection conn = DBConnection.getConnection()) {
            System.out.println("CONNECTED TO DATABASE ");
        } catch (Exception e) {
            System.out.println("CONNECTION FAILED ");
            e.printStackTrace();
        }
    }
}
