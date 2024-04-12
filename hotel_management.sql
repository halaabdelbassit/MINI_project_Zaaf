-- Active: 1712009266219@@127.0.0.1@3306@hotel_management
create database if not exists hotel_management;

use hotel_management;

CREATE TABLE `bills` (
    `billID` int(50) PRIMARY KEY NOT NULL, `resID` int(50) NOT NULL, `billDate` date NOT NULL, `billAmount` int(50) NOT NULL
);

CREATE TABLE `customers` (
    `ctmrIDNumber` int(50) PRIMARY KEY, `ctmrName` varchar(50) NOT NULL, `ctmrNationality` varchar(20) NOT NULL, `ctmrGender` varchar(10) NOT NULL, `ctmrPhoneNo` int(50) NOT NULL, `ctmrEmail` varchar(50) NOT NULL, `ctmrDiscount` int(50) NOT NULL DEFAULT 0
);

CREATE TABLE `reservations` (
    `resID` int(50) PRIMARY KEY, `ctmrIDNumber` int(10) NOT NULL, `rNumber` varchar(20) NOT NULL, `checkInDate` date NOT NULL, `checkOutDate` date NOT NULL, `status` varchar(20) NOT NULL DEFAULT 'Checked In'
);

CREATE TABLE `rooms` (
    `rNumber` varchar(20) PRIMARY KEY, `rType` varchar(50) NOT NULL, `price` int(20) NOT NULL, `status` varchar(50) NOT NULL DEFAULT 'Not Booked'
);

CREATE TABLE `users` (
    `id` int(11) PRIMARY KEY AUTO_INCREMENT, `name` varchar(200) NOT NULL, `username` varchar(200) NOT NULL UNIQUE, `password` varchar(200) NOT NULL, `address` varchar(200) NOT NULL, `status` varchar(20) DEFAULT null
);

ALTER TABLE `bills`
ADD CONSTRAINT `fk_bills_res` FOREIGN KEY (`resID`) REFERENCES `reservations` (`resID`) ON UPDATE CASCADE;

ALTER TABLE `reservations`
ADD CONSTRAINT `fk_customers_res` FOREIGN KEY (`ctmrIDNumber`) REFERENCES `customers` (`ctmrIDNumber`) ON UPDATE CASCADE;

ALTER TABLE `reservations`
ADD CONSTRAINT `fk_rooms_res` FOREIGN KEY (`rNumber`) REFERENCES `rooms` (`rNumber`) ON UPDATE CASCADE;

-- CREATE FUNCTION addCustomer(
--    ctmrIDNumber  int(50),
-- ctmrName varchar(50),
-- ctmrNationality varchar(20),
-- ctmrGender varchar(10),
-- ctmrPhoneNo int(50),
-- ctmrEmail varchar(50)
-- ) RETURNS varchar(255) DETERMINISTIC READS SQL DATA BEGIN

-- DECLARE msg varchar(255);
-- DECLARE userExist BOOLEAN;

-- SET userExist = (SELECT EXISTS(SELECT * FROM customers WHERE ctmrIDNumber = ctmrIDNumber));
-- if userExist  THEN
-- SET msg = 'Customer already exists';
-- ELSE
-- INSERT INTO customers (ctmrIDNumber, ctmrName, ctmrNationality, ctmrGender, ctmrPhoneNo, ctmrEmail) VALUES (ctmrIDNumber, ctmrName, ctmrNationality, ctmrGender, ctmrPhoneNo, ctmrEmail);

CREATE FUNCTION signUp(name varchar(200), username varchar(200), password varchar(200), address varchar(200)) RETURNS varchar(255) DETERMINISTIC READS SQL DATA 
BEGIN 
    DECLARE msg varchar(255);
    DECLARE userExist BOOLEAN;
    
    SET userExist = (
        SELECT EXISTS (
            SELECT *
            FROM users
            WHERE username = username
        )
    );
    
    IF NOT userExist THEN
        INSERT INTO users (name, username, password, address)
        VALUES (name, username, password, address);
        SET msg = 'successfully sign up';
    ELSE
        SET msg = 'User already exists';
    END IF;
    
    RETURN msg;
END; 


