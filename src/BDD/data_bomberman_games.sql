DROP TABLE IF EXISTS games ;


CREATE TABLE games (
    idgame INT  AUTO_INCREMENT NOT NULL ,
    username VARCHAR(32) NOT NULL, 
    status VARCHAR(32) NOT NULL,
    nbr_player INT NOT NULL,
    PRIMARY KEY (username, idgame)
) ENGINE = INNODB;



INSERT INTO games( username, status, nbr_player) VALUES ("Cappu", "Victory", 1);
INSERT INTO games( username, status, nbr_player) VALUES ("Cappu", "Victory", 2);
INSERT INTO games( username, status, nbr_player) VALUES ("Cappu", "Victory", 1);
INSERT INTO games( username, status, nbr_player) VALUES ("Pinol", "Defeat", 1);
