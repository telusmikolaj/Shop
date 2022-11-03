--liquibase formatted sql

--changeset liquibase:10

INSERT INTO user_(name, created, updated) VALUES
                                              ('Bartek', current_timestamp, current_timestamp),
                                              ('Pawel', current_timestamp, current_timestamp),
                                              ('Mikolaj', current_timestamp, current_timestamp),
                                              ('Boro', current_timestamp, current_timestamp),
                                              ('Lukasz', current_timestamp, current_timestamp);