create database if not exists hotel_management_system;

use hotel_management_system;


CREATE TABLE `bills`
(
    `billID`     int(50) PRIMARY KEY NOT NULL,
    `resID`      int(50)             NOT NULL,
    `billDate`   date                NOT NULL,
    `billAmount` int(50)             NOT NULL
);

CREATE TABLE `customers`
(
    `ctmrIDNumber`    int(50) PRIMARY KEY NOT NULL,
    `ctmrName`        varchar(50)         NOT NULL,
    `ctmrNationality` varchar(20)         NOT NULL,
    `ctmrGender`      varchar(10)         NOT NULL,
    `ctmrPhoneNo`     int(50)             NOT NULL,
    `ctmrEmail`       varchar(50)         NOT NULL,
    `ctmrDiscount`    int(50) NOT NULL DEFAULT 0
);

CREATE TABLE `reservations`
(
    `resID`        int(50) PRIMARY KEY NOT NULL,
    `ctmrIDNumber` int(10)             NOT NULL,
    `rNumber`      varchar(20)         NOT NULL,
    `checkInDate`  date                NOT NULL,
    `checkOutDate` date                NOT NULL,
    `status`       varchar(20)         NOT NULL DEFAULT 'Checked In'
);

CREATE TABLE `rooms`
(
    `rNumber` varchar(20) PRIMARY KEY NOT NULL,
    `rType`   varchar(50)             NOT NULL,
    `price`   int(20)                 NOT NULL,
    `status`  varchar(50)             NOT NULL DEFAULT 'Not Booked'
);

CREATE TABLE `users`
(
    `id`       int(11) PRIMARY KEY NOT NULL,
    `name`     varchar(200)        NOT NULL,
    `username` varchar(200)        NOT NULL,
    `password` varchar(200)        NOT NULL,
    `address`  varchar(200)        NOT NULL,
    `status`   varchar(20) DEFAULT null
);

ALTER TABLE `bills`
    ADD CONSTRAINT `fk_bills_res` FOREIGN KEY (`resID`) REFERENCES `reservations` (`resID`) ON UPDATE CASCADE;

ALTER TABLE `reservations`
    ADD CONSTRAINT `fk_customers_res` FOREIGN KEY (`ctmrIDNumber`) REFERENCES `customers` (`ctmrIDNumber`) ON UPDATE CASCADE;

ALTER TABLE `reservations`
    ADD CONSTRAINT `fk_rooms_res` FOREIGN KEY (`rNumber`) REFERENCES `rooms` (`rNumber`) ON UPDATE CASCADE;
