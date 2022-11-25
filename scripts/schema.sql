DROP TABLE IF EXISTS `publishers`;
CREATE TABLE `publishers` (
  `id_number` varchar(9) NOT NULL,
  `full_name` varchar(15) NOT NULL,
  `address` varchar(30) NOT NULL,
  PRIMARY KEY (`id_number`)
);

DROP TABLE IF EXISTS `garments`;
CREATE TABLE `garments` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `garment_type` varchar(10) NOT NULL,
  `publisher_id_number` varchar(9) NOT NULL,
  `size` varchar(2) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `price` float NOT NULL,
  PRIMARY KEY (`id`),
  KEY `garments_publisher_FK` (`publisher_id_number`),
  CONSTRAINT `garments_publisher_FK` FOREIGN KEY (`publisher_id_number`) REFERENCES `publishers` (`id_number`)
);