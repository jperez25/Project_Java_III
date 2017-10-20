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
DROP TABLE IF EXISTS `whereismyspot`.`LOT`;

CREATE TABLE IF NOT EXISTS `whereismyspot`.`LOT` (
`lot_id` VARCHAR(45) NOT NULL,
  `hours` VARCHAR(255) NOT NULL,
  `image` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`lot_id`));
  
INSERT INTO lot values ('Vago North', '7am - 11pm', 'vagoNorth.png');
INSERT INTO lot values ('Vago South', '7am - 11pm', 'vagoSouth.png');
INSERT INTO lot values ('STEM', '7am - 11pm', 'stem.png');
INSERT INTO lot values ('Institute', '7am - 11pm', 'institute.png');
INSERT INTO lot values ('Southlawn', '7am - 11pm', 'southlawn.png');
INSERT INTO lot values ('Eckhart', '7am - 11pm', 'eckhart.png');
INSERT INTO lot values ('Dunham', '7am - 11pm', 'dunham.png');
INSERT INTO lot values ('UBH', '7am - 11pm', 'ubh.png');
INSERT INTO lot values ('Parolini', '7am - 11pm', 'parolini.png');

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
  PRIMARY KEY (`lot_id`, `day`));
  
  INSERT INTO `spaces` VALUES ('Dunham','Friday',23,3,4,4,4,4,4,4,4,4),
('Dunham','Monday',11,2,3,4,5,6,7,8,9,10),
('Dunham','Thursday',4,3,4,4,4,4,4,4,4,4),
('Dunham','Tuesday',7,3,4,4,4,4,4,4,4,4),
('Dunham','Wednesday',10,3,4,4,4,4,4,4,4,4),
('Eckhart','Friday',20,2,3,4,5,6,7,8,9,10),
('Eckhart','Monday',10,2,3,4,5,6,7,8,9,10),
('Eckhart','Thursday',9,2,3,4,3,6,7,8,9,10),
('Eckhart','Tuesday',1,2,3,4,6,6,7,8,9,10),
('Eckhart','Wednesday',1,2,3,4,2,6,7,8,9,10),
('Institute','Friday',1,2,3,4,7,6,7,8,9,10),
('Institute','Monday',1,2,3,4,2,6,7,8,9,10),
('Institute','Thursday',1,2,3,4,7,6,7,8,9,10),
('Institute','Tuesday',1,2,3,4,8,6,7,8,9,10),
('Institute','Wednesday',1,2,3,4,5,6,7,8,9,10),
('Parolini','Friday',1,2,3,4,5,6,7,8,9,10),
('Parolini','Monday',1,2,3,4,5,6,7,8,9,10),
('Parolini','Thursday',1,2,3,4,5,6,7,8,9,10),
('Parolini','Tuesday',1,2,3,4,5,6,7,8,9,10),
('Parolini','Wednesday',1,2,3,4,5,6,7,8,9,10),
('Southlawn','Friday',1,2,6,4,93,6,7,8,9,10),
('Southlawn','Monday',1,2,4,4,93,6,7,8,9,10),
('Southlawn','Thursday',1,2,3,4,93,6,7,8,9,10),
('Southlawn','Tuesday',1,2,3,4,93,6,7,8,9,10),
('Southlawn','Wednesday',1,2,3,4,93,6,7,8,9,10),
('Stem','Friday',1,2,9,4,8,6,7,8,9,10),
('Stem','Monday',1,2,4,4,8,6,7,8,9,10),
('Stem','Thursday',1,2,3,4,8,6,7,8,9,10),
('Stem','Tuesday',1,2,2,4,8,6,7,8,9,10),
('Stem','Wednesday',1,2,8,4,8,6,7,8,9,10),
('UBH','Friday',1,2,8,4,2,6,7,8,9,10),
('UBH','Monday',1,2,2,4,2,6,7,8,9,10),
('UBH','Thursday',1,2,9,4,2,6,7,8,9,10),
('UBH','Tuesday',1,2,2,4,2,6,7,8,9,10),
('UBH','Wednesday',1,2,1,4,2,6,7,8,9,10),
('Vago North','Friday',1,2,8,4,8,6,7,8,9,10),
('Vago North','Monday',1,2,3,4,8,6,7,8,9,10),
('Vago North','Thursday',1,2,2,4,8,6,7,8,9,10),
('Vago North','Tuesday',1,2,8,4,8,6,7,8,9,10),
('Vago North','Wednesday',1,2,2,4,8,6,7,8,9,10),
('Vago South','Friday',1,2,5,4,2,6,7,8,9,10),
('Vago South','Monday',1,2,7,4,2,6,7,8,9,10),
('Vago South','Thursday',1,2,4,4,2,6,7,8,9,10),
('Vago South','Tuesday',1,2,2,4,2,6,7,8,9,10),
('Vago South','Wednesday',1,2,6,4,2,6,7,8,9,10);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
