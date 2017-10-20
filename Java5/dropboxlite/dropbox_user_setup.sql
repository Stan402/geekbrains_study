CREATE DATABASE  IF NOT EXISTS `dropbox_lite`;
USE `dropbox_lite`;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


--
-- Dumping data for table `customer`
--

LOCK TABLES `user` WRITE;

INSERT INTO `user` VALUES
  (1,'David','DavidPass'),
  (2,'John','JohnPass');

UNLOCK TABLES;

