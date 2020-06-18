-- MySQL dump 10.13  Distrib 8.0.0-dmr, for Win64 (x86_64)
--
-- Host: localhost    Database: bakery
-- ------------------------------------------------------
-- Server version	8.0.0-dmr-log

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
-- Table structure for table `clients`
--

DROP TABLE IF EXISTS clients;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE clients (
  client_id int(11) NOT NULL AUTO_INCREMENT,
  client_name varchar(45) NOT NULL,
  national_code varchar(45) DEFAULT NULL,
  mail_id varchar(45) DEFAULT NULL,
  phone varchar(45) DEFAULT NULL,
  fk_rank_id int(11) NOT NULL,
  PRIMARY KEY (client_id),
  UNIQUE KEY client_id_UNIQUE (client_id),
  UNIQUE KEY national_code_UNIQUE (national_code),
  UNIQUE KEY mail_id_UNIQUE (mail_id),
  UNIQUE KEY phone_UNIQUE (phone),
  KEY fk_rank_id_idx (fk_rank_id),
  CONSTRAINT fk_rank_id FOREIGN KEY (fk_rank_id) REFERENCES ranks (ranks_id)
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clients`
--

LOCK TABLES clients WRITE;
/*!40000 ALTER TABLE clients DISABLE KEYS */;
/*!40000 ALTER TABLE clients ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `limits`
--

DROP TABLE IF EXISTS limits;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE limits (
  limit_id int(11) NOT NULL AUTO_INCREMENT,
  limits int(11) DEFAULT NULL,
  rank_id int(11) NOT NULL,
  PRIMARY KEY (limit_id),
  KEY fk_rank_id_idx (rank_id),
  CONSTRAINT fk_bakery_rank FOREIGN KEY (rank_id) REFERENCES ranks (ranks_id)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `limits`
--

LOCK TABLES limits WRITE;
/*!40000 ALTER TABLE limits DISABLE KEYS */;
INSERT INTO limits VALUES (2,1,1),(3,1,2),(4,1,3),(5,1,4),(6,1,5),(7,1,6),(8,1,7),(9,1,8),(10,1,9),(11,1,10),(12,1,11),(13,1,12),(14,1,13),(15,1,14),(16,1,15),(17,1,16),(18,1,17),(19,1,18),(20,1,19),(21,1,20),(22,1,21),(23,1,22),(28,100,23);
/*!40000 ALTER TABLE limits ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prices`
--

DROP TABLE IF EXISTS prices;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE prices (
  price_id int(11) NOT NULL AUTO_INCREMENT,
  price decimal(10,2) DEFAULT '2.50',
  PRIMARY KEY (price_id)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prices`
--

LOCK TABLES prices WRITE;
/*!40000 ALTER TABLE prices DISABLE KEYS */;
INSERT INTO prices VALUES (1,2.50);
/*!40000 ALTER TABLE prices ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ranks`
--

DROP TABLE IF EXISTS ranks;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE ranks (
  ranks_id int(11) NOT NULL AUTO_INCREMENT,
  rank_name varchar(45) DEFAULT NULL,
  PRIMARY KEY (ranks_id)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ranks`
--

LOCK TABLES ranks WRITE;
/*!40000 ALTER TABLE ranks DISABLE KEYS */;
INSERT INTO ranks VALUES (1,'مشير'),(2,'فريق أول'),(3,'فريق'),(4,'لواء'),(5,'عميد'),(6,'عقيد'),(7,'مقدم'),(8,'رائد'),(9,'نقيب'),(10,'ملازم أول'),(11,'ملازم'),(12,'مساعد أول'),(13,'مساعد'),(14,'رقيب أول'),(15,'رقيب'),(16,'عريف'),(17,'طالب'),(18,'صانع متدرج'),(19,'وكيل عريف'),(20,'محارب'),(21,'جندى'),(22,'مدنى'),(23,'الكل');
/*!40000 ALTER TABLE ranks ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service`
--

DROP TABLE IF EXISTS service;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE service (
  service_id int(11) NOT NULL AUTO_INCREMENT,
  `time` date NOT NULL,
  nomof_service varchar(45) NOT NULL,
  total_price varchar(45) NOT NULL,
  fk_client_id int(11) NOT NULL,
  PRIMARY KEY (service_id),
  KEY fk_client_id_idx (fk_client_id),
  CONSTRAINT fk_client_id FOREIGN KEY (fk_client_id) REFERENCES `clients` (client_id)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service`
--

LOCK TABLES service WRITE;
/*!40000 ALTER TABLE service DISABLE KEYS */;
/*!40000 ALTER TABLE service ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS users;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE users (
  user_id int(11) NOT NULL AUTO_INCREMENT,
  user_name varchar(45) NOT NULL,
  user_pass varchar(45) NOT NULL,
  user_type varchar(45) NOT NULL,
  user_desc varchar(45) DEFAULT NULL,
  PRIMARY KEY (user_id),
  UNIQUE KEY user_name_UNIQUE (user_name)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES users WRITE;
/*!40000 ALTER TABLE users DISABLE KEYS */;
INSERT INTO users VALUES (18,'yehia','admin','admin','adminstrator'),(19,'سامح','55555','manager','manage system'),(20,'محمد','55555','manager','manage system');
/*!40000 ALTER TABLE users ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'bakery'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-03-07  7:57:05
