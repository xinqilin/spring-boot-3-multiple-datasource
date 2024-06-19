```bash
docker-compose up -d
docker-compose down
```

```sql

CREATE TABLE `test`.`person` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));

INSERT INTO `test`.`person` (`name`) VALUES ('Bill');
INSERT INTO `test`.`person` (`name`) VALUES ('Sherry');
```