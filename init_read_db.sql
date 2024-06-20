CREATE TABLE `person` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`)
);

INSERT INTO `test`.`person` (`name`) VALUES ('Bill_READ');
INSERT INTO `test`.`person` (`name`) VALUES ('Sherry_READ');