-- DROP DATABASE fleetdb;
CREATE DATABASE fleetdb;
USE fleetdb;


CREATE TABLE employee (
	employee_id INT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR (100) NOT NULL,
	driving_license_type VARCHAR(10) NOT NULL
);

CREATE TABLE vehicle (
	vehicle_id  INT AUTO_INCREMENT PRIMARY KEY,
    
    vehicle_type ENUM ('CAR', 'TRUCK', 'MOTORCYCLE') NOT NULL,
    
    license_plate VARCHAR(20) NOT NULL UNIQUE,
    brand VARCHAR(50) NOT NULL,
    
    mileage INT NOT NULL DEFAULT 0,
    fuel_liters DOUBLE NOT NULL DEFAULT 0,
    
    driver_id INT NULL,
    
     CONSTRAINT fk_vehicle_employee FOREIGN KEY (driver_id) REFERENCES employee(employee_id) ON DELETE SET NULL
);

CREATE TABLE car (
	vehicle_id INT PRIMARY KEY,
    number_of_seats INT NOT NULL,
    CONSTRAINT fk_car_vehicle FOREIGN KEY (vehicle_id) REFERENCES vehicle (vehicle_id) ON DELETE CASCADE
);

CREATE TABLE truck (
	vehicle_id INT PRIMARY KEY,
    max_load_kg DOUBLE NOT NULL,
    CONSTRAINT fk_truck_vehicle FOREIGN KEY (vehicle_id) REFERENCES vehicle (vehicle_id) ON DELETE CASCADE
);

CREATE TABLE motorcycle (
    vehicle_id INT PRIMARY KEY,
    engine_capacity_ccm INT NOT NULL,
    CONSTRAINT fk_motorcycle_vehicle
        FOREIGN KEY (vehicle_id) REFERENCES vehicle(vehicle_id)
        ON DELETE CASCADE
);

    
    
	

SELECT * FROM employee;
    

