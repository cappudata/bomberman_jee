DROP TABLE IF EXISTS users ;


CREATE TABLE users (
    userid INT NOT NULL AUTO_INCREMENT,
    username VARCHAR(32) NOT NULL, 
    password VARCHAR(200) NOT NULL,
    mail VARCHAR(200),
    profilepic VARCHAR(200),
    bomcoin FLOAT,
    PRIMARY KEY (userid)
) ENGINE = INNODB;



INSERT INTO users (username, password) VALUES ("test", "testpass");
INSERT INTO users (username, password) VALUES ("Cappu", "pass");
INSERT INTO users (username, password) VALUES ("Pinol", "pass");