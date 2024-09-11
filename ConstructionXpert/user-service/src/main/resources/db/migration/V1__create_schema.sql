-- V1__create_schema.sql

-- Création de la table user
CREATE TABLE IF NOT EXISTS user (
                                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                        dtype VARCHAR(255) NOT NULL,
                                        full_name VARCHAR(255) NOT NULL,
                                        username VARCHAR(255) UNIQUE NOT NULL,
                                        password VARCHAR(255) NOT NULL,
                                        email VARCHAR(255) UNIQUE NOT NULL,
                                        role ENUM('CUSTOMER', 'ADMIN') NOT NULL
    );


-- Création de la table customer
CREATE TABLE IF NOT EXISTS customer (
                                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                         FOREIGN KEY (id) REFERENCES user(id)
    );

-- Création de la table admin
CREATE TABLE IF NOT EXISTS admin (
                                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      FOREIGN KEY (id) REFERENCES user(id)
    );