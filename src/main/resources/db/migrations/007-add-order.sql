--liquibase formatted sql

--changeset liquibase:7
CREATE TABLE IF NOT EXISTS order_
(
    id uuid NOT NULL PRIMARY KEY ,
    total_price bigint,
    created timestamp,

    user_id bigint,
    FOREIGN KEY (user_id)
    REFERENCES user_(id)
    );