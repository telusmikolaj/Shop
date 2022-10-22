--liquibase formatted sql

--changeset liquibase:3
INSERT INTO category (name) VALUES
                                        ('ELEKTRONIKA'),
                                        ('MOTORYZACJA'),
                                        ('EDUKACJA'),
                                        ('INNE');