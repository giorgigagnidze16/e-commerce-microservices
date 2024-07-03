CREATE DATABASE IF NOT EXISTS ecommo
       OWNER postgres;

CREATE TABLE ecommo.item
(
    id          BIGINT                      NOT NULL,
    title       VARCHAR(250)                NOT NULL,
    description VARCHAR(500)                NOT NULL,
    price       DECIMAL                     NOT NULL,
    discount    DECIMAL,
    stock       BIGINT                      NOT NULL,
    archived    BOOLEAN DEFAULT FALSE,
    created_at  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_item PRIMARY KEY (id)
);