--liquibase formatted sql

--changeset liquibase:2
CREATE TABLE IF NOT EXISTS product
(
    id          bigint NOT NULL PRIMARY KEY,
    created     timestamp,
    description varchar(255),
    price       numeric(19, 2),
    title       varchar(255),
    updated     timestamp,
    category_id bigint,
    FOREIGN KEY (category_id)
        REFERENCES category (id)
);