CREATE TABLE IF NOT EXISTS ecommo.roles
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

INSERT INTO ecommo.roles(name)
VALUES ('ADMIN'),
       ('SELLER'),
       ('USER');

CREATE TABLE IF NOT EXISTS ecommo.users
(
    id        SERIAL PRIMARY KEY,
    firstname VARCHAR(255)                  NOT NULL,
    lastname  VARCHAR(255)                  NOT NULL,
    email     VARCHAR(255) UNIQUE           NOT NULL,
    password  VARCHAR(512)                  NOT NULl,
    role_id   INTEGER REFERENCES roles (id) NOT NULL
);

CREATE UNIQUE INDEX idx_email on
    ecommo.users (email);

INSERT INTO ecommo.users (firstname, lastname, email, password, role_id)
VALUES ('ADMIN', 'ADMIN', 'admin@ecommo.com',
        '$2a$12$EQoZ2zFJtB8fzc2b2gpxguBa949o34AkiVUdk5KT26rcDelH0Johe', 1);
