DROP TABLE IF EXISTS shop ;


CREATE TABLE shop (
    idproduct INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(200) NOT NULL, 
    description VARCHAR(200) NOT NULL,
    images VARCHAR(200) NOT NULL,
    price FLOAT NOT NULL,
    rate INT NOT NULL,
    categorie ENUM('WALL','MAP','WALL EXT','ALL'),
    PRIMARY KEY (idproduct)
) ENGINE = INNODB;

INSERT INTO shop(name, description, images, price,rate,categorie) VALUES ("Mario Wall", "Jouer avec les murs de Mario dans Bomberman", "images/wall.png", 100.0,4,'WALL');
INSERT INTO shop(name, description, images, price,rate,categorie) VALUES ("Mathieu Nebra", "Jouer avec Mathieu Nebra sur votre map", "images/op.png", 300.0,5,'MAP');
INSERT INTO shop(name, description, images, price,rate,categorie) VALUES ("Mario Box", "Les murs incassable en box mario", "images/mario_box.png", 250.0,3,'WALL EXT');
INSERT INTO shop(name, description, images, price,rate,categorie) VALUES ("Mario World", "Jouer dans l'univer de mario", "images/allmario.jpg", 1000,5,'ALL');