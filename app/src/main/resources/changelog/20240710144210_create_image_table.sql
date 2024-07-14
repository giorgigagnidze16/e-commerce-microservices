CREATE TABLE IF NOT EXISTS ecommo.image
(
    id         SERIAL PRIMARY KEY,
    product_id INT  NOT NULL,
    url        TEXT NOT NULL,
    CONSTRAINT fk_product FOREIGN KEY (product_id) REFERENCES product (id) on DELETE cascade
);

CREATE UNIQUE INDEX idx_product_image_rel
    ON ecommo.image (product_id, url);