CREATE TABLE IF NOT EXISTS manufacturer
(
    id         SERIAL       primary key,
    name       VARCHAR(255) NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL
);

INSERT INTO manufacturer VALUES (1, 'Alibaba', CURRENT_DATE, CURRENT_DATE);

ALTER TABLE ecommo.product
    ADD COLUMN manufacturer_id INT NOT NULL DEFAULT 1;