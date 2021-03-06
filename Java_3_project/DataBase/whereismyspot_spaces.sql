-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: whereismyspot
-- ------------------------------------------------------
-- Server version	5.7.19-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `spaces`
--

DROP TABLE IF EXISTS `spaces`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `spaces` (
  `lot_id` varchar(45) NOT NULL,
  `day` varchar(9) NOT NULL,
  `hours_of_service` char(20) DEFAULT NULL,
  `spots_at_7` int(11) NOT NULL,
  `spots_at_8` int(11) NOT NULL,
  `spots_at_9` int(11) NOT NULL,
  `spots_at_10` int(11) NOT NULL,
  `spots_at_11` int(11) NOT NULL,
  `spots_at_12` int(11) NOT NULL,
  `spots_at_1` int(11) NOT NULL,
  `spots_at_2` int(11) NOT NULL,
  `spots_at_3` int(11) NOT NULL,
  `spots_at_4` int(11) NOT NULL,
  `images` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`lot_id`,`day`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spaces`
--

LOCK TABLES `spaces` WRITE;
/*!40000 ALTER TABLE `spaces` DISABLE KEYS */;
INSERT INTO `spaces` VALUES ('Dunham','Friday','7am - 11pm',23,3,4,4,4,4,4,4,4,4,'/images/Dunham.png'),('Dunham','Monday','',11,2,3,4,5,6,7,8,9,10,NULL),('Dunham','Thursday','',4,3,4,4,4,4,4,4,4,4,NULL),('Dunham','Tuesday','',7,3,4,4,4,4,4,4,4,4,NULL),('Dunham','Wednesday','',10,3,4,4,4,4,4,4,4,4,NULL),('Eckhart','Frriday','7am - 11pm',20,2,3,4,5,6,7,8,9,10,'/images/Eckhart.png'),('Eckhart','Monday',NULL,10,2,3,4,5,6,7,8,9,10,NULL),('Eckhart','Thursday',NULL,9,2,3,4,3,6,7,8,9,10,NULL),('Eckhart','Tuesday',NULL,1,2,3,4,6,6,7,8,9,10,NULL),('Eckhart','Wednesday',NULL,1,2,3,4,2,6,7,8,9,10,NULL),('Institute','Friday','7am - 11pm',1,2,3,4,7,6,7,8,9,10,'/images/Institute.png'),('Institute','Monday',NULL,1,2,3,4,2,6,7,8,9,10,NULL),('Institute','Thursday',NULL,1,2,3,4,7,6,7,8,9,10,NULL),('Institute','Tuesday',NULL,1,2,3,4,8,6,7,8,9,10,NULL),('Institute','Wednesday',NULL,1,2,3,4,5,6,7,8,9,10,NULL),('Parolini','Friday','7am - 11pm',1,2,3,4,5,6,7,8,9,10,'/images/Parolini.png'),('Parolini','Monday',NULL,1,2,3,4,5,6,7,8,9,10,NULL),('Parolini','Thursday',NULL,1,2,3,4,5,6,7,8,9,10,NULL),('Parolini','Tuesday',NULL,1,2,3,4,5,6,7,8,9,10,NULL),('Parolini','Wednesday',NULL,1,2,3,4,5,6,7,8,9,10,NULL),('Southlawn','Friday','7am - 11pm',1,2,6,4,93,6,7,8,9,10,'/images/Southlawn.png'),('Southlawn','Monday',NULL,1,2,4,4,93,6,7,8,9,10,NULL),('Southlawn','Thursday',NULL,1,2,3,4,93,6,7,8,9,10,NULL),('Southlawn','Tuesday',NULL,1,2,3,4,93,6,7,8,9,10,NULL),('Southlawn','Wednesday',NULL,1,2,3,4,93,6,7,8,9,10,NULL),('Stem','Friday','7am - 11pm',1,2,9,4,8,6,7,8,9,3,'/images/STEM.png'),('Stem','Monday',NULL,1,2,4,4,8,6,7,8,9,10,NULL),('Stem','Thursday',NULL,1,2,3,4,8,6,7,8,9,10,NULL),('Stem','Tuesday',NULL,1,2,2,4,8,6,7,8,9,10,NULL),('Stem','Wednesday',NULL,1,2,8,4,8,6,7,8,9,10,NULL),('UBH','Friday','7-11',1,2,8,4,2,6,7,8,9,10,'/images/UBH.png'),('UBH','Monday',NULL,1,2,2,4,2,6,7,8,9,10,NULL),('UBH','Thursday',NULL,1,2,9,4,2,6,7,8,9,10,NULL),('UBH','Tuesday',NULL,1,2,2,4,2,6,7,8,9,10,NULL),('UBH','Wednesday',NULL,1,2,1,4,2,6,7,8,9,10,NULL),('Vago North','Friday','7am - 11pm',1,2,8,4,8,6,7,8,9,10,'/images/Vago North.png'),('Vago North','Monday',NULL,1,2,3,4,8,6,7,8,9,10,NULL),('Vago North','Thursday',NULL,1,2,2,4,8,6,7,8,9,10,NULL),('Vago North','Tuesday',NULL,1,2,8,4,8,6,7,8,9,10,NULL),('Vago North','Wednesday',NULL,1,2,2,4,8,6,7,8,9,10,NULL),('Vago South','Friday','7am - 11pm',1,2,5,4,2,6,7,8,9,10,'/images/Vago South.png'),('Vago South','Monday',NULL,1,2,7,4,2,6,7,8,9,10,NULL),('Vago South','Thursday',NULL,1,2,4,4,2,6,7,8,9,10,NULL),('Vago South','Tuesday',NULL,1,2,2,4,2,6,7,8,9,10,NULL),('Vago South','Wednesday',NULL,1,2,6,4,2,6,7,8,9,10,NULL);
/*!40000 ALTER TABLE `spaces` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-10-26 23:35:39
