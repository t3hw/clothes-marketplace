DROP TABLE IF EXISTS `garments`;
DROP TABLE IF EXISTS `users`;
DROP TABLE IF EXISTS `publishers`;

CREATE TABLE `publishers` (
  `id_number` varchar(9) NOT NULL,
  `full_name` varchar(15) NOT NULL,
  `address` varchar(30) NOT NULL,
  PRIMARY KEY (`id_number`)
);

CREATE TABLE `users` (
  `id_number` varchar(9) NOT NULL,
  `password` varchar(60) NOT NULL,
  PRIMARY KEY (`id_number`),
  CONSTRAINT `users_FK` FOREIGN KEY (`id_number`) REFERENCES `publishers` (`id_number`)
);

CREATE TABLE `garments` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `garment_type` varchar(10) NOT NULL,
  `publisher_id` varchar(9) NOT NULL,
  `size` varchar(2) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `price` float NOT NULL,
  PRIMARY KEY (`id`),
  KEY `garments_publisher_FK` (`publisher_id`),
  CONSTRAINT `garments_publisher_FK` FOREIGN KEY (`publisher_id`) REFERENCES `publishers` (`id_number`)
);