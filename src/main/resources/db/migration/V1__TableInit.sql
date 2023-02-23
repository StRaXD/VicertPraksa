-- MySQL Script generated by MySQL Workbench
-- Tue Dec 21 11:38:17 2021
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema HealthSystem
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema HealthSystem
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `HealthSystem` DEFAULT CHARACTER SET utf8 ;
USE `HealthSystem` ;

-- -----------------------------------------------------
-- Table `HealthSystem`.`hospital`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `HealthSystem`.`hospital` (
                                                         `id_hospital` INT NOT NULL AUTO_INCREMENT,
                                                         `hospital_name` VARCHAR(80) ,
    `city` VARCHAR(45) ,
    `zip_code` VARCHAR(10) ,
    `address` VARCHAR(45) ,
    PRIMARY KEY (`id_hospital`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `HealthSystem`.`doctor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `HealthSystem`.`doctor` (
                                                       `id_doctor` INT NOT NULL AUTO_INCREMENT,
                                                       `first_name` VARCHAR(20) ,
    `last_name` VARCHAR(20) ,
    `specialization` VARCHAR(45) ,
    `age` INT ,
    `hospital_id_hospital` INT NOT NULL,
    PRIMARY KEY (`id_doctor`, `hospital_id_hospital`),
    INDEX `fk_doctor_hospital_idx` (`hospital_id_hospital` ASC) VISIBLE,
    CONSTRAINT `fk_doctor_hospital`
    FOREIGN KEY (`hospital_id_hospital`)
    REFERENCES `HealthSystem`.`hospital` (`id_hospital`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `HealthSystem`.`review`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `HealthSystem`.`review` (
                                                       `id_review` INT NOT NULL AUTO_INCREMENT,
                                                       `comment` VARCHAR(500) ,
    `hospital_id_hospital` INT NOT NULL,
    PRIMARY KEY (`id_review`),
    INDEX `fk_review_hospital1_idx` (`hospital_id_hospital` ASC) VISIBLE,
    CONSTRAINT `fk_review_hospital1`
    FOREIGN KEY (`hospital_id_hospital`)
    REFERENCES `HealthSystem`.`hospital` (`id_hospital`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `HealthSystem`.`patient`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `HealthSystem`.`patient` (
                                                        `id_patient` INT NOT NULL AUTO_INCREMENT,
                                                        `first_name` VARCHAR(20) ,
    `last_name` VARCHAR(20) ,
    PRIMARY KEY (`id_patient`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `HealthSystem`.`medical_record`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `HealthSystem`.`medical_record` (
                                                               `id_medical` INT NOT NULL AUTO_INCREMENT,
                                                               `social_security` VARCHAR(45) ,
    `age` INT ,
    `vaccinated` TINYINT ,
    `city` VARCHAR(45) ,
    `address` VARCHAR(45) ,
    `patient_id_patient` INT NOT NULL,
    PRIMARY KEY (`id_medical`, `patient_id_patient`),
    INDEX `fk_medical_record_patient1_idx` (`patient_id_patient` ASC) VISIBLE,
    CONSTRAINT `fk_medical_record_patient1`
    FOREIGN KEY (`patient_id_patient`)
    REFERENCES `HealthSystem`.`patient` (`id_patient`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `HealthSystem`.`time_table`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `HealthSystem`.`time_table` (
                                                           `id_table` INT NOT NULL AUTO_INCREMENT,
                                                           `Date` DATE ,
                                                           `available` TINYINT ,
                                                           `doctor_id_doctor` INT NOT NULL,
                                                           PRIMARY KEY (`id_table`, `doctor_id_doctor`),
    INDEX `fk_time_table_doctor1_idx` (`doctor_id_doctor` ASC) VISIBLE,
    CONSTRAINT `fk_time_table_doctor1`
    FOREIGN KEY (`doctor_id_doctor`)
    REFERENCES `HealthSystem`.`doctor` (`id_doctor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `HealthSystem`.`patient_doctor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `HealthSystem`.`patient_doctor` (
                                                               `patient_id_patient` INT NOT NULL,
                                                               `doctor_id_doctor` INT NOT NULL,
                                                               PRIMARY KEY (`patient_id_patient`, `doctor_id_doctor`),
    INDEX `fk_patient_has_doctor_doctor1_idx` (`doctor_id_doctor` ASC) VISIBLE,
    INDEX `fk_patient_has_doctor_patient1_idx` (`patient_id_patient` ASC) VISIBLE,
    CONSTRAINT `fk_patient_has_doctor_patient1`
    FOREIGN KEY (`patient_id_patient`)
    REFERENCES `HealthSystem`.`patient` (`id_patient`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_patient_has_doctor_doctor1`
    FOREIGN KEY (`doctor_id_doctor`)
    REFERENCES `HealthSystem`.`doctor` (`id_doctor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
