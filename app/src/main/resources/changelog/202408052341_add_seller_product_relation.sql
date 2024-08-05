ALTER TABLE ecommo.product
    ADD COLUMN seller_id INT NOT NULL DEFAULT 1 REFERENCES
        users (id) ON DELETE CASCADE;

CREATE INDEX product_seller_idx on ecommo.product (seller_id);