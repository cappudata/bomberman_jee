DROP TABLE IF EXISTS games ;


CREATE TABLE games (
    idgame INT NOT NULL,
    username VARCHAR(32) NOT NULL, 
    status VARCHAR(10) NOT NULL,
    nbr_player INT NOT NULL,
    PRIMARY KEY (username, idgame)
) ENGINE = INNODB;



INSERT INTO games(idgame, username, status, nbr_player) VALUES (90001, "Cappu", "Victory", 1);
INSERT INTO games(idgame, username, status, nbr_player) VALUES (90003, "Cappu", "Victory", 2);
INSERT INTO games(idgame, username, status, nbr_player) VALUES (90007, "Cappu", "Victory", 1);
INSERT INTO games(idgame, username, status, nbr_player) VALUES (90015, "Pinol", "Defeat", 1);
