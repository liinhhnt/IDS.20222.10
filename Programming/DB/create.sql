drop database ecobike;
create database ecobike;
use ecobike;

DROP TABLE IF EXISTS `bike`;
DROP TABLE IF EXISTS `bikeType`;
DROP TABLE IF EXISTS `dock`;
DROP TABLE IF EXISTS `card`;

use ecobike;
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



INSERT INTO `dock` VALUES 
	(1,'Hust Station','1st Dai Co Viet Street', 100, 0, 1000, 'https://www.ttz.com.vn/uploads/News/may-giu-xe-dh-bach-khoa.jpg'),
    (2,'Hoan Kiem Station','Trang Tien Street', 50, 0, 999,'https://th.bing.com/th/id/OIP.DfcFX9B00Y6I5Uw9Ckx5TQHaE8?w=288&h=192&c=7&r=0&o=5&dpr=1.6&pid=1.7&fbclid=IwAR0_LME57QWeJYPn1wvs_yI5lQd3PdnlZSUpy6ElbS3rl7-WbduUkpNl8rw'),
    (3,'To Hoang','67 To Hoang Lane', 30, 0, 500, 'https://th.bing.com/th/id/OIP.DfcFX9B00Y6I5Uw9Ckx5TQHaE8?w=288&h=192&c=7&r=0&o=5&dpr=1.6&pid=1.7&fbclid=IwAR0_LME57QWeJYPn1wvs_yI5lQd3PdnlZSUpy6ElbS3rl7-WbduUkpNl8rw'),
    (4,'To Hoang','67 To Hoang Lane', 30, 0, 500, 'https://th.bing.com/th/id/OIP.DfcFX9B00Y6I5Uw9Ckx5TQHaE8?w=288&h=192&c=7&r=0&o=5&dpr=1.6&pid=1.7&fbclid=IwAR0_LME57QWeJYPn1wvs_yI5lQd3PdnlZSUpy6ElbS3rl7-WbduUkpNl8rw'),
    (5,'To Hoang','67 To Hoang Lane', 30, 0, 500, 'https://th.bing.com/th/id/OIP.DfcFX9B00Y6I5Uw9Ckx5TQHaE8?w=288&h=192&c=7&r=0&o=5&dpr=1.6&pid=1.7&fbclid=IwAR0_LME57QWeJYPn1wvs_yI5lQd3PdnlZSUpy6ElbS3rl7-WbduUkpNl8rw'),
    (6,'To Hoang','67 To Hoang Lane', 30, 0, 500, 'https://th.bing.com/th/id/OIP.DfcFX9B00Y6I5Uw9Ckx5TQHaE8?w=288&h=192&c=7&r=0&o=5&dpr=1.6&pid=1.7&fbclid=IwAR0_LME57QWeJYPn1wvs_yI5lQd3PdnlZSUpy6ElbS3rl7-WbduUkpNl8rw'),
    (7,'To Hoang','67 To Hoang Lane', 30, 0, 500, 'https://th.bing.com/th/id/OIP.DfcFX9B00Y6I5Uw9Ckx5TQHaE8?w=288&h=192&c=7&r=0&o=5&dpr=1.6&pid=1.7&fbclid=IwAR0_LME57QWeJYPn1wvs_yI5lQd3PdnlZSUpy6ElbS3rl7-WbduUkpNl8rw'),
    (8,'To Hoang','67 To Hoang Lane', 30, 0, 500, 'https://th.bing.com/th/id/OIP.DfcFX9B00Y6I5Uw9Ckx5TQHaE8?w=288&h=192&c=7&r=0&o=5&dpr=1.6&pid=1.7&fbclid=IwAR0_LME57QWeJYPn1wvs_yI5lQd3PdnlZSUpy6ElbS3rl7-WbduUkpNl8rw'),
    (9,'To Hoang','67 To Hoang Lane', 30, 0, 500, 'https://th.bing.com/th/id/OIP.DfcFX9B00Y6I5Uw9Ckx5TQHaE8?w=288&h=192&c=7&r=0&o=5&dpr=1.6&pid=1.7&fbclid=IwAR0_LME57QWeJYPn1wvs_yI5lQd3PdnlZSUpy6ElbS3rl7-WbduUkpNl8rw'),
    (10,'To Hoang','67 To Hoang Lane', 30, 0, 500, 'https://th.bing.com/th/id/OIP.DfcFX9B00Y6I5Uw9Ckx5TQHaE8?w=288&h=192&c=7&r=0&o=5&dpr=1.6&pid=1.7&fbclid=IwAR0_LME57QWeJYPn1wvs_yI5lQd3PdnlZSUpy6ElbS3rl7-WbduUkpNl8rw');

CREATE TABLE bikeType (
    `typeId` INT PRIMARY KEY,
    `name` VARCHAR(100) NOT NULL,
    `noSaddles` INT NOT NULL,
    `noRearSeats` INT NOT NULL,
    `noPedals` INT NOT NULL,
    `value` INT NOT NULL
);


INSERT INTO bikeType VALUES 
	(1, 'Standard Bike', 1, 1, 1, 400000), 
    (2, 'Standard E-Bike', 1, 1, 1, 700000), 
    (3, 'Twins Bike', 2, 1, 2, 550000);

CREATE TABLE `bike` (
  `bikeId` int NOT NULL,
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

CREATE TABLE `ebike` (
  `bikeId` int NOT NULL,
  `battery` int NOT NULL,
  `remainingTime` time NOT NULL,
  PRIMARY KEY (`bikeId`),
  KEY `FK_EBike_0` (`bikeId`),
  CONSTRAINT `FK_EBike_0` FOREIGN KEY (`bikeId`) REFERENCES `bike` (`bikeId`)
);

CREATE TABLE `card` (
  `cardNumber` varchar(100) NOT NULL,
  `cardHolderName` varchar(45) DEFAULT NULL,
  `expDate` varchar(5) DEFAULT NULL,
  `secureCode` varchar(3) NOT NULL,
  `isBeingUsed` bool DEFAULT false,
  `balance` int DEFAULT 1000000,
  PRIMARY KEY (`cardNumber`)
) ;

CREATE TABLE `invoice`(
`invoiceId` int NOT NULL AUTO_INCREMENT,
`cardNumber` varchar(100) NOT NULL,
`bikeId` varchar(100) NOT NULL,
`startTime` DATETIME,
`totalRentTime` INT,
`totalFee` int,
`deposit` int,
`status` int,
PRIMARY KEY (`invoiceId`),
KEY `FK_Invoice_0` (`cardNumber`),
CONSTRAINT `FK_Invoice_0` FOREIGN KEY (`cardNumber`) REFERENCES `card` (`cardNumber`)
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

INSERT INTO `bike` VALUES 
	(1,1,'S001','99-G1 12345',false,'https://firstbikeafrica.co.za/cdn/shop/products/FirstBike-Fat-Cross-Green-2_1024x.jpg?v=1603646435', 1), 
    (2,2,'SE001','99-G1 12345',false,'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQk3HzljMmL2W91o7mNUwRO09K2jcHocFt0gQ&usqp=CAU', 1), 
    (3,3,'T001','99-G1 12345',false,'https://cdn-amz.woka.io/images/I/71uxi96rVDS.jpg', 1),
    (4,2,'SE002','99-G1 12345',false,'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQk3HzljMmL2W91o7mNUwRO09K2jcHocFt0gQ&usqp=CAU', 1),
    (5,3,'T001','99-G1 12345',false,'https://cdn-amz.woka.io/images/I/71uxi96rVDS.jpg', 1),
    (6,3,'T001','99-G1 12345',false,'https://cdn-amz.woka.io/images/I/71uxi96rVDS.jpg', 1),
    (7,3,'T001','99-G1 12345',false,'https://cdn-amz.woka.io/images/I/71uxi96rVDS.jpg', 2),
    (8,3,'T001','99-G1 12345',false,'https://cdn-amz.woka.io/images/I/71uxi96rVDS.jpg', 2);

INSERT INTO `ebike` VALUES
	(2, 99, '04:30:00'),
    (4, 50, '02:00:00');



INSERT INTO `card` VALUES 
	('139396_group10_2023', 'Group10', '12/25', '123', false, 1000000),
    ('824751_linhnt_2023', 'Nguyen Thi Linh', '12/25', '456', true, 750000),
    ('824751_linhvt_2023', 'Vu Thuy Linh', '12/25', '789', false, 0),
    ('824751_linhpk_2023', 'Pham Khanh Linh', '12/25', '234', false, 100000),
    ('824751_longbt_2023', 'Bui Thanh Long', '12/25', '345', false, 750000);
	
