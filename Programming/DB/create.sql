create database ecobike;
use ecobike;

DROP TABLE IF EXISTS `bike`;

CREATE TABLE `bike` (
  `bikeId` varchar(100) NOT NULL,
  `type` int NOT NULL,
  `barcode` varchar(100) NOT NULL,
  `licencePlate` varchar(100) DEFAULT NULL,
  `value` float NOT NULL,
  `isBeingUsed` boolean DEFAULT FALSE,
  `imgUrl` text,
  `dockId` int,
  PRIMARY KEY (`bikeId`)
 --  KEY `FK_Bike_0` (`type`),
--   KEY `FK_Bike_1` (`dockId`),
--   CONSTRAINT `FK_Bike_0` FOREIGN KEY (`type`) REFERENCES `bike_type` (`id`),
--   CONSTRAINT `FK_Bike_1` FOREIGN KEY (`dock_id`) REFERENCES `dock` (`id`)
);