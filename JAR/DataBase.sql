-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: usermanagmentsystem
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `usermanagmentsystem`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `usermanagmentsystem` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `usermanagmentsystem`;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `username` varchar(45) NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `userType` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `firstname` varchar(45) DEFAULT NULL,
  `lastname` varchar(45) DEFAULT NULL,
  `branch` varchar(45) DEFAULT NULL,
  `personID` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('HallelWeil','7891011','hallel@gmail.com','BranchManager','054123456','hallel','weil','Braude','200000000'),('OfirGalai','223344','ofir@gmail.com','BranchEmployee','051444888','ofir','galai','Braude','300000000'),('Omersh','1234567','ommersh@gmail.comCEO','CEO','0545970626',' Omer','shamir','Braude','314763970'),('Omershami','667788','om@gmail.com','Courier','056670000','omerr','shami','Braude','600000000'),('OrBalmas','123456','or@gmail.com','NonAuthorizedCustomer','0569999917','or','balmas',' ','100000000'),('RonenZeyan','778899','ronen@gmail.com','CustomerServiceEmloyee','0505813931','ronen','zeyan','Braude','4000000000'),('WorkHallel','112233','hallel2@gmail.com','MarketingEmployee','0508889976','hallelll','wiel','Braude','500000000');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Current Database: `zelisystem`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `zelisystem` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `zelisystem`;

--
-- Table structure for table `branch`
--

DROP TABLE IF EXISTS `branch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `branch` (
  `branchName` varchar(45) NOT NULL,
  `phoneNumber` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`branchName`),
  UNIQUE KEY `branchName_UNIQUE` (`branchName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `branch`
--

LOCK TABLES `branch` WRITE;
/*!40000 ALTER TABLE `branch` DISABLE KEYS */;
INSERT INTO `branch` VALUES ('Ariel','0877799565'),('Braude','0433221166'),('Haifa','0566688001');
/*!40000 ALTER TABLE `branch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `complaint`
--

DROP TABLE IF EXISTS `complaint`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `complaint` (
  `complaintNumber` int NOT NULL AUTO_INCREMENT,
  `responsibleEmployeeUsername` varchar(45) DEFAULT NULL,
  `complaint` varchar(200) DEFAULT NULL,
  `answer` varchar(200) DEFAULT NULL,
  `compensation` double DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `customerID` varchar(15) DEFAULT NULL,
  `creationTime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`complaintNumber`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `complaint`
--

LOCK TABLES `complaint` WRITE;
/*!40000 ALTER TABLE `complaint` DISABLE KEYS */;
INSERT INTO `complaint` VALUES (12,'RonenZeyan','the flowers were sad :(','im sorry to hear that',0,'Completed','123456789','2022-04-06 12:19:05'),(13,'RonenZeyan','the flowers were sad :(','im sorry to hear that',0,'Completed','123456789','2022-04-06 12:19:09'),(14,'RonenZeyan','the flowers were sad :(','im sorry to hear that',0,'Completed','123456789','2022-04-06 12:19:10'),(15,'RonenZeyan','the flowers were sad :(','im sorry to hear that',0,'Completed','123456789','2022-05-06 12:19:10'),(16,'RonenZeyan','the flowers were sad :(','im sorry to hear that',0,'Completed','123456789','2022-05-06 12:19:11'),(17,'RonenZeyan','the flowers were sad :(','im sorry to hear that',0,'Completed','123456789','2022-05-06 12:19:11'),(18,'RonenZeyan','the flowers were sad :(','im sorry to hear that',0,'Completed','123456789','2022-05-06 12:19:11'),(19,'RonenZeyan','the flowers were sad :(','im sorry to hear that',0,'Completed','123456789','2022-05-06 12:19:12'),(20,'RonenZeyan','the flowers were sad :(','im sorry to hear that',0,'Completed','123456789','2022-05-06 12:19:13'),(21,'RonenZeyan','the flowers were sad :(','im sorry to hear that',0,'Completed','123456789','2022-05-06 12:19:13'),(22,'RonenZeyan','the flowers were sad :(','im sorry to hear that',0,'Completed','123456789','2022-05-06 12:19:13'),(23,'RonenZeyan','the flowers were sad :(','ok',0,'Completed','123456789','2022-06-06 12:19:14'),(24,'RonenZeyan','the flowers were sad :(','ok',0,'Completed','123456789','2022-06-06 12:19:14'),(25,'RonenZeyan','the flowers were sad :(','ok',0,'Completed','123456789','2022-06-06 12:19:14');
/*!40000 ALTER TABLE `complaint` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `creditdetails`
--

DROP TABLE IF EXISTS `creditdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `creditdetails` (
  `username` varchar(45) NOT NULL,
  `cardInfo` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `creditdetails`
--

LOCK TABLES `creditdetails` WRITE;
/*!40000 ALTER TABLE `creditdetails` DISABLE KEYS */;
INSERT INTO `creditdetails` VALUES ('OrBalmas','1234-5678-9101-1112 25/12');
/*!40000 ALTER TABLE `creditdetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `deliverydetails`
--

DROP TABLE IF EXISTS `deliverydetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `deliverydetails` (
  `orderNumber` varchar(45) NOT NULL,
  `firstName` varchar(45) DEFAULT NULL,
  `lastName` varchar(45) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `phoneNumber` varchar(45) DEFAULT NULL,
  `comments` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`orderNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `deliverydetails`
--

LOCK TABLES `deliverydetails` WRITE;
/*!40000 ALTER TABLE `deliverydetails` DISABLE KEYS */;
INSERT INTO `deliverydetails` VALUES ('52','Or','Balmas','idk 5 karmiel 6','','');
/*!40000 ALTER TABLE `deliverydetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
  `orderNumber` int NOT NULL AUTO_INCREMENT,
  `orderDate` timestamp NULL DEFAULT NULL,
  `arrivalDate` timestamp NULL DEFAULT NULL,
  `homeDelivery` tinyint DEFAULT NULL,
  `branchName` varchar(45) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `customerID` varchar(45) DEFAULT NULL,
  `personalLetter` varchar(200) DEFAULT NULL,
  `orderStatus` varchar(45) DEFAULT NULL,
  `data` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`orderNumber`),
  UNIQUE KEY `orderID_UNIQUE` (`orderNumber`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (51,'2022-05-01 11:27:43','2022-05-07 12:45:00',0,'Braude',72,'OrBalmas','null','COLLECTED','\n20% off for your first order!'),(52,'2022-05-01 11:28:35','2022-05-10 07:00:00',1,'Braude',188,'OrBalmas','','COLLECTED','18 for the delivery!'),(53,'2022-05-03 11:28:46','2022-05-06 15:45:00',0,'Braude',160,'OrBalmas','null','COLLECTED',''),(54,'2022-05-04 11:29:00','2022-05-06 15:45:00',0,'Braude',135,'OrBalmas','null','COLLECTED',''),(55,'2022-05-06 11:29:33','2022-05-06 15:45:00',0,'Braude',417,'OrBalmas','null','COLLECTED',''),(56,'2022-05-06 11:29:50','2022-05-06 15:45:00',0,'Braude',230,'OrBalmas','null','COLLECTED',''),(57,'2022-05-07 11:30:51','2022-05-06 14:45:00',0,'Braude',142,'OrBalmas','null','COLLECTED',''),(58,'2022-05-08 11:31:00','2022-05-06 14:45:00',0,'Braude',235,'OrBalmas','null','COLLECTED',''),(59,'2022-05-08 11:31:06','2022-05-06 14:45:00',0,'Braude',80,'OrBalmas','null','COLLECTED',''),(60,'2022-05-08 11:31:19','2022-05-06 14:45:00',0,'Braude',230,'OrBalmas','null','COLLECTED',''),(61,'2022-05-12 11:31:30','2022-05-06 14:45:00',0,'Braude',205,'OrBalmas','null','COLLECTED',''),(62,'2022-05-14 11:32:39','2022-05-06 15:45:00',0,'Braude',335,'OrBalmas','null','COLLECTED',''),(63,'2022-05-22 11:35:51','2022-05-16 09:45:00',0,'Braude',70,'OrBalmas','null','COLLECTED',''),(64,'2022-05-24 11:36:03','2022-05-24 09:45:00',0,'Braude',140,'OrBalmas','null','COLLECTED',''),(65,'2022-06-05 12:28:41','2022-06-24 09:20:00',0,'Braude',20,'OrBalmas','null','COLLECTED',''),(66,'2022-06-06 12:28:50','2022-06-24 09:20:00',0,'Braude',60,'OrBalmas','null','COLLECTED',''),(67,'2022-06-06 12:29:01','2022-07-02 09:20:00',0,'Braude',75,'OrBalmas','null','COLLECTED',''),(68,'2022-06-12 12:29:11','2022-06-21 09:20:00',0,'Braude',107,'OrBalmas','null','COLLECTED',''),(69,'2022-06-13 12:29:22','2022-06-06 18:20:00',0,'Braude',145,'OrBalmas','null','COLLECTED',''),(70,'2022-06-22 12:29:27','2022-06-06 18:20:00',0,'Braude',100,'OrBalmas','null','COLLECTED',''),(71,'2022-06-26 12:29:32','2022-06-06 18:20:00',0,'Braude',15,'OrBalmas','null','COLLECTED','');
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `productID` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `colors` varchar(45) DEFAULT NULL,
  `image` blob,
  `category` varchar(45) DEFAULT NULL,
  `oldPrice` double DEFAULT '0',
  PRIMARY KEY (`productID`),
  UNIQUE KEY `ProductID_UNIQUE` (`productID`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (32,'Rose',5,'a very pertty rose','red',NULL,'singleItems',0),(33,'Sunflower',3,'a very pertty sunflower','yellow',NULL,'singleItems',0),(34,'Lily',2,'a very pertty lily','blue',NULL,'singleItems',0),(35,'Tulip',6,'a very pertty tulip','pink',NULL,'singleItems',0),(36,'Orchid',8,'a very pertty purple','purple',NULL,'singleItems',0),(37,'Bouquet of chrysanthemums',20,'An amazing bouquet for the celebration','white',NULL,'birthdayFlowers',0),(38,'A love bouquet',25,'An amazing bouquet for the celebration of love ;)','pink',NULL,'AnniversaryFlowers',0),(39,'White bouquet',50,'a great way to celebrate a wedding','white',NULL,'weddingFlowers',0),(40,'Rainbow bouquet',35,'all the colors of the rainbow','colorful',NULL,'congratulationFlowers',0),(41,'new baby boy',40,'a great way to celebrate a new baby!','blue',NULL,'babyFlowers',0),(42,'new baby girl',40,'a great way to celebrate a new baby!','pink',NULL,'babyFlowers',0),(43,'black as my heart',70,'omer favorite!','black',NULL,'birthdayFlowers',0),(44,'vase',10,'a very beautiful vase','white',NULL,'birthdayFlowers',0),(45,'red wedding',85,'for people who love red','red',NULL,'weddingFlowers',0),(46,'happy new year',100,'for fancy people','gold',NULL,'congratulationFlowers',0),(47,'sorry about your loss',30,'for sad people','black',NULL,'congratulationFlowers',0),(48,'Blue Sage',15,'to make you feel good!','11',NULL,'AnniversaryFlowers',0),(49,'Field Pansy',17,'good for babies!','colorful',NULL,'babyFlowers',0),(50,'Ivy Geranium',12,'good for angery people','red',NULL,'AnniversaryFlowers',0),(51,'a happy wedding',100,'for very fancy wedding','silver',NULL,'weddingFlowers',0),(52,'a white wedding',120,'very good for people who like white','white ',NULL,'weddingFlowers',0),(53,'flower girl',30,'very good for flower girls at you wedding!!','pink',NULL,'weddingFlowers',0),(54,'Bridesmaids',30,'very good for Bridesmaidsat you wedding!!','pruple',NULL,'weddingFlowers',0),(55,'i forgot u ',70,'for man who forget its today!','yellow',NULL,'AnniversaryFlowers',0),(56,'i will never forget u!',60,'for man who  didnt forget its today!','pink',NULL,'AnniversaryFlowers',0),(57,'happy birthday',60,'happy !!','blue',NULL,'birthdayFlowers',0);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productinorder`
--

DROP TABLE IF EXISTS `productinorder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productinorder` (
  `orderNumber` int NOT NULL,
  `name` varchar(100) NOT NULL,
  `price` double DEFAULT NULL,
  `amount` int DEFAULT NULL,
  `category` varchar(45) DEFAULT NULL,
  `data` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`orderNumber`,`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productinorder`
--

LOCK TABLES `productinorder` WRITE;
/*!40000 ALTER TABLE `productinorder` DISABLE KEYS */;
INSERT INTO `productinorder` VALUES (51,'black as my heart',70,1,'birthdayFlowers',''),(51,'Bouquet of chrysanthemums',20,1,'birthdayFlowers',''),(52,'a happy wedding',100,1,'weddingFlowers',''),(52,'happy birthday',60,1,'birthdayFlowers',''),(52,'vase',10,1,'birthdayFlowers',''),(53,'a white wedding',120,1,'weddingFlowers',''),(53,'new baby girl',40,1,'babyFlowers',''),(54,'happy new year',100,1,'congratulationFlowers',''),(54,'Rainbow bouquet',35,1,'congratulationFlowers',''),(55,'Field Pansy',17,1,'babyFlowers',''),(55,'new baby boy',40,2,'babyFlowers',''),(55,'new baby girl',40,8,'babyFlowers',''),(56,'happy new year',100,2,'congratulationFlowers',''),(56,'sorry about your loss',30,1,'congratulationFlowers',''),(57,'i forgot u ',70,1,'AnniversaryFlowers',''),(57,'i will never forget u!',60,1,'AnniversaryFlowers',''),(57,'Ivy Geranium',12,1,'AnniversaryFlowers',''),(58,'a happy wedding',100,1,'weddingFlowers',''),(58,'red wedding',85,1,'weddingFlowers',''),(58,'White bouquet',50,1,'weddingFlowers',''),(59,'new baby boy',40,1,'babyFlowers',''),(59,'new baby girl',40,1,'babyFlowers',''),(60,'a happy wedding',100,2,'weddingFlowers',''),(60,'Bridesmaids',30,1,'weddingFlowers',''),(61,'a white wedding',120,1,'weddingFlowers',''),(61,'red wedding',85,1,'weddingFlowers',''),(62,'a happy wedding',100,1,'weddingFlowers',''),(62,'a white wedding',120,1,'weddingFlowers',''),(62,'flower girl',30,1,'weddingFlowers',''),(62,'red wedding',85,1,'weddingFlowers',''),(63,'black as my heart',70,1,'birthdayFlowers',''),(64,'black as my heart',70,1,'birthdayFlowers',''),(64,'happy birthday',60,1,'birthdayFlowers',''),(64,'vase',10,1,'birthdayFlowers',''),(65,'Bouquet of chrysanthemums',20,1,'birthdayFlowers',''),(66,'happy birthday',60,1,'birthdayFlowers',''),(67,'new baby boy',40,1,'babyFlowers',''),(67,'Rainbow bouquet',35,1,'congratulationFlowers',''),(68,'A love bouquet',25,1,'AnniversaryFlowers',''),(68,'i forgot u ',70,1,'AnniversaryFlowers',''),(68,'Ivy Geranium',12,1,'AnniversaryFlowers',''),(69,'i will never forget u!',60,1,'AnniversaryFlowers',''),(69,'red wedding',85,1,'weddingFlowers',''),(70,'a happy wedding',100,1,'weddingFlowers',''),(71,'Blue Sage',15,1,'AnniversaryFlowers','');
/*!40000 ALTER TABLE `productinorder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `promotion`
--

DROP TABLE IF EXISTS `promotion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `promotion` (
  `promotionID` int NOT NULL AUTO_INCREMENT,
  `productID` int DEFAULT NULL,
  `discount` double DEFAULT NULL,
  `promotionText` varchar(200) DEFAULT NULL,
  `creationDate` timestamp NULL DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`promotionID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promotion`
--

LOCK TABLES `promotion` WRITE;
/*!40000 ALTER TABLE `promotion` DISABLE KEYS */;
/*!40000 ALTER TABLE `promotion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report`
--

DROP TABLE IF EXISTS `report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `report` (
  `branchName` varchar(45) NOT NULL,
  `type` varchar(45) NOT NULL,
  `year` int NOT NULL,
  `month` int NOT NULL,
  `data` blob,
  PRIMARY KEY (`branchName`,`type`,`year`,`month`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report`
--

LOCK TABLES `report` WRITE;
/*!40000 ALTER TABLE `report` DISABLE KEYS */;
INSERT INTO `report` VALUES ('ALL','QUARTERLY_ORDERS_REPORT',2022,2,_binary 'rO0ABXNyABxyZXBvcnQuUXVhcnRlcmx5T3JkZXJzUmVwb3J0AAAAAAAAAAECAAVJAAt0b3RhbE9yZGVyc1sAFGF2YXJhZ2VNb250aGx5T3JkZXJzdAACW0RMABBtb3N0UG9wdWxhckl0ZW1zdAAVTGphdmEvdXRpbC9BcnJheUxpc3Q7TAARb3JkZXJzUGVyQ2F0ZWdvcnl0ABNMamF2YS91dGlsL0hhc2hNYXA7WwAMb3JkZXJzUGVyRGF5dAADW1tJeHIADXJlcG9ydC5SZXBvcnQAAAAAAAAAAgIABEkABW1vbnRoSQAEeWVhckwACkJyYW5jaE5hbWV0ABJMamF2YS9sYW5nL1N0cmluZztMAAR0eXBldAATTHJlcG9ydC9SZXBvcnRUeXBlO3hwAAAAAgAAB+Z0AANBTEx+cgARcmVwb3J0LlJlcG9ydFR5cGUAAAAAAAAAABIAAHhyAA5qYXZhLmxhbmcuRW51bQAAAAAAAAAAEgAAeHB0ABdRVUFSVEVSTFlfT1JERVJTX1JFUE9SVAAAAC11cgACW0Q+powUq2NaHgIAAHhwAAAAA0ACo9cKPXCkAAAAAAAAAABAEq4UeuFHrnNyABNqYXZhLnV0aWwuQXJyYXlMaXN0eIHSHZnHYZ0DAAFJAARzaXpleHAAAAADdwQAAAADdAAAdAAPUmFpbmJvdyBib3VxdWV0dAANbmV3IGJhYnkgZ2lybHhzcgARamF2YS51dGlsLkhhc2hNYXAFB9rBwxZg0QMAAkYACmxvYWRGYWN0b3JJAAl0aHJlc2hvbGR4cD9AAAAAAAAMdwgAAAAQAAAABXQAC2JhYnlGbG93ZXJzc3IAEWphdmEubGFuZy5JbnRlZ2VyEuKgpPeBhzgCAAFJAAV2YWx1ZXhyABBqYXZhLmxhbmcuTnVtYmVyhqyVHQuU4IsCAAB4cAAAAA90AA53ZWRkaW5nRmxvd2Vyc3NxAH4AGAAAABB0AA9iaXJ0aGRheUZsb3dlcnNzcQB+ABgAAAAKdAAVY29uZ3JhdHVsYXRpb25GbG93ZXJzc3EAfgAYAAAABnQAEkFubml2ZXJzYXJ5Rmxvd2Vyc3NxAH4AGAAAAAh4dXIAA1tbSRf35E8Zj4k8AgAAeHAAAAADdXIAAltJTbpgJnbqsqUCAAB4cAAAAB8AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAdXEAfgAlAAAAHwAAAAAAAAAFAAAAAgAAAAIAAAAFAAAABwAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAB1cQB+ACUAAAAfAAAABQAAAAAAAAABAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA=='),('ALL','QUARTERLY_REVENUE_REPORT',2022,2,_binary 'rO0ABXNyAB1yZXBvcnQuUXVhcnRlcmx5UmV2ZW51ZVJlcG9ydAAAAAAAAAABAgAFRAAWYXZlcmFnZVJldmVudWVQZXJPcmRlckQADHRvdGFsUmV2ZW51ZVsAFWF2YXJhZ2VNb250aGx5UmV2ZW51ZXQAAltETAASbW9zdFByb2ZpdGFibGVJdGVtdAAVTGphdmEvdXRpbC9BcnJheUxpc3Q7WwANcmV2ZW51ZVBlckRheXQAA1tbRHhyAA1yZXBvcnQuUmVwb3J0AAAAAAAAAAICAARJAAVtb250aEkABHllYXJMAApCcmFuY2hOYW1ldAASTGphdmEvbGFuZy9TdHJpbmc7TAAEdHlwZXQAE0xyZXBvcnQvUmVwb3J0VHlwZTt4cAAAAAIAAAfmdAADQUxMfnIAEXJlcG9ydC5SZXBvcnRUeXBlAAAAAAAAAAASAAB4cgAOamF2YS5sYW5nLkVudW0AAAAAAAAAABIAAHhwdAAYUVVBUlRFUkxZX1JFVkVOVUVfUkVQT1JUQGYZmZmZmZpAvxQAAAAAAHVyAAJbRD6mjBSrY1oeAgAAeHAAAAADQHPquFHrhR8AAAAAAAAAAECiOqj1wo9cc3IAE2phdmEudXRpbC5BcnJheUxpc3R4gdIdmcdhnQMAAUkABHNpemV4cAAAAAJ3BAAAAAJ0AAB0AA9hIGhhcHB5IHdlZGRpbmd4dXIAA1tbRMetC/9kZ/9FAgAAeHAAAAADdXEAfgANAAAAH0B/QAAAAAAAAAAAAAAAAABAWQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAdXEAfgANAAAAHwAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAdXEAfgANAAAAHwAAAAAAAAAAQIcgAAAAAABAcOAAAAAAAEB5oAAAAAAAQJq8AAAAAABAm5gAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA'),('ALL','QUARTERLY_SATISFACTION_REPORT',2022,2,_binary 'rO0ABXNyACJyZXBvcnQuUXVhcnRlcmx5U2F0aXNmYWN0aW9uUmVwb3J0AAAAAAAAAAECAAFbABJjb21wbGFpbnRzUGVyTW9udGh0AAJbSXhyAA1yZXBvcnQuUmVwb3J0AAAAAAAAAAICAARJAAVtb250aEkABHllYXJMAApCcmFuY2hOYW1ldAASTGphdmEvbGFuZy9TdHJpbmc7TAAEdHlwZXQAE0xyZXBvcnQvUmVwb3J0VHlwZTt4cAAAAAIAAAfmdAADQUxMfnIAEXJlcG9ydC5SZXBvcnRUeXBlAAAAAAAAAAASAAB4cgAOamF2YS5sYW5nLkVudW0AAAAAAAAAABIAAHhwdAAdUVVBUlRFUkxZX1NBVElTRkFDVElPTl9SRVBPUlR1cgACW0lNumAmduqypQIAAHhwAAAAAwAAAAMAAAAIAAAAAw=='),('Ariel','MONTHLY_ORDERS_REPORT',2022,5,_binary 'rO0ABXNyABNyZXBvcnQuT3JkZXJzUmVwb3J0AAAAAAAAAAECAAVEABRhdmFyYWdlTW9udGhseU9yZGVyc0kAC3RvdGFsT3JkZXJzTAAPbW9zdFBvcHVsYXJJdGVtdAASTGphdmEvbGFuZy9TdHJpbmc7TAARb3JkZXJzUGVyQ2F0ZWdvcnl0ABNMamF2YS91dGlsL0hhc2hNYXA7WwAMb3JkZXJzUGVyRGF5dAACW0l4cgANcmVwb3J0LlJlcG9ydAAAAAAAAAACAgAESQAFbW9udGhJAAR5ZWFyTAAKQnJhbmNoTmFtZXEAfgABTAAEdHlwZXQAE0xyZXBvcnQvUmVwb3J0VHlwZTt4cAAAAAUAAAfmdAAFQXJpZWx+cgARcmVwb3J0LlJlcG9ydFR5cGUAAAAAAAAAABIAAHhyAA5qYXZhLmxhbmcuRW51bQAAAAAAAAAAEgAAeHB0ABVNT05USExZX09SREVSU19SRVBPUlRAEq4UeuFHrgAAAAB0AABzcgARamF2YS51dGlsLkhhc2hNYXAFB9rBwxZg0QMAAkYACmxvYWRGYWN0b3JJAAl0aHJlc2hvbGR4cD9AAAAAAAAAdwgAAAAQAAAAAHh1cgACW0lNumAmduqypQIAAHhwAAAAHwAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA='),('Ariel','MONTHLY_ORDERS_REPORT',2022,6,_binary 'rO0ABXNyABNyZXBvcnQuT3JkZXJzUmVwb3J0AAAAAAAAAAECAAVEABRhdmFyYWdlTW9udGhseU9yZGVyc0kAC3RvdGFsT3JkZXJzTAAPbW9zdFBvcHVsYXJJdGVtdAASTGphdmEvbGFuZy9TdHJpbmc7TAARb3JkZXJzUGVyQ2F0ZWdvcnl0ABNMamF2YS91dGlsL0hhc2hNYXA7WwAMb3JkZXJzUGVyRGF5dAACW0l4cgANcmVwb3J0LlJlcG9ydAAAAAAAAAACAgAESQAFbW9udGhJAAR5ZWFyTAAKQnJhbmNoTmFtZXEAfgABTAAEdHlwZXQAE0xyZXBvcnQvUmVwb3J0VHlwZTt4cAAAAAYAAAfmdAAFQXJpZWx+cgARcmVwb3J0LlJlcG9ydFR5cGUAAAAAAAAAABIAAHhyAA5qYXZhLmxhbmcuRW51bQAAAAAAAAAAEgAAeHB0ABVNT05USExZX09SREVSU19SRVBPUlRAAqPXCj1wpAAAAAB0AABzcgARamF2YS51dGlsLkhhc2hNYXAFB9rBwxZg0QMAAkYACmxvYWRGYWN0b3JJAAl0aHJlc2hvbGR4cD9AAAAAAAAAdwgAAAAQAAAAAHh1cgACW0lNumAmduqypQIAAHhwAAAAHwAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA='),('Ariel','MONTHLY_REVENU_EREPORT',2022,5,_binary 'rO0ABXNyABRyZXBvcnQuUmV2ZW51ZVJlcG9ydAAAAAAAAAABAgAFRAAVYXZlcmFnZU1vbnRobHlSZXZlbnVlRAAWYXZlcmFnZVJldmVudWVQZXJPcmRlckQADHRvdGFsUmV2ZW51ZUwAEm1vc3RQcm9maXRhYmxlSXRlbXQAEkxqYXZhL2xhbmcvU3RyaW5nO1sADXJldmVudWVQZXJEYXl0AAJbRHhyAA1yZXBvcnQuUmVwb3J0AAAAAAAAAAICAARJAAVtb250aEkABHllYXJMAApCcmFuY2hOYW1lcQB+AAFMAAR0eXBldAATTHJlcG9ydC9SZXBvcnRUeXBlO3hwAAAABQAAB+Z0AAVBcmllbH5yABFyZXBvcnQuUmVwb3J0VHlwZQAAAAAAAAAAEgAAeHIADmphdmEubGFuZy5FbnVtAAAAAAAAAAASAAB4cHQAFk1PTlRITFlfUkVWRU5VX0VSRVBPUlRAojqo9cKPXAAAAAAAAAAAAAAAAAAAAAB0AAB1cgACW0Q+powUq2NaHgIAAHhwAAAAHwAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA'),('Ariel','MONTHLY_REVENU_EREPORT',2022,6,_binary 'rO0ABXNyABRyZXBvcnQuUmV2ZW51ZVJlcG9ydAAAAAAAAAABAgAFRAAVYXZlcmFnZU1vbnRobHlSZXZlbnVlRAAWYXZlcmFnZVJldmVudWVQZXJPcmRlckQADHRvdGFsUmV2ZW51ZUwAEm1vc3RQcm9maXRhYmxlSXRlbXQAEkxqYXZhL2xhbmcvU3RyaW5nO1sADXJldmVudWVQZXJEYXl0AAJbRHhyAA1yZXBvcnQuUmVwb3J0AAAAAAAAAAICAARJAAVtb250aEkABHllYXJMAApCcmFuY2hOYW1lcQB+AAFMAAR0eXBldAATTHJlcG9ydC9SZXBvcnRUeXBlO3hwAAAABgAAB+Z0AAVBcmllbH5yABFyZXBvcnQuUmVwb3J0VHlwZQAAAAAAAAAAEgAAeHIADmphdmEubGFuZy5FbnVtAAAAAAAAAAASAAB4cHQAFk1PTlRITFlfUkVWRU5VX0VSRVBPUlRAc+q4UeuFHwAAAAAAAAAAAAAAAAAAAAB0AAB1cgACW0Q+powUq2NaHgIAAHhwAAAAHwAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA'),('Braude','MONTHLY_ORDERS_REPORT',2022,5,_binary 'rO0ABXNyABNyZXBvcnQuT3JkZXJzUmVwb3J0AAAAAAAAAAECAAVEABRhdmFyYWdlTW9udGhseU9yZGVyc0kAC3RvdGFsT3JkZXJzTAAPbW9zdFBvcHVsYXJJdGVtdAASTGphdmEvbGFuZy9TdHJpbmc7TAARb3JkZXJzUGVyQ2F0ZWdvcnl0ABNMamF2YS91dGlsL0hhc2hNYXA7WwAMb3JkZXJzUGVyRGF5dAACW0l4cgANcmVwb3J0LlJlcG9ydAAAAAAAAAACAgAESQAFbW9udGhJAAR5ZWFyTAAKQnJhbmNoTmFtZXEAfgABTAAEdHlwZXQAE0xyZXBvcnQvUmVwb3J0VHlwZTt4cAAAAAUAAAfmdAAGQnJhdWRlfnIAEXJlcG9ydC5SZXBvcnRUeXBlAAAAAAAAAAASAAB4cgAOamF2YS5sYW5nLkVudW0AAAAAAAAAABIAAHhwdAAVTU9OVEhMWV9PUkRFUlNfUkVQT1JUQBKuFHrhR64AAAAidAANbmV3IGJhYnkgZ2lybHNyABFqYXZhLnV0aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAAAx3CAAAABAAAAAFdAALYmFieUZsb3dlcnNzcgARamF2YS5sYW5nLkludGVnZXIS4qCk94GHOAIAAUkABXZhbHVleHIAEGphdmEubGFuZy5OdW1iZXKGrJUdC5TgiwIAAHhwAAAADnQADndlZGRpbmdGbG93ZXJzcQB+ABJ0AA9iaXJ0aGRheUZsb3dlcnNzcQB+ABAAAAAIdAAVY29uZ3JhdHVsYXRpb25GbG93ZXJzc3EAfgAQAAAABXQAEkFubml2ZXJzYXJ5Rmxvd2Vyc3NxAH4AEAAAAAN4dXIAAltJTbpgJnbqsqUCAAB4cAAAAB8AAAAAAAAABQAAAAIAAAACAAAABQAAAAcAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA'),('Braude','MONTHLY_ORDERS_REPORT',2022,6,_binary 'rO0ABXNyABNyZXBvcnQuT3JkZXJzUmVwb3J0AAAAAAAAAAECAAVEABRhdmFyYWdlTW9udGhseU9yZGVyc0kAC3RvdGFsT3JkZXJzTAAPbW9zdFBvcHVsYXJJdGVtdAASTGphdmEvbGFuZy9TdHJpbmc7TAARb3JkZXJzUGVyQ2F0ZWdvcnl0ABNMamF2YS91dGlsL0hhc2hNYXA7WwAMb3JkZXJzUGVyRGF5dAACW0l4cgANcmVwb3J0LlJlcG9ydAAAAAAAAAACAgAESQAFbW9udGhJAAR5ZWFyTAAKQnJhbmNoTmFtZXEAfgABTAAEdHlwZXQAE0xyZXBvcnQvUmVwb3J0VHlwZTt4cAAAAAYAAAfmdAAGQnJhdWRlfnIAEXJlcG9ydC5SZXBvcnRUeXBlAAAAAAAAAAASAAB4cgAOamF2YS5sYW5nLkVudW0AAAAAAAAAABIAAHhwdAAVTU9OVEhMWV9PUkRFUlNfUkVQT1JUQAKj1wo9cKQAAAALdAAPUmFpbmJvdyBib3VxdWV0c3IAEWphdmEudXRpbC5IYXNoTWFwBQfawcMWYNEDAAJGAApsb2FkRmFjdG9ySQAJdGhyZXNob2xkeHA/QAAAAAAADHcIAAAAEAAAAAV0AAtiYWJ5Rmxvd2Vyc3NyABFqYXZhLmxhbmcuSW50ZWdlchLioKT3gYc4AgABSQAFdmFsdWV4cgAQamF2YS5sYW5nLk51bWJlcoaslR0LlOCLAgAAeHAAAAABdAAOd2VkZGluZ0Zsb3dlcnNzcQB+ABAAAAACdAAPYmlydGhkYXlGbG93ZXJzcQB+ABR0ABVjb25ncmF0dWxhdGlvbkZsb3dlcnNxAH4AEnQAEkFubml2ZXJzYXJ5Rmxvd2Vyc3NxAH4AEAAAAAV4dXIAAltJTbpgJnbqsqUCAAB4cAAAAB8AAAAFAAAAAAAAAAEAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA'),('Braude','MONTHLY_REVENU_EREPORT',2022,5,_binary 'rO0ABXNyABRyZXBvcnQuUmV2ZW51ZVJlcG9ydAAAAAAAAAABAgAFRAAVYXZlcmFnZU1vbnRobHlSZXZlbnVlRAAWYXZlcmFnZVJldmVudWVQZXJPcmRlckQADHRvdGFsUmV2ZW51ZUwAEm1vc3RQcm9maXRhYmxlSXRlbXQAEkxqYXZhL2xhbmcvU3RyaW5nO1sADXJldmVudWVQZXJEYXl0AAJbRHhyAA1yZXBvcnQuUmVwb3J0AAAAAAAAAAICAARJAAVtb250aEkABHllYXJMAApCcmFuY2hOYW1lcQB+AAFMAAR0eXBldAATTHJlcG9ydC9SZXBvcnRUeXBlO3hwAAAABQAAB+Z0AAZCcmF1ZGV+cgARcmVwb3J0LlJlcG9ydFR5cGUAAAAAAAAAABIAAHhyAA5qYXZhLmxhbmcuRW51bQAAAAAAAAAAEgAAeHB0ABZNT05USExZX1JFVkVOVV9FUkVQT1JUQKI6qPXCj1xAabwo9cKPXEC7WAAAAAAAdAAPYSBoYXBweSB3ZWRkaW5ndXIAAltEPqaMFKtjWh4CAAB4cAAAAB8AAAAAAAAAAECHIAAAAAAAQHDgAAAAAABAeaAAAAAAAECavAAAAAAAQJuYAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA=='),('Braude','MONTHLY_REVENU_EREPORT',2022,6,_binary 'rO0ABXNyABRyZXBvcnQuUmV2ZW51ZVJlcG9ydAAAAAAAAAABAgAFRAAVYXZlcmFnZU1vbnRobHlSZXZlbnVlRAAWYXZlcmFnZVJldmVudWVQZXJPcmRlckQADHRvdGFsUmV2ZW51ZUwAEm1vc3RQcm9maXRhYmxlSXRlbXQAEkxqYXZhL2xhbmcvU3RyaW5nO1sADXJldmVudWVQZXJEYXl0AAJbRHhyAA1yZXBvcnQuUmVwb3J0AAAAAAAAAAICAARJAAVtb250aEkABHllYXJMAApCcmFuY2hOYW1lcQB+AAFMAAR0eXBldAATTHJlcG9ydC9SZXBvcnRUeXBlO3hwAAAABgAAB+Z0AAZCcmF1ZGV+cgARcmVwb3J0LlJlcG9ydFR5cGUAAAAAAAAAABIAAHhyAA5qYXZhLmxhbmcuRW51bQAAAAAAAAAAEgAAeHB0ABZNT05USExZX1JFVkVOVV9FUkVQT1JUQHPquFHrhR9AVbo9cKPXCkCN4AAAAAAAdAAPYSBoYXBweSB3ZWRkaW5ndXIAAltEPqaMFKtjWh4CAAB4cAAAAB9Af0AAAAAAAAAAAAAAAAAAQFkAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA=='),('Haifa','MONTHLY_ORDERS_REPORT',2022,5,_binary 'rO0ABXNyABNyZXBvcnQuT3JkZXJzUmVwb3J0AAAAAAAAAAECAAVEABRhdmFyYWdlTW9udGhseU9yZGVyc0kAC3RvdGFsT3JkZXJzTAAPbW9zdFBvcHVsYXJJdGVtdAASTGphdmEvbGFuZy9TdHJpbmc7TAARb3JkZXJzUGVyQ2F0ZWdvcnl0ABNMamF2YS91dGlsL0hhc2hNYXA7WwAMb3JkZXJzUGVyRGF5dAACW0l4cgANcmVwb3J0LlJlcG9ydAAAAAAAAAACAgAESQAFbW9udGhJAAR5ZWFyTAAKQnJhbmNoTmFtZXEAfgABTAAEdHlwZXQAE0xyZXBvcnQvUmVwb3J0VHlwZTt4cAAAAAUAAAfmdAAFSGFpZmF+cgARcmVwb3J0LlJlcG9ydFR5cGUAAAAAAAAAABIAAHhyAA5qYXZhLmxhbmcuRW51bQAAAAAAAAAAEgAAeHB0ABVNT05USExZX09SREVSU19SRVBPUlRAEq4UeuFHrgAAAAB0AABzcgARamF2YS51dGlsLkhhc2hNYXAFB9rBwxZg0QMAAkYACmxvYWRGYWN0b3JJAAl0aHJlc2hvbGR4cD9AAAAAAAAAdwgAAAAQAAAAAHh1cgACW0lNumAmduqypQIAAHhwAAAAHwAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA='),('Haifa','MONTHLY_ORDERS_REPORT',2022,6,_binary 'rO0ABXNyABNyZXBvcnQuT3JkZXJzUmVwb3J0AAAAAAAAAAECAAVEABRhdmFyYWdlTW9udGhseU9yZGVyc0kAC3RvdGFsT3JkZXJzTAAPbW9zdFBvcHVsYXJJdGVtdAASTGphdmEvbGFuZy9TdHJpbmc7TAARb3JkZXJzUGVyQ2F0ZWdvcnl0ABNMamF2YS91dGlsL0hhc2hNYXA7WwAMb3JkZXJzUGVyRGF5dAACW0l4cgANcmVwb3J0LlJlcG9ydAAAAAAAAAACAgAESQAFbW9udGhJAAR5ZWFyTAAKQnJhbmNoTmFtZXEAfgABTAAEdHlwZXQAE0xyZXBvcnQvUmVwb3J0VHlwZTt4cAAAAAYAAAfmdAAFSGFpZmF+cgARcmVwb3J0LlJlcG9ydFR5cGUAAAAAAAAAABIAAHhyAA5qYXZhLmxhbmcuRW51bQAAAAAAAAAAEgAAeHB0ABVNT05USExZX09SREVSU19SRVBPUlRAAqPXCj1wpAAAAAB0AABzcgARamF2YS51dGlsLkhhc2hNYXAFB9rBwxZg0QMAAkYACmxvYWRGYWN0b3JJAAl0aHJlc2hvbGR4cD9AAAAAAAAAdwgAAAAQAAAAAHh1cgACW0lNumAmduqypQIAAHhwAAAAHwAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA='),('Haifa','MONTHLY_REVENU_EREPORT',2022,5,_binary 'rO0ABXNyABRyZXBvcnQuUmV2ZW51ZVJlcG9ydAAAAAAAAAABAgAFRAAVYXZlcmFnZU1vbnRobHlSZXZlbnVlRAAWYXZlcmFnZVJldmVudWVQZXJPcmRlckQADHRvdGFsUmV2ZW51ZUwAEm1vc3RQcm9maXRhYmxlSXRlbXQAEkxqYXZhL2xhbmcvU3RyaW5nO1sADXJldmVudWVQZXJEYXl0AAJbRHhyAA1yZXBvcnQuUmVwb3J0AAAAAAAAAAICAARJAAVtb250aEkABHllYXJMAApCcmFuY2hOYW1lcQB+AAFMAAR0eXBldAATTHJlcG9ydC9SZXBvcnRUeXBlO3hwAAAABQAAB+Z0AAVIYWlmYX5yABFyZXBvcnQuUmVwb3J0VHlwZQAAAAAAAAAAEgAAeHIADmphdmEubGFuZy5FbnVtAAAAAAAAAAASAAB4cHQAFk1PTlRITFlfUkVWRU5VX0VSRVBPUlRAojqo9cKPXAAAAAAAAAAAAAAAAAAAAAB0AAB1cgACW0Q+powUq2NaHgIAAHhwAAAAHwAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA'),('Haifa','MONTHLY_REVENU_EREPORT',2022,6,_binary 'rO0ABXNyABRyZXBvcnQuUmV2ZW51ZVJlcG9ydAAAAAAAAAABAgAFRAAVYXZlcmFnZU1vbnRobHlSZXZlbnVlRAAWYXZlcmFnZVJldmVudWVQZXJPcmRlckQADHRvdGFsUmV2ZW51ZUwAEm1vc3RQcm9maXRhYmxlSXRlbXQAEkxqYXZhL2xhbmcvU3RyaW5nO1sADXJldmVudWVQZXJEYXl0AAJbRHhyAA1yZXBvcnQuUmVwb3J0AAAAAAAAAAICAARJAAVtb250aEkABHllYXJMAApCcmFuY2hOYW1lcQB+AAFMAAR0eXBldAATTHJlcG9ydC9SZXBvcnRUeXBlO3hwAAAABgAAB+Z0AAVIYWlmYX5yABFyZXBvcnQuUmVwb3J0VHlwZQAAAAAAAAAAEgAAeHIADmphdmEubGFuZy5FbnVtAAAAAAAAAAASAAB4cHQAFk1PTlRITFlfUkVWRU5VX0VSRVBPUlRAc+q4UeuFHwAAAAAAAAAAAAAAAAAAAAB0AAB1cgACW0Q+powUq2NaHgIAAHhwAAAAHwAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA');
/*!40000 ALTER TABLE `report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shopcredit`
--

DROP TABLE IF EXISTS `shopcredit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shopcredit` (
  `customerID` varchar(15) NOT NULL,
  `credit` double DEFAULT '0',
  PRIMARY KEY (`customerID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shopcredit`
--

LOCK TABLES `shopcredit` WRITE;
/*!40000 ALTER TABLE `shopcredit` DISABLE KEYS */;
/*!40000 ALTER TABLE `shopcredit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `survey`
--

DROP TABLE IF EXISTS `survey`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `survey` (
  `surveyNumber` int NOT NULL AUTO_INCREMENT,
  `q1` varchar(120) DEFAULT NULL,
  `q2` varchar(120) DEFAULT NULL,
  `q3` varchar(120) DEFAULT NULL,
  `q4` varchar(120) DEFAULT NULL,
  `q5` varchar(120) DEFAULT NULL,
  `q6` varchar(120) DEFAULT NULL,
  `a1` int DEFAULT '0',
  `a2` int DEFAULT '0',
  `a3` int DEFAULT '0',
  `a4` int DEFAULT '0',
  `a5` int DEFAULT '0',
  `a6` int DEFAULT '0',
  `participants` int DEFAULT '0',
  `surveyResult` mediumblob,
  PRIMARY KEY (`surveyNumber`),
  UNIQUE KEY `surveyNumber_UNIQUE` (`surveyNumber`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `survey`
--

LOCK TABLES `survey` WRITE;
/*!40000 ALTER TABLE `survey` DISABLE KEYS */;
INSERT INTO `survey` VALUES (1,'1','2','3','4','5','6',0,0,0,0,0,0,0,NULL);
/*!40000 ALTER TABLE `survey` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `username` varchar(45) NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  `userType` varchar(45) DEFAULT NULL,
  `connected` tinyint DEFAULT NULL,
  `firstName` varchar(45) DEFAULT NULL,
  `lastName` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `phoneNumber` varchar(45) DEFAULT NULL,
  `personID` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `branch` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `UserName_UNIQUE` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('HallelWeil','7891011','BranchManager',0,'hallel','weil','hallel@gmail.com','054123456','200000000','Active','Braude'),('OfirGalai','223344','BranchEmployee',0,'ofir','galai','ofir@gmail.com','051444888','300000000','Active','Braude'),('Omersh','1234567','CEO',0,' Omer','shamir','ommersh@gmail.comCEO','0545970626','314763970','Active','Braude'),('Omershami','667788','Courier',0,'omerr','shami','om@gmail.com','056670000','600000000','Active','Braude'),('OrBalmas','123456','AuthorizedCustomer',0,'or','balmas','or@gmail.com','0569999917','100000000','Active',' '),('RonenZeyan','778899','CustomerServiceEmloyee',0,'ronen','zeyan','ronen@gmail.com','0505813931','4000000000','Active','Braude'),('WorkHallel','112233','MarketingEmployee',0,'hallelll','wiel','hallel2@gmail.com','0508889976','500000000','Active','Braude');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-06 15:53:41
