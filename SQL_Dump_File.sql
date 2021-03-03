-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema warehouse
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema warehouse
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `warehouse` DEFAULT CHARACTER SET utf8mb4 ;
USE `warehouse` ;

-- -----------------------------------------------------
-- Table `warehouse`.`client`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `warehouse`.`client` (
  `name` VARCHAR(64) NOT NULL,
  `adress` VARCHAR(128) NOT NULL,
  PRIMARY KEY (`name`),
  UNIQUE INDEX `name` (`name` ASC) VISIBLE,
  UNIQUE INDEX `adress` (`adress` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `warehouse`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `warehouse`.`product` (
  `name` VARCHAR(32) NOT NULL,
  `quantity` INT(4) UNSIGNED NULL DEFAULT NULL,
  `price` INT(4) UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`name`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `warehouse`.`my_order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `warehouse`.`my_order` (
  `id` INT(11) NOT NULL,
  `client_name` VARCHAR(64) NOT NULL,
  `product_name` VARCHAR(32) NOT NULL,
  `product_quantity` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `my_order_ibfk_1` (`client_name` ASC) VISIBLE,
  INDEX `my_order_ibfk_2` (`product_name` ASC) VISIBLE,
  CONSTRAINT `my_order_ibfk_1`
    FOREIGN KEY (`client_name`)
    REFERENCES `warehouse`.`client` (`name`),
  CONSTRAINT `my_order_ibfk_2`
    FOREIGN KEY (`product_name`)
    REFERENCES `warehouse`.`product` (`name`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
