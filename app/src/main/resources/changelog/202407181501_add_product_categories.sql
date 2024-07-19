CREATE TABLE IF NOT EXISTS category
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE INDEX idx_ctg_name ON
    category (name);

CREATE TABLE IF NOT EXISTS product_categories_rel
(
    id          SERIAL PRIMARY KEY,
    product_id  INT NOT NULL,
    category_id INT NOT NULL,
    CONSTRAINT fk_product FOREIGN KEY (product_id) REFERENCES product(id) on DELETE CASCADE,
    CONSTRAINT fk_category FOREIGN KEY (category_id) REFERENCES category(id) on DELETE CASCADE
);

CREATE INDEX idx_prod_id ON
    product_categories_rel (product_id);

CREATE INDEX idx_cat_id ON
    product_categories_rel (category_id);

