/*
SQLyog  v12.2.6 (64 bit)
MySQL - 5.7.18 : Database - sd_message
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`sd_message` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `sd_message`;

/*Table structure for table `mes_sys_log` */

DROP TABLE IF EXISTS `mes_sys_log`;

CREATE TABLE `mes_sys_log` (
  `sys_log_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `module` varchar(20) NOT NULL DEFAULT '' COMMENT '模块名',
  `level` tinyint(5) NOT NULL DEFAULT '0' COMMENT '类型0 info 1 warn 2 err',
  `trace_id` varchar(50) NOT NULL DEFAULT '',
  `message` longtext COMMENT '内容',
  `class_name` varchar(200) NOT NULL DEFAULT '' COMMENT '类',
  `request_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '报错时间',
  `create_user` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` bigint(20) NOT NULL DEFAULT '0' COMMENT '更新人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `version` int(5) NOT NULL DEFAULT '0' COMMENT '版本',
  PRIMARY KEY (`sys_log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

/*Data for the table `mes_sys_log` */

insert  into `mes_sys_log`(`sys_log_id`,`module`,`level`,`trace_id`,`message`,`class_name`,`request_time`,`create_user`,`create_time`,`update_user`,`update_time`,`version`) values 
(1,'',3,'Connection 192.168.131.102:5672 -','Channel shutdown: connection error','o.s.a.rabbit.connection.CachingConnectionFactory','2019-09-06 15:31:46',0,'2019-09-06 08:08:16',0,'2019-09-06 08:08:16',0),
(2,'',3,'Connection 192.168.131.102:5672 -','Channel shutdown: connection error','o.s.a.rabbit.connection.CachingConnectionFactory','2019-09-06 15:31:46',0,'2019-09-06 08:08:16',0,'2019-09-06 08:08:16',0),
(3,'',3,'Connection 192.168.131.102:5672 -','Channel shutdown: connection error','o.s.a.rabbit.connection.CachingConnectionFactory','2019-09-06 15:31:46',0,'2019-09-06 08:09:49',0,'2019-09-06 08:09:49',0),
(4,'',3,'Connection 192.168.131.102:5672 -','Channel shutdown: connection error','o.s.a.rabbit.connection.CachingConnectionFactory','2019-09-06 15:31:46',0,'2019-09-06 08:09:49',0,'2019-09-06 08:09:49',0),
(5,'',3,'-','Failed to check/redeclare auto-delete queue(s).','o.s.a.r.listener.SimpleMessageListenerContainer','2019-09-06 16:08:04',0,'2019-09-06 08:09:49',0,'2019-09-06 08:09:49',0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
