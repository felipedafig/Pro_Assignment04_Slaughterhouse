-- Station 1: Animal Registration
CREATE TABLE IF NOT EXISTS animal (
    registration_number VARCHAR(255) PRIMARY KEY,
    arrival_date DATE,
    origin_farm VARCHAR(255),
    type VARCHAR(255),
    weight DOUBLE PRECISION
);
