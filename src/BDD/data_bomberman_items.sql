DROP TABLE IF EXISTS items ;


CREATE TABLE items (
    id INT NOT NULL AUTO_INCREMENT,
    idproduct INT NOT NULL,
    username VARCHAR(32) NOT NULL, 
    PRIMARY KEY (id),
    FOREIGN KEY(idproduct) REFERENCES shop(idproduct),
    FOREIGN KEY(idproduct) REFERENCES shop(idproduct),
) ENGINE = INNODB;