CREATE TABLE `stockmarket`.`user` (
  `userid` INT NOT NULL AUTO_INCREMENT,
  `isadmin` TINYINT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `verified` TINYINT NULL,
  PRIMARY KEY (`userid`, `username`));
