USE fleetdb;

CREATE or REPLACE VIEW current_driver_view  AS SELECT 
	v.vehicle_id,
    v.license_plate,
    v.vehicle_type,
    e.employee_id,
    p.name,
    GROUP_CONCAT(el.license_code ORDER BY el.license_code SEPARATOR ', ') AS licenses,
-- el.license_code,
    vd.assigned_from,
    vd.assigned_to    
FROM vehicle v
LEFT JOIN vehicle_driver vd ON vd.vehicle_id=v.vehicle_id 
							AND vd.assigned_to is NULL
LEFT JOIN employee e ON e.employee_id=vd.employee_id
LEFT JOIN person p ON p.person_id=e.person_id
LEFT JOIN employee_license el ON el.employee_id=e.employee_id
GROUP BY 
    v.vehicle_id,
    v.license_plate,
    v.vehicle_type,
    e.employee_id,
    p.name,
    vd.assigned_from,
    vd.assigned_to
ORDER BY v.vehicle_id;

SELECT * FROM current_driver_view;
SELECT * FROM employee_license;

CREATE or REPLACE VIEW driver_history_view   AS SELECT 
	v.vehicle_id,
    v.license_plate,
    v.vehicle_type,
    e.employee_id,
    p.name,
    GROUP_CONCAT(el.license_code ORDER BY el.license_code SEPARATOR ', ') AS licenses,
    vd.assigned_from,
    vd.assigned_to    
FROM vehicle v
LEFT JOIN vehicle_driver vd ON vd.vehicle_id=v.vehicle_id 
LEFT JOIN employee e ON e.employee_id=vd.employee_id
LEFT JOIN person p ON p.person_id=e.person_id
LEFT JOIN employee_license el ON el.employee_id=e.employee_id
GROUP BY 
    v.vehicle_id,
    v.license_plate,
    v.vehicle_type,
    e.employee_id,
    p.name,
    vd.assigned_from,
    vd.assigned_to
ORDER BY v.vehicle_id, vd.assigned_to DESC, vd.assigned_from DESC;

SELECT * FROM driver_history_view ;


CREATE or REPLACE VIEW all_employees_view AS SELECT
	e.employee_id,
    p.name,
    p.email,
    p.phone,
    p.birthdate,
    GROUP_CONCAT(el.license_code ORDER BY el.license_code SEPARATOR ', ') AS licenses
FROM employee e
JOIN person p ON p.person_id=e.person_id
LEFT JOIN employee_license el ON el.employee_id=e.employee_id
GROUP BY e.employee_id, p.name, p.email, p.phone, p.birthdate
ORDER BY e.employee_id;

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

CREATE OR REPLACE VIEW employee_licenses_view AS
SELECT
    e.employee_id,
    
    p.name,
    el.license_code
FROM employee e
JOIN person p ON p.person_id = e.person_id
JOIN employee_license el ON el.employee_id = e.employee_id
ORDER BY e.employee_id, el.license_code;

SELECT * FROM employee_licenses_view;

UPDATE person p
JOIN employee e ON e.person_id = p.person_id
SET
    p.name = 'New Name',
    p.email = 'new.email@example.com',
    p.phone = '+43 600 000 000',
    p.birthdate = '1995-05-20'
WHERE e.employee_id = 6;




