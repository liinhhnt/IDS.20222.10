create database ecobike;
use ecobike;
select * from dock;


use ecobike;
DROP TABLE IF EXISTS `dock`;
CREATE TABLE `dock` (
  `dockId` int NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `noOfEmptyPoints` int DEFAULT 0,
  `noOfBikes` int DEFAULT 0,
  `area` int DEFAULT NULL,
  `imageUrl` text,
  PRIMARY KEY (`dockId`)
) ;

DROP TABLE IF EXISTS `bikeType`;
CREATE TABLE bikeType (
    `typeId` INT PRIMARY KEY,
    `name` VARCHAR(100) NOT NULL,
    `noSaddles` INT NOT NULL,
    `noRearSeats` INT NOT NULL,
    `noPedals` INT NOT NULL,
    `value` FLOAT NOT NULL
);

INSERT INTO bikeType VALUES (1, 'Standard Bike', 1, 1, 1, 400000), (2, 'Standard E-Bike', 1, 1, 1, 700000), (3, 'Twins Bike', 2, 1, 2, 550000);

DROP TABLE IF EXISTS `bike`;
CREATE TABLE `bike` (
  `bikeId` varchar(100) NOT NULL,
  `type` int NOT NULL,
  `barcode` varchar(100) NOT NULL,
  `licencePlate` varchar(100) DEFAULT NULL,
  `isBeingUsed` boolean DEFAULT FALSE,
  `imgUrl` text,
  `dockId` int,
  PRIMARY KEY (`bikeId`),
  KEY `FK_Bike_0` (`type`),
  KEY `FK_Bike_1` (`dockId`),
  CONSTRAINT `FK_Bike_0` FOREIGN KEY (`type`) REFERENCES `bikeType` (`typeId`),
  CONSTRAINT `FK_Bike_1` FOREIGN KEY (`dockId`) REFERENCES `dock` (`dockId`)
);

DELIMITER //
CREATE TRIGGER bike_created_trigger
AFTER INSERT ON bike FOR EACH ROW
BEGIN
    UPDATE dock
    SET noOfBikes = noOfBikes + 1
    WHERE dockId = NEW.dockId;
END;
//
DELIMITER ;





