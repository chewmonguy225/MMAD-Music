-- MySQL dump 10.13  Distrib 8.0.40, for macos14 (arm64)
--
-- Host: localhost    Database: music_share_db
-- ------------------------------------------------------
-- Server version	9.0.1

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
-- Table structure for table `album`
--

DROP TABLE IF EXISTS `album`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `album` (
  `id` int NOT NULL AUTO_INCREMENT,
  `source_id` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `artist_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `source_id_UNIQUE` (`source_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `album`
--

LOCK TABLES `album` WRITE;
/*!40000 ALTER TABLE `album` DISABLE KEYS */;
INSERT INTO `album` VALUES (1,'30fIbIvd1AN04FrBt12KhM','Older',1),(2,'68L5xVV9wydotfDXEik7eD','five seconds flat',1),(3,'0jw9gOg8M1Ve6KpP4Iu7XF','Older (and Wiser)',1),(5,'45evuVVrY9LzPez8geNEIF','Older',1),(9,'63ButSDtUefzCmdE3AXdor','Give Me A Minute',1),(10,'7JVJlkNNobS0GSoy4tCS96','The Bodyguard - Original Soundtrack Album',3),(11,'13r6eqjYlKELFQlNvVCBz1','Djesse Vol. 4',5),(12,'1pwXpbHvWEMH4nCAlhlwf7','Time Alone With You (feat. Daniel Caesar)',5),(13,'7qsJCY5jPI72VLTkvK2Fq5','how many',6),(14,'171wlUzsjGs3s2ux9JSdst','Fuzzy Feeling',7),(15,'7qUpf1A0w5hh4Do5SLRYjP','Loser',8),(16,'3bKudQjY5CKr4OQaRCkJpB','tiny things',9),(17,'5fjNNSyiwXoOQMCW4dLjJ2','Emotional',10);
/*!40000 ALTER TABLE `album` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `artist`
--

DROP TABLE IF EXISTS `artist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `artist` (
  `id` int NOT NULL AUTO_INCREMENT,
  `source_id` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `source_id_UNIQUE` (`source_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `artist`
--

LOCK TABLES `artist` WRITE;
/*!40000 ALTER TABLE `artist` DISABLE KEYS */;
INSERT INTO `artist` VALUES (1,'1GmsPCcpKgF9OhlNXjOsbS','Lizzy McAlpine'),(2,'19ra5tSw0tWufvUp8GotLo','George Michael'),(3,'6XpaIBNiVzIetEPCWDvAFP','Whitney Houston'),(4,'74KM79TiuVKeVCqs8QtB0B','Sabrina Carpenter'),(5,'0QWrMNukfcVOmgEU0FEDyD','Jacob Collier'),(6,'7Lfq2a2cpwQBdDzo7SW1HC','jake minch'),(7,'73BLwSX6gsNeVzS7DgI4xe','grentperez'),(8,'4xnihxcoXWK3UqryOSnbw5','Sasha Alex Sloan'),(9,'2QYdqWGgRorVkA8cJMMdrn','Tiny Habits'),(10,'6I6t21SFbxZ7RbQgD5dN7U','Carl Thomas');
/*!40000 ALTER TABLE `artist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `playlist_song`
--

DROP TABLE IF EXISTS `playlist_song`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `playlist_song` (
  `username` varchar(15) NOT NULL,
  `song_id` int NOT NULL,
  PRIMARY KEY (`username`,`song_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playlist_song`
--

LOCK TABLES `playlist_song` WRITE;
/*!40000 ALTER TABLE `playlist_song` DISABLE KEYS */;
INSERT INTO `playlist_song` VALUES ('mickael',8),('mickael',9);
/*!40000 ALTER TABLE `playlist_song` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review` (
  `id` varchar(45) NOT NULL,
  `text` varchar(255) NOT NULL,
  `rating` int NOT NULL,
  `username` varchar(45) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES ('al5','Got me through dammit',5,'m','2024-12-08 08:29:14'),('ar1','sad girl',5,'m','2024-12-08 08:29:14'),('ar4','wow',3,'mikl','2024-12-08 08:30:15'),('ar5','a mind fuck',5,'mickael','2024-12-08 08:38:27'),('s10','makes you miss childhood',5,'mickael','2024-12-08 08:38:56'),('s11','romantic',5,'m','2024-12-08 08:49:05');
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `song`
--

DROP TABLE IF EXISTS `song`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `song` (
  `id` int NOT NULL AUTO_INCREMENT,
  `source_id` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `artist_id` int NOT NULL,
  `album_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `source_id_UNIQUE` (`source_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `song`
--

LOCK TABLES `song` WRITE;
/*!40000 ALTER TABLE `song` DISABLE KEYS */;
INSERT INTO `song` VALUES (1,'059gVW493dUeBHkn0gE1zm','Older',1,3),(7,'31er9IGsfFbwqy1pH4aiTP','I Have Nothing',3,10),(8,'6XJvYWE3tx9tRVavh6GysW','Cinnamon Crush (feat. Lindsey Lomis)',5,11),(9,'04jVEb76Kw5OrmGtlXlHxI','Time Alone With You (feat. Daniel Caesar)',5,12),(10,'6aoUp4OToOino1MafPgILM','bike ride',6,13),(11,'6UX55FQuFq4q8jtIijZDRv','Fuzzy Feeling',7,14),(13,'3kywzyEr7V106Un5cDyh12','Older',8,15),(14,'68jZByx2c1UWtRyIX7gpN7','tiny things',9,16),(15,'0NBHHa8wwwmBnn3aAzX5wJ','Summer Rain',10,17);
/*!40000 ALTER TABLE `song` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `username` varchar(15) NOT NULL,
  `password` varchar(30) NOT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `UserName_UNIQUE` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('m','a'),('mickael','1'),('mikl','111');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_friend`
--

DROP TABLE IF EXISTS `user_friend`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_friend` (
  `username` varchar(15) NOT NULL,
  `friend_username` varchar(15) NOT NULL,
  PRIMARY KEY (`username`,`friend_username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_friend`
--

LOCK TABLES `user_friend` WRITE;
/*!40000 ALTER TABLE `user_friend` DISABLE KEYS */;
INSERT INTO `user_friend` VALUES ('mickael','m');
/*!40000 ALTER TABLE `user_friend` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-08  5:07:23
