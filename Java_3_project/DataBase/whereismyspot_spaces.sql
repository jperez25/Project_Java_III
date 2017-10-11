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
  PRIMARY KEY (`lot_id`,`day`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spaces`
--

LOCK TABLES `spaces` WRITE;
/*!40000 ALTER TABLE `spaces` DISABLE KEYS */;
INSERT INTO `spaces` VALUES ('Dunham','Monday',1,2,3,4,5,6,7,8,9,10),('Eckhart','Monday',1,2,3,4,5,6,7,8,9,10),('Institute','Monday',1,2,3,4,5,6,7,8,9,10),('Parolini','Monday',1,2,3,4,5,6,7,8,9,10),('Southlawn','Monday',1,2,3,4,5,6,7,8,9,10),('Stem','Monday',1,2,3,4,5,6,7,8,9,10),('UBH','Monday',1,2,3,4,5,6,7,8,9,10),('Vago North','Monday',1,2,3,4,5,6,7,8,9,10),('Vago South','Monday',1,2,3,4,5,6,7,8,9,10);
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

-- Dump completed on 2017-10-10 21:41:38
