CREATE TYPE statut AS ENUM ('A_FAIRE', 'EN_COURS', 'TERMINE');

CREATE TABLE taches (
                        id SERIAL PRIMARY KEY,
                        description VARCHAR(255) ,
                        dateDebut DATE ,
                        dateFin DATE ,
                        status statut,
                        projetId INT
);