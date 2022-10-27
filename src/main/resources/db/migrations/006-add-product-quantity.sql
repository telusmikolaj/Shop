--liquibase formatted sql

--changeset liquibase:6
ALTER TABLE product
ADD quantity bigint;