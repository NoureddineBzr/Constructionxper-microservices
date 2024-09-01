CREATE TABLE projets (
                         id SERIAL PRIMARY KEY,
                         nom VARCHAR(255),
                         description VARCHAR(255),
                         dateDebut DATE,
                         dateFin DATE,
                         budget DOUBLE PRECISION
);
