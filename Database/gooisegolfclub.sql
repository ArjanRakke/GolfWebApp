-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema gooisegolfclub
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema gooisegolfclub
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `gooisegolfclub` DEFAULT CHARACTER SET utf8 ;
USE `gooisegolfclub` ;

-- -----------------------------------------------------
-- Table `gooisegolfclub`.`baanstatus`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gooisegolfclub`.`baanstatus` (
  `baan_id` INT(11) NOT NULL,
  `golfbaanbeschikbaar` VARCHAR(256) NOT NULL,
  `Qualifying` VARCHAR(256) NOT NULL,
  `zomerofwintergreens` VARCHAR(256) NOT NULL,
  `trolleysengolfkarren` VARCHAR(256) NOT NULL,
  `bemest` VARCHAR(256) NOT NULL,
  `onderhoud` VARCHAR(256) NOT NULL,
  `aankondiging` VARCHAR(256) NULL DEFAULT NULL,
  PRIMARY KEY (`baan_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `gooisegolfclub`.`leden`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gooisegolfclub`.`leden` (
  `NGF` INT(11) NOT NULL,
  `Voornaam` VARCHAR(50) NOT NULL,
  `Achternaam` VARCHAR(50) NOT NULL,
  `Telefoonnummer` TINYTEXT NOT NULL,
  `Emailadres` VARCHAR(50) NOT NULL,
  `Handicap` DECIMAL(3,1) NOT NULL,
  PRIMARY KEY (`NGF`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `gooisegolfclub`.`clubcommissie`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gooisegolfclub`.`clubcommissie` (
  `commissie_id` INT(11) NOT NULL AUTO_INCREMENT,
  `NGF` INT(11) NOT NULL,
  `Gebruikersnaam` VARCHAR(50) NOT NULL,
  `Wachtwoord` VARCHAR(256) NOT NULL,
  `Rol` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`commissie_id`),
  INDEX `fk_Clubcommissie_Leden_idx` (`NGF` ASC),
  CONSTRAINT `fk_Clubcommissie_Leden`
    FOREIGN KEY (`NGF`)
    REFERENCES `gooisegolfclub`.`leden` (`NGF`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `gooisegolfclub`.`wedstrijdschema`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gooisegolfclub`.`wedstrijdschema` (
  `wedstrijd_id` INT(11) NOT NULL,
  `Naam` VARCHAR(60) NOT NULL,
  `Type` VARCHAR(50) NOT NULL,
  `Holes` INT(11) NOT NULL,
  `Begindatum` DATE NOT NULL,
  PRIMARY KEY (`wedstrijd_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
