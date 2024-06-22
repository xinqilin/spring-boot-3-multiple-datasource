CREATE TABLE `person`
(
    `id`   INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NULL,
    PRIMARY KEY (`id`)
);

INSERT INTO `read_db`.`person` (`name`)
VALUES ('Bill_READ');
INSERT INTO `read_db`.`person` (`name`)
VALUES ('Sherry_READ');