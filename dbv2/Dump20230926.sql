-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: pet_project
-- ------------------------------------------------------
-- Server version	8.0.22

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
-- Table structure for table `bill_material_detail`
--

DROP TABLE IF EXISTS `bill_material_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_material_detail` (
  `id` int NOT NULL AUTO_INCREMENT,
  `product_id` int DEFAULT NULL,
  `material_id` int DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_material_detail`
--

LOCK TABLES `bill_material_detail` WRITE;
/*!40000 ALTER TABLE `bill_material_detail` DISABLE KEYS */;
INSERT INTO `bill_material_detail` VALUES (14,4,3,1),(13,4,2,1),(12,4,1,5),(4,2,1,0),(5,2,2,1),(6,2,3,1);
/*!40000 ALTER TABLE `bill_material_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booking` (
  `idbooking` int NOT NULL AUTO_INCREMENT,
  `appointment_date` datetime DEFAULT NULL,
  `client_id` int DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `pet_name` varchar(255) DEFAULT NULL,
  `pet_type` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `service_id` int DEFAULT NULL,
  `service_worker_id` int DEFAULT NULL,
  `status` int DEFAULT NULL,
  PRIMARY KEY (`idbooking`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking`
--

LOCK TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Đồ gia dụng'),(2,'Đồ nội thất'),(4,'kkkk');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `material`
--

DROP TABLE IF EXISTS `material`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `material` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `quantity` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `material`
--

LOCK TABLES `material` WRITE;
/*!40000 ALTER TABLE `material` DISABLE KEYS */;
INSERT INTO `material` VALUES (1,'Khung gỗ',0),(2,'Khung sắt 2m',80),(3,'Ổ khóa',80);
/*!40000 ALTER TABLE `material` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_product`
--

DROP TABLE IF EXISTS `order_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `price` double DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `id_order` int NOT NULL,
  `id_product` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKg3uugag7e9dxyx74h2tftyp1c` (`id_order`),
  KEY `FKkm8jfm4t1ocixswclleb7xkxj` (`id_product`)
) ENGINE=MyISAM AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_product`
--

LOCK TABLES `order_product` WRITE;
/*!40000 ALTER TABLE `order_product` DISABLE KEYS */;
INSERT INTO `order_product` VALUES (5,22222222222,2,3,5),(6,11111111111,1,4,5),(7,211111111109,19,5,5),(8,600,2,6,7),(13,2400,8,9,7),(14,160,1,9,8),(15,1200,4,10,7),(16,900,3,11,7),(17,1200,4,12,7),(18,1500,5,13,7),(19,1800,6,14,7),(20,900,3,15,7),(21,600,2,16,7),(22,160,1,17,8),(23,1200,4,17,7),(33,300,1,22,7),(37,1800,6,25,7),(36,900,3,24,7),(38,1120,7,25,8),(39,320,2,26,8),(40,300,1,27,7),(41,1200,4,30,7),(42,160,1,31,8);
/*!40000 ALTER TABLE `order_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `name_user` varchar(255) DEFAULT NULL,
  `order_date` datetime DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `status` int DEFAULT NULL,
  `totalprice` double DEFAULT NULL,
  `id_user` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKp1jglhdt6fpf5plvbns0gp5ns` (`id_user`)
) ENGINE=MyISAM AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (3,'HAHA','0399617064','2023-05-27 00:00:00','0399617064',3,22222222222,5),(4,'HAHA','0399617064','2023-05-27 00:00:00','0399617064',3,11111111111,5),(5,'a','0399617064','2023-05-27 00:00:00','0399617064',3,211111111109,5),(6,'a','0399617063','2023-09-24 00:00:00','0399617063',0,600,4),(22,'HAHAggggggg','0399617064','2023-09-24 00:00:00','0399617064',0,300,5),(24,'Nam Dan Nghe An','Trang Down Thi','2023-09-25 00:00:00','0359596431',1,900,10),(25,'Hai Duong','ighka','2023-09-26 00:00:00','0123456789',1,2920,10),(26,'Hai Duong','ighka','2023-09-26 00:00:00','0123456789',1,320,10),(31,'Hai Duong','ighka','2023-09-26 00:00:00','0123456789',0,160,10),(30,'Hai Duong','ighka','2023-09-26 00:00:00','0123456789',1,300,10);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `unit` varchar(255) DEFAULT NULL,
  `id_category` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5cxv31vuhc7v32omftlxa8k3c` (`id_category`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (7,'2023-09-23 20:03:08','<p>33333333333333</p>\n','401be0b8-2764-43f2-9a00-fdeac023fd99.png','Hehe',300,NULL,1),(8,'2023-09-24 17:55:25','<p>tttrtrtrtrtrtr</p>\n','cd9531b4-54ed-495f-8d15-540a30f157a9.png','rrrrrtt3`',160,NULL,2);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ADMIN'),(2,'USER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service`
--

DROP TABLE IF EXISTS `service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service`
--

LOCK TABLES `service` WRITE;
/*!40000 ALTER TABLE `service` DISABLE KEYS */;
/*!40000 ALTER TABLE `service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_worker`
--

DROP TABLE IF EXISTS `service_worker`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_worker` (
  `id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `fullname` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_worker`
--

LOCK TABLES `service_worker` WRITE;
/*!40000 ALTER TABLE `service_worker` DISABLE KEYS */;
/*!40000 ALTER TABLE `service_worker` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `fullname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `email` varchar(50) NOT NULL,
  `last_name` varchar(100) DEFAULT NULL,
  `first_name` varchar(100) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (6,'Ha Noi','2023-05-27 17:37:22','Nguyen Van Tien','$2a$12$puZgg8sWwmNg/njvkDbkSeUDFDSfhRfk0smUbU.f0J5GHWufQL10a',NULL,'0123456789','tiengiddy1234@gmail.com',NULL,NULL,NULL),(5,'Ha Noi','2023-05-27 16:40:06','Nguyen Van Tiennnnnnnn','$2a$12$6U.VvI1kER417JPdEOvhduHy9lTOS395CFUr9Ye1UoRl0CQrMQ/bW','0399617066','0399617064','tiengiddy@gmail.com',NULL,NULL,NULL),(4,'a','2023-05-27 15:03:38','tien','$2a$12$XMg7BDmXwU4v13gwXWO5TOpnTwLtikv2xtEi3ERwnwEwTx56CRfOK','0399617063','0399617063','tiengiddy1234@gmail.com',NULL,NULL,NULL),(7,NULL,'2023-09-23 18:44:10','Trang','$2a$12$rvSZ4qQECr91DNJNP6tPp.BTaapkgkDOhwwboybzObs5DsdFZAXUm','0123456789','trangdh','tiengiddy1234@gmail.com',NULL,NULL,NULL),(8,NULL,'2023-09-25 13:03:12','Nguyen Van Tien','$2a$12$5RyX9fgyorup2F2O..F0hu0/F0MOgDE4xXN8111w7BBB9W3qAa0wW','0399617065','tiennv','nguyentien@gmail.com','Tien',' ',NULL),(9,NULL,'2023-09-25 13:04:46','Doan Van B','$2a$12$H8IoyjLfnx4fpZ5ASkLfQO.e3L6yO7gP.iTi.VfRvEXDJvfIVt9fq','0123456789','bnguyenvan','nguyenb@gmail.com','B',' DoanVan',NULL),(10,'Hai Duong','2023-09-25 13:07:04','Xa Ha Hoa','$2a$12$w8BR8sVDKtbdVTwjyLjoFu7TJJM0WsR6v1sJFnPjn7SSH0jPQHwq.','0123456789','ighka','tiengiddy@gmail.com','Hoa','Xa Ha','239ad24c-a63c-42c4-baeb-cb47ca727c6c.jpg');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_role` int NOT NULL,
  `id_user` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2aam9nt2tv8vcfymi3jo9c314` (`id_role`),
  KEY `FKfhxaael2m459kbk8lv8smr5iv` (`id_user`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (5,1,4),(2,2,5),(3,1,6),(4,2,6),(6,2,4),(7,2,7),(8,2,8),(9,2,9),(10,2,10);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-09-26 22:30:49
