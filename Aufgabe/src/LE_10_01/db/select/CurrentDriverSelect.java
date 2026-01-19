package LE_10_01.db.select;

import LE_10_01.db.DbRunner;

public class CurrentDriverSelect {

    public static void main(String[] args) {

        String sql = """
                SELECT v.vehicle_id, v.license_plate,
                       e.employee_id, p.name,
                       vd.assigned_from
                FROM vehicle v
                LEFT JOIN vehicle_driver vd
                    ON vd.vehicle_id = v.vehicle_id AND vd.assigned_to IS NULL
                LEFT JOIN employee e ON e.employee_id = vd.employee_id
                LEFT JOIN person p ON p.person_id = e.person_id
                ORDER BY v.vehicle_id
                """;

        String header = String.format("| %-3s | %-10s | %-3s | %-20s | %-10s |",
                "VID", "PLATE", "EID", "DRIVER", "FROM");

        DbRunner.print("CURRENT DRIVER", header, sql, rs ->
                String.format("| %3d | %-10s | %-3s | %-20s | %-10s |",
                        rs.getInt("vehicle_id"),
                        rs.getString("license_plate"),
                        (rs.getObject("employee_id") == null ? "-" : rs.getInt("employee_id")),
                        (rs.getString("name") == null ? "-" : rs.getString("name")),
                        (rs.getDate("assigned_from") == null ? "-" : rs.getDate("assigned_from").toString())
                )
        );
    }
}
