CREATE TABLE public.product
(
    id          serial                   primary key,
    title       text                        NOT NULL,
    description text                        NOT NULL,
    price       DECIMAL                     NOT NULL,
    discount    DECIMAL,
    stock       BIGINT                      NOT NULL,
    archived    BOOLEAN DEFAULT FALSE,
    created_at  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at  TIMESTAMP WITHOUT TIME ZONE NOT NULL
);
