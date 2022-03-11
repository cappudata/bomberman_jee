DROP TABLE IF EXISTS shop ;


CREATE TABLE shop (
    idproduct INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(200) NOT NULL, 
    description VARCHAR(200) NOT NULL,
    images VARCHAR(200) NOT NULL,
    price FLOAT NOT NULL,
    rate INT NOT NULL,
    PRIMARY KEY (idproduct)
) ENGINE = INNODB;

INSERT INTO shop(name, description, images, price,rate) VALUES ("Mario Wall", "Jouer avec les murs de Mario dans Bomberman", "images/wall.png", 100.0,4);
INSERT INTO shop(name, description, images, price,rate) VALUES ("Mathieu Nebra", "Jouer avec Mathieu Nebra sur votre map", "images/op.png", 300.0,5);
INSERT INTO shop(name, description, images, price,rate) VALUES ("Mario Box", "Les murs incassable en box mario", "images/mario_box.png", 250.0,3);