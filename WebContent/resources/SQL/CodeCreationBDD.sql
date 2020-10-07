CREATE OR REPLACE DATABASE InteractionMots;

CREATE TABLE terme (id INT, nom VARCHAR(100), importe INT);

CREATE TABLE relation (nomT1 VARCHAR(100), nomT2 VARCHAR(100) VARCHAR(100), poids INT);

--contraintes

ALTER TABLE relation add CONSTRAINT unicite unique(nomT1, nomT2, poids);

ALTER TABLE terme add CONSTRAINT cleprimaire PRIMARY KEY(id);