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
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Chó'),(2,'Mèo'),(3,'Hamster');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_product`
--

DROP TABLE IF EXISTS `order_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_product` int NOT NULL,
  `id_order` int NOT NULL,
  `quantity` int DEFAULT NULL,
  `price` float DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_product` (`id_product`),
  KEY `id_order` (`id_order`),
  CONSTRAINT `order_product_ibfk_1` FOREIGN KEY (`id_product`) REFERENCES `product` (`id`),
  CONSTRAINT `order_product_ibfk_2` FOREIGN KEY (`id_order`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=374 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_product`
--

LOCK TABLES `order_product` WRITE;
/*!40000 ALTER TABLE `order_product` DISABLE KEYS */;
INSERT INTO `order_product` VALUES (370,37,168,1,220000),(373,37,170,4,880000);
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
  `id_user` int NOT NULL,
  `totalprice` float DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `phone_number` varchar(10) DEFAULT NULL,
  `name_user` varchar(100) DEFAULT NULL,
  `orderDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `order_date` datetime DEFAULT NULL,
  `status` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_user` (`id_user`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=171 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (168,51,220000,'dsds',NULL,'0399617064','2022-08-13 14:01:25','2022-08-13 00:00:00',1),(170,51,880000,'dsds',NULL,'0399617064','2022-08-14 10:17:31','2022-08-14 00:00:00',1);
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
  `id_category` int NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `description` text,
  `price` float DEFAULT NULL,
  `image` text,
  `create_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_category` (`id_category`),
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`id_category`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (36,1,'Lược chải lông chó mèo bấm nút DELE M006 Round Head','<p>M&ocirc; tả</p>\n\n<p>Lược chải l&ocirc;ng ch&oacute; m&egrave;o bấm n&uacute;t&nbsp;<a href=\"https://www.petmart.vn/thuong-hieu/dele\" title=\"DELE\">DELE</a>&nbsp;M006 Round Head ph&ugrave; hợp với tất cả c&aacute;c giống ch&oacute;. Bao gồm cả c&aacute;c giống ch&oacute; nhỏ v&agrave; ch&oacute; lớn như Poodle, Phốc s&oacute;c, Samoyel, Alaska&hellip;</p>\n\n<h2>Lợi &iacute;ch ch&iacute;nh</h2>\n\n<p>Lược chải l&ocirc;ng ch&oacute; m&egrave;o bấm n&uacute;t DELE M006 Round Head c&oacute; tay cầm rất thoải m&aacute;i, an to&agrave;n v&agrave; chắc chắn. Khi sử dụng bạn chỉ cần bấm v&agrave;o n&uacute;t tr&ograve;n. C&oacute; n&uacute;t bấm kết hợp với c&aacute;c răng lược sẽ tự động được đẩy ra. L&ocirc;ng rụng sẽ được đẩy theo ra ngo&agrave;i. Kh&ocirc;ng cần sử dụng tay để gỡ. Điều n&agrave;y gi&uacute;p cho việc loại bỏ những l&ocirc;ng rụng, l&ocirc;ng thừa hay l&ocirc;ng chết dễ d&agrave;ng v&agrave; nhanh ch&oacute;ng hơn.&nbsp; Đảm bảo cho ch&oacute; m&egrave;o cưng c&oacute; một bộ l&ocirc;ng mềm mượt, kh&ocirc;ng xơ rối. Sản phẩm được thiết kế tinh xảo với th&eacute;p kh&ocirc;ng gỉ, chất lượng cao. L&agrave; vật dụng rất cần thiết trong nh&agrave; bất k&igrave; ai nu&ocirc;i th&uacute; cưng.</p>\n\n<p>&nbsp;</p>\n\n<h2>Pet Mart tại H&agrave; Nội</h2>\n\n<ul>\n	<li><strong>3 Đại Cồ Việt</strong>, Phường Cầu Dền, Quận Hai B&agrave; Trưng</li>\n	<li><strong>476 Minh Khai</strong>, Phường Vĩnh Tuy, Quận Hai B&agrave; Trưng</li>\n	<li><strong>83 Nghi T&agrave;m</strong>, Phường Y&ecirc;n Phụ, Quận T&acirc;y Hồ</li>\n	<li><strong>206 Kim M&atilde;</strong>, Phường Kim M&atilde;, Quận Ba Đ&igrave;nh</li>\n	<li><strong>18 Chả C&aacute;</strong>, Phường H&agrave;ng Đ&agrave;o, Quận Ho&agrave;n Kiếm</li>\n	<li><strong>242 Nguyễn Tr&atilde;i</strong>, Phường Thanh Xu&acirc;n Trung, Quận Thanh Xu&acirc;n</li>\n	<li><strong>290 Nguyễn Văn Cừ</strong>, Phường Bồ Đề, Quận Long Bi&ecirc;n</li>\n	<li><strong>Villa E10 Đỗ Đ&igrave;nh Thiện</strong>, Phường Mỹ Đ&igrave;nh, Quận Nam Từ Li&ecirc;m</li>\n	<li><strong>81 Quang Trung</strong>, Phường Quang Trung, Quận H&agrave; Đ&ocirc;ng</li>\n</ul>\n',140000,'6f7ac290-8e46-4bef-9bce-0b7a24ab4866.jpg','2022-08-11 23:09:22'),(37,1,'Khay vệ sinh cho chó MAKAR Dog Toilet Trays Small','<p>M&ocirc; tả</p>\n\n<p><a href=\"https://www.petmart.vn/danh-muc/cho/khay-ve-sinh-cho\" title=\"Khay vệ sinh cho chó\">Khay vệ sinh cho ch&oacute;</a>&nbsp;<a href=\"https://www.petmart.vn/thuong-hieu/makar\" title=\"MAKAR\">MAKAR</a>&nbsp;Dog Toilet Trays Small với thiết kế th&ocirc;ng minh được l&agrave;m từ 100% nhựa tổng hợp cao cấp an to&agrave;n với th&uacute; cưng v&agrave; m&ocirc;i trường. Ph&ugrave; hợp với tất cả c&aacute;c giống ch&oacute; v&agrave; giới t&iacute;nh đực c&aacute;i c&oacute; trọng lượng dưới 5kg.</p>\n\n<h2>Lợi &iacute;ch ch&iacute;nh</h2>\n\n<p>Khay vệ sinh cho ch&oacute; MAKAR Dog Toilet Trays Small với tấm lưới l&oacute;t để nước tiểu kh&ocirc;ng bị vung v&atilde;i khắp nh&agrave; v&agrave; l&agrave;m ướt l&ocirc;ng th&uacute; cưng. Phần khay lưới b&ecirc;n dưới chắc v&agrave; kh&ocirc;ng bị ăn m&ograve;n. Những ch&uacute; ch&oacute; con c&oacute; thể đứng tr&ecirc;n khay để đi vệ sinh dễ d&agrave;ng v&agrave; thuận tiện. Phần th&agrave;nh cao chống lại vi khuẩn, c&ocirc;n tr&ugrave;ng v&agrave; ngăn nước tiểu bắn ra ngo&agrave;i. Đặc biệt, c&uacute;n con sẽ đ&aacute;nh hơi được dễ d&agrave;ng v&agrave; x&aacute;c định được vị tr&iacute; để thải ra nhanh hơn. Dễ d&agrave;ng trong việc hướng dẫn v&agrave; huấn luyện.</p>\n\n<p>Khay vệ sinh cho ch&oacute; MAKAR Dog Toilet Trays Small c&oacute; thiết kế nhỏ gọn, đa dạng về kiểu d&aacute;ng v&agrave; m&agrave;u sắc. Bạn c&oacute; thể lựa chọn gam m&agrave;u ph&ugrave; hợp với kh&ocirc;ng gian nơi ở của m&igrave;nh. Việc vệ sinh cũng trở l&ecirc;n đơn giản hơn với chất liệu nhựa. Bạn c&oacute; thể dễ d&agrave;ng khử m&ugrave;i v&agrave; l&agrave;m sạch ch&uacute;ng. V&agrave; y&ecirc;n t&acirc;m v&igrave; c&uacute;n cưng sẽ kh&ocirc;ng c&ograve;n đi vệ sinh bừa b&atilde;i nữa</p>\n',220000,'58f6b37a-d81c-408a-a1cc-8961e5ac5d7d.jpg','2022-08-11 23:12:51'),(38,2,'Lồng vận chuyển chó mèo AUPET BP27 Pet Kennel','<p>M&ocirc; tả</p>\n\n<p><a href=\"https://www.petmart.vn/danh-muc/cho/van-chuyen-cho/long-van-chuyen-cho\" title=\"Lồng vận chuyển chó\">Lồng vận chuyển ch&oacute;</a>&nbsp;m&egrave;o&nbsp;<a href=\"https://www.petmart.vn/thuong-hieu/aupet\" title=\"AUPET\">AUPET</a>&nbsp;BP27 Kennel với ti&ecirc;u chuẩn an to&agrave;n quốc tế, ph&ugrave; hợp với c&aacute;c y&ecirc;u cầu của h&agrave;ng kh&ocirc;ng trong quy định về vận chuyển vật nu&ocirc;i. Sản phẩm được giải thương hiệu c&ocirc;ng nghiệp vật nu&ocirc;i đ&aacute;ng tin cậy năm 2010 tại Đức. Lồng vận chuyển h&agrave;ng kh&ocirc;ng cho ch&oacute; m&egrave;o sử dụng nguy&ecirc;n liệu nhựa PP ABS kh&ocirc;ng m&ugrave;i nhập khẩu c&oacute; khả năng chịu được trọng lượng lớn, người trưởng th&agrave;nh đứng l&ecirc;n tr&ecirc;n lồng cũng kh&ocirc;ng c&oacute; vấn đề g&igrave;.</p>\n\n<p>Sản phẩm với thiết kế luồng kh&iacute; tuần ho&agrave;n th&ocirc;ng gi&oacute; mạnh nhiều lỗ kh&ocirc;ng kh&iacute; đối lưu ở mọi nơi trong lồng. Thiết kế cửa b&ecirc;n với chất liệu chống ăn m&ograve;n, chống axit, chịu được ma s&aacute;t, kh&oacute; bị gỉ. Rất an to&agrave;n khi sử dụng v&agrave; c&oacute; độ bền cao. Sản phẩm được thiết kế tay cầm sẽ kh&ocirc;ng khiến mỏi tay khi x&aacute;ch thời gian d&agrave;i c&ugrave;ng với b&aacute;nh xe trượt kh&oacute;a an to&agrave;n.</p>\n\n<h2>Lợi &iacute;ch ch&iacute;nh</h2>\n\n<ul>\n	<li>Sản phẩm lồng vận chuyển ch&oacute; m&egrave;o dễ d&agrave;ng th&aacute;o lắp, sạch sẽ dễ chịu, dễ rửa sạch, ti&ecirc;u độc.</li>\n	<li>Sản phẩm với thiết kế th&iacute;ch hợp, đường n&eacute;t tinh tế, kết cấu c&acirc;n xứng.</li>\n	<li>Sản phẩm hai b&ecirc;n tr&aacute;i phải đều c&oacute; lỗ th&ocirc;ng gi&oacute;, để th&uacute; cưng trong chuyến bay đường d&agrave;i kh&ocirc;ng&nbsp;cảm thấy kh&oacute; thở mệt mỏi.</li>\n	<li>Sản phẩm c&oacute; kiểu lưới ngăn cửa th&eacute;p kh&ocirc;ng gỉ an to&agrave;n cho th&uacute; cưng.</li>\n	<li>Tất cả c&aacute;c h&atilde;ng h&agrave;ng kh&ocirc;ng tr&ecirc;n thế giới đều&nbsp;chấp nhận cho ph&eacute;p sử dụng sản phẩm n&agrave;y.</li>\n</ul>\n\n<h2>K&iacute;ch thước sản phẩm</h2>\n\n<ul>\n	<li><strong>Size SS</strong>&nbsp;&ndash; M&atilde; sản phẩm&nbsp;<strong>0922</strong>&nbsp;(BP270) :<strong>&nbsp;29 x 41 x 31 (cm)</strong></li>\n	<li><strong>Size S</strong>&nbsp;&ndash; M&atilde; sản phẩm&nbsp;<strong>0925</strong>&nbsp;(BP274) :&nbsp;<strong>34 x 50 x 32 (cm)</strong></li>\n	<li><strong>Size M</strong>&nbsp;&ndash; M&atilde; sản phẩm&nbsp;<strong>0923</strong>&nbsp;(BP275) :&nbsp;<strong>39 x 59 x 41 (cm)</strong>Lồng vận chuyển ch&oacute; m&egrave;o AUPET BP27 Pet Kennel</li>\n</ul>\n',600000,'5b822bdc-1be5-4e10-9454-17c2c480c32e.jpg','2022-08-11 23:15:20'),(39,3,'Nhà gỗ lâu đài sắc màu','<p>Với mong muốn mang lại sự h&agrave;i l&ograve;ng cho qu&yacute; kh&aacute;ch khi mua h&agrave;ng, ch&uacute;ng t&ocirc;i c&oacute; những quy định trong vận chuyển, nhằm đảm bảo rằng những sản phẩm qu&yacute; kh&aacute;ch mua l&agrave; sản phẩm m&agrave; vừa &yacute; nhất.<br />\n1. Ch&uacute;ng t&ocirc;i sẽ được thực hiện v&agrave; chuyển ph&aacute;t dựa tr&ecirc;n mẫu kh&aacute;ch h&agrave;ng đ&atilde; chọn. Trường hợp kh&ocirc;ng c&oacute; đ&uacute;ng sản phẩm Qu&yacute; kh&aacute;ch y&ecirc;u cầu ch&uacute;ng t&ocirc;i sẽ gọi điện x&aacute;c nhận gửi sản phẩm tương tự thay thế.<br />\n2. Thời gian chuyển ph&aacute;t ti&ecirc;u chuẩn cho một đơn h&agrave;ng l&agrave; 12 giờ kể từ l&uacute;c đặt h&agrave;ng. Chuyển ph&aacute;t sản phẩm đến c&aacute;c khu vực nội th&agrave;nh th&agrave;nh phố tr&ecirc;n to&agrave;n quốc từ 4 giờ kể từ khi nhận h&agrave;ng, chuyển ph&aacute;t ngay trong ng&agrave;y đến c&aacute;c v&ugrave;ng l&acirc;n cận (b&aacute;n k&iacute;nh từ 10km &ndash; 50km).<br />\n3. C&aacute;c đơn h&agrave;ng gửi đi quốc tế: kh&ocirc;ng đảm bảo thời gian được ch&iacute;nh x&aacute;c như y&ecirc;u cầu, kh&ocirc;ng đảm bảo thời gian nếu thời điểm chuyển ph&aacute;t tr&ugrave;ng với c&aacute;c ng&agrave;y lễ, tết v&agrave; chủ nhật tại khu vực nơi đến.<br />\n4. Trường hợp kh&ocirc;ng li&ecirc;n lạc được với người nhận, người nhận đi vắng:<br />\n- Nếu chưa r&otilde; địa chỉ ch&uacute;ng t&ocirc;i sẽ lưu lại trong v&ograve;ng 6 tiếng v&agrave; li&ecirc;n lạc lại với người nhận, trong trường hợp ko li&ecirc;n lạc được đơn h&agrave;ng sẽ bị hủy v&agrave; kh&ocirc;ng được ho&agrave;n lại thanh to&aacute;n.<br />\n- Nếu địa chỉ l&agrave; c&ocirc;ng ty, văn ph&ograve;ng, nh&agrave; ở&hellip; Ch&uacute;ng t&ocirc;i sẽ gửi đồng nghiệp, người th&acirc;n nhận hộ v&agrave; k&yacute; x&aacute;c nhận<br />\n- Để tại một nơi an to&agrave;n người nhận dễ nhận thấy tại nh&agrave;, văn ph&ograve;ng, c&ocirc;ng ty&hellip; Trường hợp n&agrave;y kh&ocirc;ng c&oacute; k&yacute; nhận.<br />\n5. Trường hợp người nhận kh&ocirc;ng nhận đơn h&agrave;ng:<br />\n- Ch&uacute;ng t&ocirc;i sẽ hủy bỏ đơn h&agrave;ng. Trường hợp n&agrave;y sẽ kh&ocirc;ng được ho&agrave;n trả thanh to&aacute;n.<br />\n6. Trường hợp kh&ocirc;ng đ&uacute;ng địa chỉ, thay đổi địa chỉ:<br />\n- Kh&ocirc;ng đ&uacute;ng địa chỉ: trường hợp sai địa chỉ ch&uacute;ng t&ocirc;i sẽ lưu lại 6 tiếng v&agrave; li&ecirc;n lạc với người gửi v&agrave; người nhận để thỏa thuận về địa điểm, thời gian, nếu địa chỉ mới kh&ocirc;ng qu&aacute; 3km sẽ ph&aacute;t miễn ph&iacute;. Trường hợp địa chỉ mới xa hơn 3km sẽ t&iacute;nh th&ecirc;m ph&iacute; theo quy định chuyển ph&aacute;t.<br />\n7. Trường hợp kh&ocirc;ng tồn tại người nhận tại địa chỉ y&ecirc;u cầu: đơn h&agrave;ng sẽ được hủy v&agrave; kh&ocirc;ng được ho&agrave;n lại thanh to&aacute;n.<br />\n8. Ch&uacute;ng t&ocirc;i kh&ocirc;ng vận chuyển sản phẩm đến c&aacute;c địa chỉ tr&ecirc;n t&agrave;u hỏa, m&aacute;y bay, t&agrave;u thủy, khu vực nguy hiểm, c&aacute;c khu vực cấm&hellip;</p>\n',120000,'ccf632da-34df-4049-9a6f-fc206935f105.jpg','2022-08-11 23:16:52');
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
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
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
-- Table structure for table `service_worker`
--

DROP TABLE IF EXISTS `service_worker`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_worker` (
  `id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `fullname` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `avatar` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_worker`
--

LOCK TABLES `service_worker` WRITE;
/*!40000 ALTER TABLE `service_worker` DISABLE KEYS */;
INSERT INTO `service_worker` VALUES (1,'Ha Noi ','Lam Spa cho cho duoc 1 nam kinh nghiem','Nguyen Tuan Anh','021354687','https://scontent.fhan2-1.fna.fbcdn.net/v/t39.30808-6/299310703_4032247393725374_7057726837163764297_n.jpg?_nc_cat=1&ccb=1-7&_nc_sid=8bfeb9&_nc_ohc=YJGAT-KVM0kAX_TpVlE&tn=HVBdDJDsrHOIc-Pf&_nc_ht=scontent.fhan2-1.fna&oh=00_AT_foX9NFZ555jN75fvveAiJ3kmnk8Rl09v3jpbKWArKBg&oe=62FD9FB4');
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
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `fullname` varchar(100) DEFAULT NULL,
  `phone_number` varchar(50) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (49,'0399617063','$2a$12$dm909cg/9nwa5lCzPISLQ./pBZa1HOOedLD9xb//hYt85696m5BwC','JDSS','4343444444','Thanh xuan ha noi','2022-08-13 11:18:28'),(51,'0399617064','$2a$12$w5brLflLH8DdrOoEJk4m4uDqF7b2rKf.jKL8aUHijE3bC.D03mSji','tsdsds',NULL,'dsds','2022-08-13 12:09:54');
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
  `id_user` int NOT NULL,
  `id_role` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_role` (`id_role`),
  KEY `id_user` (`id_user`),
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`id_role`) REFERENCES `role` (`id`),
  CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (78,49,1),(80,51,2);
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

-- Dump completed on 2022-08-18 21:16:13
