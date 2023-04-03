-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: mgnrega_project
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `gpm`
--

DROP TABLE IF EXISTS `gpm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gpm` (
  `gpmID` int NOT NULL AUTO_INCREMENT,
  `gpmName` varchar(20) NOT NULL,
  `gpmAadhar` varchar(12) NOT NULL,
  `gpmDob` date NOT NULL,
  `gpmGender` varchar(6) NOT NULL,
  `gpmEmail` varchar(20) NOT NULL,
  `gpmPassword` varchar(20) NOT NULL,
  `gpName` varchar(20) NOT NULL,
  `district` varchar(20) NOT NULL,
  `state` varchar(20) NOT NULL,
  `is_delete` tinyint(1) NOT NULL DEFAULT (false),
  PRIMARY KEY (`gpmID`),
  UNIQUE KEY `gpmAadhar` (`gpmAadhar`),
  UNIQUE KEY `gpmEmail` (`gpmEmail`),
  UNIQUE KEY `gpName` (`gpName`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gpm`
--

LOCK TABLES `gpm` WRITE;
/*!40000 ALTER TABLE `gpm` DISABLE KEYS */;
INSERT INTO `gpm` VALUES (1,'Ankit','111111111111','2001-10-15','male','ankit@gmail.com','Ankit@123','Attarshumma','Sultanpur','UP',0),(2,'Suraj','222222222222','2000-03-10','male','suraj@gmail.com','Suraj@123','Mayang','Sultanpur','UP',0),(3,'Aadi','333333333333','2000-06-25','male','aadi@gmail.com','Aadi@123','Bharshada','Sultanpur','UP',0),(4,'Sumi','444444444444','2000-09-05','male','sumi@gmail.com','Sumi@123','Pipergao','Sultanpur','UP',0),(5,'Raj','555555555555','2002-05-18','male','raj@gmail.com','Raj@123','Madhavpur','Sultanpur','UP',0),(6,'Rahul','994685236756','2000-10-01','male','rahul@gmail.com','Rahul@123','Haroha','Sultanpur','UP',0);
/*!40000 ALTER TABLE `gpm` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `project` (
  `proID` int NOT NULL AUTO_INCREMENT,
  `proName` varchar(20) NOT NULL,
  `proStrDate` date NOT NULL,
  `proEndDate` date NOT NULL,
  `gpmID` int DEFAULT NULL,
  `noOfWorkes` int DEFAULT NULL,
  `wagesParDay` int NOT NULL,
  `is_delete` tinyint(1) NOT NULL DEFAULT (false),
  PRIMARY KEY (`proID`),
  KEY `gpmID` (`gpmID`),
  CONSTRAINT `project_ibfk_1` FOREIGN KEY (`gpmID`) REFERENCES `gpm` (`gpmID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` VALUES (1,'Water Resources','2022-04-23','2024-04-01',4,4,400,0),(2,'Agricultural','2021-08-02','2025-06-01',1,4,350,0),(3,'Natural Resource','2023-02-11','2026-06-29',5,3,400,0),(4,'Healthcare','2022-09-06','2025-03-22',2,4,500,0),(5,'Education','2023-02-11','2025-08-02',6,0,200,0),(6,'xyz','2023-01-14','2024-11-01',NULL,NULL,100,0);
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `workers`
--

DROP TABLE IF EXISTS `workers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `workers` (
  `wID` int NOT NULL AUTO_INCREMENT,
  `wName` varchar(20) NOT NULL,
  `wAadhar` varchar(12) NOT NULL,
  `wDob` date NOT NULL,
  `wGender` varchar(6) NOT NULL,
  `gpName` varchar(20) NOT NULL,
  `gpmID` int NOT NULL,
  `proID` int DEFAULT NULL,
  `workStrDate` date DEFAULT NULL,
  `district` varchar(20) NOT NULL,
  `state` varchar(20) NOT NULL,
  `is_delete` tinyint(1) NOT NULL DEFAULT (false),
  PRIMARY KEY (`wID`),
  UNIQUE KEY `wAadhar` (`wAadhar`),
  KEY `proID` (`proID`),
  KEY `gpmID` (`gpmID`),
  CONSTRAINT `workers_ibfk_1` FOREIGN KEY (`proID`) REFERENCES `project` (`proID`),
  CONSTRAINT `workers_ibfk_2` FOREIGN KEY (`gpmID`) REFERENCES `gpm` (`gpmID`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `workers`
--

LOCK TABLES `workers` WRITE;
/*!40000 ALTER TABLE `workers` DISABLE KEYS */;
INSERT INTO `workers` VALUES (27,'Mohan','211111111111','1976-09-24','male','Mayang',2,4,'2023-02-22','Sultanpur','UP',0),(28,'Sohan','221111111111','1978-08-14','male','Mayang',2,4,'2023-02-22','Sultanpur','UP',0),(29,'Suresh','231111111111','1975-04-27','male','Mayang',2,4,'2023-02-24','Sultanpur','UP',0),(30,'Mukesh','241111111111','1986-09-16','male','Attarshumma',1,2,'2023-01-12','Sultanpur','UP',0),(31,'Mahesh','251111111111','1984-09-21','male','Attarshumma',1,2,'2023-01-14','Sultanpur','UP',0),(32,'Manoram','261111111111','1971-11-07','male','Attarshumma',1,2,'2023-02-18','Sultanpur','UP',0),(33,'Parash','271111111111','1971-06-09','male','Madhavpur',5,3,'2023-03-02','Sultanpur','UP',0),(34,'Bablu','281111111111','1988-05-29','male','Madhavpur',5,3,'2023-03-11','Sultanpur','UP',0),(35,'Bhabhuti','291111111111','1973-08-01','male','Madhavpur',5,3,'2023-03-15','Sultanpur','UP',0),(36,'Prakash','212111111111','1976-05-17','male','Pipargao',4,1,'2022-12-01','Sultanpur','UP',0),(37,'Ashok','213111111111','1968-02-24','male','Pipargao',4,1,'2023-02-12','Sultanpur','UP',0),(38,'Ghanti','214111111111','1981-10-12','male','Pipargao',4,1,'2023-02-26','Sultanpur','UP',0),(39,'Sharvan','215111111111','1986-09-14','male','Pipargao',4,1,'2023-03-07','Sultanpur','UP',0),(43,'Shyam','239887768978','1990-12-11','male','Mayang',2,4,'2023-03-30','Sultanpue','UP',0),(45,'Anmol','654534236543','1990-12-17','male','Attarshumma',1,2,'2023-03-30','Sultanpur','UP',0),(46,'Mohit','253748564546','1990-12-01','male','Haroha',6,6,'2023-04-01','Sultanpur','UP',0),(47,'asd','123456789012','2000-12-11','male','Haroha',6,6,'2023-04-01','Sultanpur','UP',0),(48,'Madhu','349876897678','1989-02-09','femal','Haroha',6,NULL,'2023-04-16','Sultanpur','UP',0),(49,'Rani','684398873498','1995-03-02','female','Madhavpur',5,3,'2023-04-03','Sultanpur','UP',0);
/*!40000 ALTER TABLE `workers` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-03 10:47:41
