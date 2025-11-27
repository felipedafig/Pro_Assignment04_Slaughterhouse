-- Station 3: Packing and Products
CREATE TABLE IF NOT EXISTS product (
    id BIGSERIAL PRIMARY KEY,
    description VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS product_tray (
    product_id BIGINT REFERENCES product(id),
    tray_id BIGINT, -- No FK to tray table as it is in another DB
    PRIMARY KEY (product_id, tray_id)
);

-- Lookup table to track which animal went into which tray (populated by events)
CREATE TABLE IF NOT EXISTS tray_animal_lookup (
    tray_id BIGINT,
    animal_registration_number VARCHAR(255),
    PRIMARY KEY (tray_id, animal_registration_number)
);
