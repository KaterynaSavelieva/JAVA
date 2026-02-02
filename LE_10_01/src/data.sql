INSERT INTO employee (name, driving_license_type)
VALUES ('Anna', 'B'), ('Max', 'C');

INSERT INTO vehicle (vehicle_type, license_plate, brand, mileage, fuel_liters, driver_id)
VALUES 
('CAR', 'AT-111', 'Toyota', 1000, 30, 1),
('TRUCK', 'AT-222', 'MAN', 50000, 120, 2),
('MOTORCYCLE', 'AT-333', 'Honda', 7000, 10, NULL);

INSERT INTO car (vehicle_id, number_of_seats) VALUES (1, 5);
INSERT INTO truck (vehicle_id, max_load_kg) VALUES (2, 12000);
INSERT INTO motorcycle (vehicle_id, engine_capacity_ccm) VALUES (3, 650);
