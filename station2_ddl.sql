-- Station 2: Cutting and Trays
CREATE TABLE IF NOT EXISTS part (
    id BIGSERIAL PRIMARY KEY,
    weight DOUBLE PRECISION,
    type VARCHAR(255),
    animal_registration_number VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS tray (
    id BIGSERIAL PRIMARY KEY,
    max_weight DOUBLE PRECISION,
    current_weight DOUBLE PRECISION,
    part_type VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS part_tray (
    part_id BIGINT REFERENCES part(id),
    tray_id BIGINT REFERENCES tray(id),
    PRIMARY KEY (part_id, tray_id)
);

-- Station 2 also needs to know which animal is valid, but strictly it just links parts to animal IDs.
-- We can enforce integrity if we replicate 'animal' table or just trust the ID.
-- For strictness, we might have a lightweight animal table.
CREATE TABLE IF NOT EXISTS animal_ref (
    registration_number VARCHAR(255) PRIMARY KEY
);
