USE fleetdb;


SELECT * FROM employee;
SELECT * FROM license;
SELECT * FROM employee_license;
SELECT * FROM vehicle;
SELECT * FROM car;
SELECT * FROM truck;
SELECT * FROM motorcycle;
SELECT * FROM vehicle_driver;

SELECT * FROM person;


CREATE VIEW current_driver_view  AS SELECT 
	v.vehicle_id,
    v.license_plate,
    e.employee_id,
    p.name,
    vd.assigned_from
FROM vehicle v
LEFT JOIN vehicle_driver vd ON vd.vehicle_id=v.vehicle_id
LEFT JOIN employee e ON e.employee_id=vd.employee_id
LEFT JOIN person p ON p.person_id=e.employee_id;

SELECT * FROM current_driver_view;

CREATE VIEW all_employees_view AS SELECT
	e.employee_id,
    p.name,
    p.email,
    p.phone,
    p.birthdate    
FROM employee e
JOIN person p ON p.person_id=e.person_id;

SELECT * FROM all_employees_view;

CREATE VIEW all_vehicles_view AS SELECT
	v.vehicle_id,
    v.vehicle_type,
    v.license_plate,
    v.brand,
    v.production_year,
    c.number_of_seats,
    t.max_load_kg,
    m.engine_capacity_ccm    
FROM vehicle v 
LEFT JOIN car c ON c.vehicle_id=v.vehicle_id
LEFT JOIN truck t ON t.vehicle_id=v.vehicle_id
LEFT JOIN motorcycle m ON m.vehicle_id=v.vehicle_id;

SELECT * FROM all_vehicles_view;
