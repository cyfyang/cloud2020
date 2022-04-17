CREATE DATABASE /*!32312 IF NOT EXISTS*/`db2022` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `db2022`;

DROP TABLE IF EXISTS  payment;

--支付表
CREATE TABLE `payment`(
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `serial` varchar(200) DEFAULT '',
  PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8