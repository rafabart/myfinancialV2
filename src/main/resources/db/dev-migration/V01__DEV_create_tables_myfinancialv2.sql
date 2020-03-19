USE `myfinacial`;
-- MySQL dump 10.13  Distrib 5.7.29, for Linux (x86_64)
--
-- Host: localhost    Database: myfinacial
-- ------------------------------------------------------
-- Server version	8.0.19

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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `update_at` datetime(6) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `name` varchar(80) NOT NULL,
  `password` varchar(60) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'2020-03-19 01:09:32.404927','2020-03-19 01:09:32.404987','rafamola@gmail.com','Rafael Marinho','$2a$10$Zpf37PTVLPGLsOCtbXzHbu6hJco9CP/ePHLQg720RGmWgjn8C2JxS'),(2,'2020-03-19 01:09:32.778448','2020-03-19 01:09:32.778523','corona@gmail.com','Corona Vírus','$2a$10$.dwV3Izqw5SHvoxkw70RD.lRkwLTZeMKSwXVKf0IttSVVcKN3c5Ya');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `id` bigint NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `update_at` datetime(6) DEFAULT NULL,
  `name` varchar(40) NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpfk8djhv5natgshmxiav6xkpu` (`user_id`),
  CONSTRAINT `FKpfk8djhv5natgshmxiav6xkpu` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (3,'2020-03-19 01:09:33.074943','2020-03-19 01:09:33.075006','Educação',1),(4,'2020-03-19 01:09:33.332206','2020-03-19 01:09:33.332312','Diversão',1),(5,'2020-03-19 01:09:33.662811','2020-03-19 01:09:33.662909','Educação',2),(6,'2020-03-19 01:09:33.921322','2020-03-19 01:09:34.086037','Aluguel',2);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `expense`
--

DROP TABLE IF EXISTS `expense`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `expense` (
  `id` bigint NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `update_at` datetime(6) DEFAULT NULL,
  `description` varchar(100) NOT NULL,
  `due_date` date DEFAULT NULL,
  `expense_type` int NOT NULL,
  `payment_date` date DEFAULT NULL,
  `value` double NOT NULL,
  `category_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmvjm59reb5i075vu38bwcaqj6` (`category_id`),
  KEY `FK758h5dgdblrpwoaaycbmn29i0` (`user_id`),
  CONSTRAINT `FK758h5dgdblrpwoaaycbmn29i0` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKmvjm59reb5i075vu38bwcaqj6` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `expense`
--

LOCK TABLES `expense` WRITE;
/*!40000 ALTER TABLE `expense` DISABLE KEYS */;
INSERT INTO `expense` VALUES (7,'2020-03-19 01:09:35.251643','2020-03-19 01:09:35.251696','Faculdade','2020-03-21',1,'2020-03-18',450,3,1),(8,'2020-03-19 01:09:35.256754','2020-03-19 01:09:35.256804','Curso de Inglês','2020-03-21',1,'2020-03-18',179.99,3,1),(9,'2020-03-19 01:09:35.259683','2020-03-19 01:09:35.259712','Toca do Lobo','2020-03-21',1,'2020-03-18',180,4,1),(10,'2020-03-19 01:09:35.262341','2020-03-19 01:09:35.262370','Salario',NULL,2,'2020-03-18',180,6,1),(11,'2020-03-19 01:09:35.265020','2020-03-19 01:09:35.265049','Faculdade','2020-03-21',1,'2020-03-18',320.45,5,2),(12,'2020-03-19 01:09:35.268053','2020-03-19 01:09:35.268087','Moradia','2020-03-21',1,'2020-03-18',550,6,2),(13,'2020-03-19 01:09:35.271120','2020-03-19 01:09:35.271161','Loja',NULL,2,'2020-03-18',600,6,2);
/*!40000 ALTER TABLE `expense` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_profile_list`
--

DROP TABLE IF EXISTS `user_profile_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_profile_list` (
  `user_id` bigint NOT NULL,
  `profile_list` int DEFAULT NULL,
  KEY `FK85kyk500yk105lc9uvv8r4pnd` (`user_id`),
  CONSTRAINT `FK85kyk500yk105lc9uvv8r4pnd` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_profile_list`
--

LOCK TABLES `user_profile_list` WRITE;
/*!40000 ALTER TABLE `user_profile_list` DISABLE KEYS */;
INSERT INTO `user_profile_list` VALUES (1,1),(1,2),(2,1);
/*!40000 ALTER TABLE `user_profile_list` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-03-18 22:23:04
