DROP DATABASE IF EXISTS `FamilyCareInventory`;
SET default_storage_engine=InnoDB;
SET NAMES utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE DATABASE IF NOT EXISTS FamilyCareInventory
    DEFAULT CHARACTER SET utf8mb4
    DEFAULT COLLATE utf8mb4_unicode_ci;
USE FamilyCareInventory;

DROP DATABASE IF EXISTS `FamilyCareInventory`;
SET default_storage_engine=InnoDB;
SET NAMES utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE DATABASE IF NOT EXISTS FamilyCareInventory
    DEFAULT CHARACTER SET utf8mb4
    DEFAULT COLLATE utf8mb4_unicode_ci;
USE FamilyCareInventory;

-- Tables

CREATE TABLE Employee (
                          emyId int(16) primary key AUTO_INCREMENT,
                          `name` nvarchar(255) NOT NULL,
                          role varchar(255) NOT NULL
);

CREATE TABLE Product (
                         productId int(16) primary key AUTO_INCREMENT,
                         product_name nvarchar(255) NOT NULL,
                         product_version nvarchar(255) NOT NULL,
                         inventory int(16) NOT NULL,
                         created_time Datetime
);

CREATE TABLE `Cost` (
                        costId int(16) primary key AUTO_INCREMENT,
                        name nvarchar(255),
                        amt float,
                        cost_time Date,
                        note nvarchar(255)
);

CREATE TABLE `Order` (
                         orderId int(16) primary key AUTO_INCREMENT,
                         name nvarchar(255),
                         employee_name nvarchar(255) NOT NULL,
                         customer_name nvarchar(225),
                         trans_time Datetime,
                         note nvarchar(255),
                         flag varchar(255),
                         method varchar(255),
                         total float
);

CREATE TABLE OrderLine (
                           orderId int(16) NOT NULL,
                           orderlineId int(16) NOT NULL,
                           product_name varchar(255),
                           product_version varchar(255),
                           quantity int,
                           price float
);

CREATE TABLE Buy (
                     buyId int(16) primary key AUTO_INCREMENT,
                     name nvarchar(255),
                     buy_time Date,
                     note nvarchar(255),
                     deposit float,
                     ship_time Date
);

CREATE TABLE BuyLine (
                         buyId int(16) NOT NULL,
                         buylineId int(16) NOT NULL,
                         product_name varchar(255),
                         product_version varchar(255),
                         quantity int,
                         price float
);

CREATE TABLE Account (
                         transId int primary key,
                         trans_name nvarchar(255),
                         trans_time Date,
                         trans_amt float,
                         cur_acc_amt float,
                         cur_cash_amt float,
                         total_amt float,
                         note nvarchar(255)
);
ALTER TABLE OrderLine
    ADD CONSTRAINT FK_ORDERLINE_REFERENCE_ORDER FOREIGN KEY (orderId) REFERENCES Buy (orderId)
        ON DELETE CASCADE

ALTER TABLE BuyLine
    ADD CONSTRAINT FK_BUYLINE_REFERENCE_BUY FOREIGN KEY (buyId) REFERENCES Buy (buyId)
        ON DELETE CASCADE

INSERT INTO Account
VALUES(1, "init", CURRENT_DATE(), 0, 1000, 2000, "init note")
