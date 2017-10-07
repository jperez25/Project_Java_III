-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema whereismyspot
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `whereismyspot` ;

-- -----------------------------------------------------
-- Schema whereismyspot
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `whereismyspot` DEFAULT CHARACTER SET utf8 ;
USE `whereismyspot` ;

-- -----------------------------------------------------
-- Table `whereismyspot`.`LOT`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `whereismyspot`.`LOT` ;

CREATE TABLE IF NOT EXISTS `whereismyspot`.`LOT` (
  `name` VARCHAR(20) NOT NULL,
  `hours` VARCHAR(255) NOT NULL,
  `image` VARCHAR(45) NOT NULL,
  `lot_id` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`lot_id`))
ENGINE = InnoDB;

-- ------------------------------------------------------
-- Table `whereismyspot`.`USER`
-- ------------------------------------------------------
DROP TABLE IF EXISTS `whereismyspot`.`USER`;

CREATE TABLE IF NOT EXISTS `whereismyspot`.`USER` (
	`First_Name` VARCHAR(32) DEFAULT NULL,
	`Last_Name` VARCHAR(32) DEFAULT NULL,
	`UserName` VARCHAR(32) DEFAULT NULL,
	`Password` VARCHAR(32) DEFAULT NULL,
	`Email` VARCHAR(32) DEFAULT NULL,
	`Register_Date` DATE DEFAULT NULL)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `whereismyspot`.`SPACES`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `whereismyspot`.`SPACES` ;


CREATE TABLE IF NOT EXISTS `whereismyspot`.`SPACES` (
  `lot_id` VARCHAR(45) NOT NULL,
  `day` VARCHAR(9) NOT NULL,
  `spots_at_7` INT NOT NULL,
  `spots_at_8` INT NOT NULL,
  `spots_at_9` INT NOT NULL,
  `spots_at_10` INT NOT NULL,
  `spots_at_11` INT NOT NULL,
  `spots_at_12` INT NOT NULL,
  `spots_at_1` INT NOT NULL,
  `spots_at_2` INT NOT NULL,
  `spots_at_3` INT NOT NULL,
  `spots_at_4` INT NOT NULL,
  PRIMARY KEY (`lot_id`, `day`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
