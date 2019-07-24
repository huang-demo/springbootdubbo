/*
SQLyog  v12.2.6 (64 bit)
MySQL - 5.7.18 : Database - sd_user
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`sd_user` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `sd_user`;

/*Table structure for table `user_account` */

DROP TABLE IF EXISTS `user_account`;

CREATE TABLE `user_account` (
  `account_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户id',
  `money` bigint(20) NOT NULL DEFAULT '0' COMMENT '金额(分)',
  `points` bigint(20) NOT NULL DEFAULT '0' COMMENT '积分',
  `salt` varchar(20) NOT NULL DEFAULT '' COMMENT '加密盐',
  `create_user` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` bigint(20) NOT NULL DEFAULT '0' COMMENT '更新人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `version` int(5) NOT NULL DEFAULT '0' COMMENT '版本',
  PRIMARY KEY (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_account` */

/*Table structure for table `user_account_flow` */

DROP TABLE IF EXISTS `user_account_flow`;

CREATE TABLE `user_account_flow` (
  `account_flow_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL DEFAULT '0',
  `flow_type` int(5) NOT NULL DEFAULT '0' COMMENT '流水类型(1 账户金额,2账户积分)',
  `before_value` bigint(20) NOT NULL DEFAULT '0' COMMENT '变更前',
  `change_value` bigint(20) NOT NULL DEFAULT '0' COMMENT '变更值',
  `after_value` bigint(20) NOT NULL DEFAULT '0' COMMENT '变更后',
  `create_user` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` bigint(20) NOT NULL DEFAULT '0' COMMENT '更新人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `version` int(5) NOT NULL DEFAULT '0' COMMENT '版本',
  PRIMARY KEY (`account_flow_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_account_flow` */

/*Table structure for table `user_info` */

DROP TABLE IF EXISTS `user_info`;

CREATE TABLE `user_info` (
  `user_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_name` varchar(50) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(50) NOT NULL DEFAULT '' COMMENT '密码',
  `open_id` varchar(50) NOT NULL DEFAULT '' COMMENT '微信号',
  `avator` varchar(200) NOT NULL COMMENT '微信头像',
  `appid` varchar(50) NOT NULL DEFAULT '' COMMENT '公众号',
  `phone` varchar(20) NOT NULL DEFAULT '' COMMENT '电话号码',
  `email` varchar(50) NOT NULL DEFAULT '' COMMENT '邮箱',
  `user_state` int(5) NOT NULL DEFAULT '0' COMMENT '状态 0默认1有效账户2 锁定',
  `user_type` int(5) NOT NULL DEFAULT '0' COMMENT '用户类型,0默认 1系统账户,2微信用户',
  `create_user` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` bigint(20) NOT NULL DEFAULT '0' COMMENT '更新人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `version` int(5) NOT NULL DEFAULT '0' COMMENT '版本',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `user_info` */

insert  into `user_info`(`user_id`,`user_name`,`password`,`open_id`,`avator`,`appid`,`phone`,`email`,`user_state`,`user_type`,`create_user`,`create_time`,`update_user`,`update_time`,`version`) values 
(1,'admin','21232F297A57A5A743894A0E4A801FC3','xxx','xxx','','','',0,0,0,'2019-07-22 15:31:06',0,'2019-07-22 15:31:09',0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
