DROP DATABASE IF EXISTS fleetdb;
CREATE DATABASE fleetdb;
USE fleetdb;

-- ===== PERSON =====
CREATE TABLE person (
    person_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    phone VARCHAR(30),
    birthdate DATE
);

-- ===== EMPLOYEE =====
CREATE TABLE employee (
    employee_id INT AUTO_INCREMENT PRIMARY KEY,
    person_id INT NOT NULL UNIQUE,
    FOREIGN KEY (person_id) REFERENCES person(person_id) ON DELETE CASCADE
);

-- ===== LICENSE =====
CREATE TABLE license (
    license_code VARCHAR(5) PRIMARY KEY,
    description VARCHAR(100) NULL
);

-- Many-to-many: employee <-> license
CREATE TABLE employee_license (
    employee_id INT NOT NULL,
    license_code VARCHAR(5) NOT NULL,
    PRIMARY KEY (employee_id, license_code),
    FOREIGN KEY (employee_id) REFERENCES employee(employee_id) ON DELETE CASCADE,
    FOREIGN KEY (license_code) REFERENCES license(license_code) ON DELETE RESTRICT
);

-- ===== VEHICLE =====
CREATE TABLE vehicle (
    vehicle_id INT AUTO_INCREMENT PRIMARY KEY,
    vehicle_type ENUM ('CAR', 'TRUCK', 'MOTORCYCLE') NOT NULL,
    license_plate VARCHAR(20) NOT NULL UNIQUE,
    brand VARCHAR(50) NOT NULL,
    production_year YEAR NOT NULL,

    energy_type ENUM('PETROL', 'DIESEL', 'ELECTRIC', 'HYBRID') NOT NULL,
    energy_unit ENUM('LITER', 'KWH') NOT NULL,
    energy_consumption DECIMAL(6,2) NOT NULL, -- per 100 km
    mileage INT NOT NULL DEFAULT 0,
    energy_level DECIMAL(10,2) NOT NULL DEFAULT 0,

    CHECK (production_year BETWEEN 1950 AND 2025),
    CHECK (energy_consumption >= 0),
    CHECK (energy_level >= 0)
);

-- ===== DRIVER ASSIGNMENT (history) =====
CREATE TABLE vehicle_driver (
    vehicle_id INT NOT NULL,
    employee_id INT NOT NULL,
    assigned_from DATE NOT NULL,
    assigned_to DATE NULL,
    PRIMARY KEY (vehicle_id, employee_id, assigned_from),
    FOREIGN KEY (vehicle_id) REFERENCES vehicle(vehicle_id) ON DELETE CASCADE,
    FOREIGN KEY (employee_id) REFERENCES employee(employee_id) ON DELETE RESTRICT
);

-- ===== SUBTYPES =====
CREATE TABLE car (
    vehicle_id INT PRIMARY KEY,
    number_of_seats INT NOT NULL,
    FOREIGN KEY (vehicle_id) REFERENCES vehicle (vehicle_id) ON DELETE CASCADE
);

CREATE TABLE truck (
    vehicle_id INT PRIMARY KEY,
    max_load_kg DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (vehicle_id) REFERENCES vehicle (vehicle_id) ON DELETE CASCADE
);

CREATE TABLE motorcycle (
    vehicle_id INT PRIMARY KEY,
    engine_capacity_ccm INT NOT NULL,
    FOREIGN KEY (vehicle_id) REFERENCES vehicle(vehicle_id) ON DELETE CASCADE
);

-- ===== TEST DATA =====
INSERT INTO license (license_code, description) VALUES
('A',  'Motorcycles'),
('B',  'Cars'),
('C',  'Trucks'),
('BE', 'Car with trailer'),
('CE', 'Truck with trailer');

INSERT INTO person (name, email, phone, birthdate) VALUES
('Anna Berger',  'anna.berger@example.com',  '+43 660 111 222', '1991-04-12'),
('Markus Huber', 'markus.huber@example.com', '+43 699 333 444', '1987-09-03'),
('Olena Koval',  'olena.koval@example.com',  '+43 676 555 666', '1994-02-25'),
('David Novak',  'david.novak@example.com',  '+43 664 777 888', '1982-11-19'),
('Sara Meier',   'sara.meier@example.com',   '+43 650 999 000', '1998-07-08');

INSERT INTO employee (person_id) VALUES (1),(2),(3),(4),(5);

INSERT INTO employee_license (employee_id, license_code) VALUES
(1, 'B'),
(2, 'B'), (2, 'C'), (2, 'CE'),
(3, 'B'),
(4, 'A'), (4, 'B'),
(5, 'B'), (5, 'BE');

INSERT INTO vehicle (vehicle_type, license_plate, brand, production_year, energy_type, energy_unit,
                     energy_consumption, mileage, energy_level) VALUES
('CAR',        'MT-AB123', 'Toyota', 2019, 'PETROL',   'LITER',  6.80,  84500, 22.50),
('CAR',        'GZ-EV777', 'Tesla',  2022, 'ELECTRIC', 'KWH',   15.20,  32000, 48.00),
('TRUCK',      'LB-TR900', 'MAN',    2016, 'DIESEL',  'LITER', 28.50, 210000, 120.00),
('MOTORCYCLE', 'KN-MC404', 'Honda',  2018, 'PETROL',  'LITER',  4.20,  19600,  9.00),
('TRUCK',      'MU-TR222', 'Volvo',  2020, 'DIESEL',  'LITER', 25.80,  98000, 180.00);

INSERT INTO car (vehicle_id, number_of_seats) VALUES
(1, 5), (2, 5);

INSERT INTO truck (vehicle_id, max_load_kg) VALUES
(3, 18000.00), (5, 12000.00);

INSERT INTO motorcycle (vehicle_id, engine_capacity_ccm) VALUES
(4, 650);

INSERT INTO vehicle_driver (vehicle_id, employee_id, assigned_from, assigned_to) VALUES
(1, 1, '2024-01-10', NULL),
(2, 3, '2024-06-01', NULL),
(3, 2, '2023-09-15', '2024-12-31'),
(3, 4, '2025-01-01', NULL),
(4, 4, '2024-03-05', NULL),
(5, 2, '2024-02-01', NULL);
