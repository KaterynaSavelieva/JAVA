USE fleetdb;
CREATE OR REPLACE VIEW v_fleet AS
SELECT 
    v.vehicle_id,
    v.vehicle_type,
    v.license_plate,
    v.brand,
    v.mileage,
    v.fuel_liters,
    v.driver_id,
    c.number_of_seats,
    t.max_load_kg,
    m.engine_capacity_ccm
FROM vehicle v
LEFT JOIN car c ON c.vehicle_id = v.vehicle_id
LEFT JOIN truck t ON t.vehicle_id = v.vehicle_id
LEFT JOIN motorcycle m ON m.vehicle_id = v.vehicle_id;

SELECT * FROM v_fleet;
