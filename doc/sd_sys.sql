/*
SQLyog  v12.2.6 (64 bit)
MySQL - 5.7.18 : Database - sd_sys
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`sd_sys` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `sd_sys`;

/*Table structure for table `sys_dict` */

DROP TABLE IF EXISTS `sys_dict`;

CREATE TABLE `sys_dict` (
  `dict_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code` varchar(50) NOT NULL DEFAULT '' COMMENT 'code',
  `dict_name` varchar(100) NOT NULL DEFAULT '' COMMENT '名称',
  `dict_value` varchar(200) NOT NULL DEFAULT '' COMMENT '值',
  `dict_type` int(5) NOT NULL DEFAULT '0' COMMENT '类型',
  `dict_state` tinyint(2) NOT NULL DEFAULT '0' COMMENT '状态',
  `parent_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '父级',
  `create_user` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` bigint(20) NOT NULL DEFAULT '0' COMMENT '更新人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `version` int(5) NOT NULL DEFAULT '0' COMMENT '版本',
  PRIMARY KEY (`dict_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_dict` */

/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `menu_name` varchar(50) NOT NULL COMMENT '菜单名称',
  `level` int(5) NOT NULL DEFAULT '0' COMMENT '层级',
  `parent_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '父级id',
  `menu_type` int(5) NOT NULL DEFAULT '0' COMMENT '菜单类型',
  `menu_state` int(5) NOT NULL DEFAULT '0' COMMENT '菜单状态',
  `url` varchar(200) NOT NULL DEFAULT '' COMMENT '跳转连接',
  `icon` varchar(50) NOT NULL DEFAULT '' COMMENT '图标',
  `sort` int(5) NOT NULL DEFAULT '0' COMMENT '排序',
  `create_user` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` bigint(20) NOT NULL DEFAULT '0' COMMENT '更新人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `version` int(5) NOT NULL DEFAULT '0' COMMENT '版本',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`menu_id`,`menu_name`,`level`,`parent_id`,`menu_type`,`menu_state`,`url`,`icon`,`sort`,`create_user`,`create_time`,`update_user`,`update_time`,`version`) values

(1,'系统管理',0,0,0,1,'','xxx',0,0,'2019-07-24 09:22:11',0,'2019-07-24 09:22:11',0),

(2,'订单管理',0,0,0,1,'','',0,0,'2019-07-24 09:23:05',0,'2019-07-24 09:23:05',0),

(3,'商品管理',0,0,0,1,'','',0,0,'2019-07-24 09:23:45',0,'2019-07-24 09:23:45',0),

(4,'库存管理',0,0,0,1,'','',0,0,'2019-07-24 09:23:52',0,'2019-07-24 09:23:52',0),

(5,'会员管理',0,0,0,1,'xxx','',0,0,'2019-07-24 09:26:05',0,'2019-07-24 09:26:05',0),

(6,'订单列表',0,2,1,1,'xxx','',0,0,'2019-07-24 09:26:43',0,'2019-07-24 09:26:43',0),

(7,'商品列表',0,3,1,1,'xxx','',0,0,'2019-07-24 09:27:31',0,'2019-07-24 09:27:31',0),

(8,'库存列表',0,4,1,1,'xxx','',0,0,'2019-07-24 09:27:48',0,'2019-07-24 09:27:48',0),

(9,'菜单管理',0,1,1,1,'xxx','',0,0,'2019-07-24 09:32:59',0,'2019-07-24 09:32:59',0),

(10,'角色管理',0,1,1,1,'xxx','',0,0,'2019-07-24 09:33:06',0,'2019-07-24 09:33:06',0);

/*Table structure for table `sys_permission` */

DROP TABLE IF EXISTS `sys_permission`;

CREATE TABLE `sys_permission` (
  `permission_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `permission_code` varchar(50) NOT NULL DEFAULT '' COMMENT 'code',
  `permission_name` varchar(100) NOT NULL DEFAULT '' COMMENT '名称',
  `parent_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '父级',
  `create_user` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` bigint(20) NOT NULL DEFAULT '0' COMMENT '更新人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `version` int(5) NOT NULL DEFAULT '0' COMMENT '版本',
  PRIMARY KEY (`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `sys_permission` */

/*Table structure for table `sys_permission_menu` */

DROP TABLE IF EXISTS `sys_permission_menu`;

CREATE TABLE `sys_permission_menu` (
  `permission_menu_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `permission_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '权限',
  `menu_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '菜单',
  `create_user` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` bigint(20) NOT NULL DEFAULT '0' COMMENT '更新人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `version` int(5) NOT NULL DEFAULT '0' COMMENT '版本',
  PRIMARY KEY (`permission_menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `sys_permission_menu` */

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `role_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_name` varchar(50) NOT NULL DEFAULT '' COMMENT '角色名称',
  `role_state` int(5) NOT NULL DEFAULT '0' COMMENT '角色状态',
  `parent_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '父级',
  `remark` varchar(500) NOT NULL DEFAULT '' COMMENT '备注',
  `create_user` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` bigint(20) NOT NULL DEFAULT '0' COMMENT '更新人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `version` int(5) NOT NULL DEFAULT '0' COMMENT '版本',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `sys_role` */

insert  into `sys_role`(`role_id`,`role_name`,`role_state`,`parent_id`,`remark`,`create_user`,`create_time`,`update_user`,`update_time`,`version`) values

(1,'超级管理员',1,0,'最高权限',0,'2019-07-24 10:13:45',0,'2019-07-24 10:13:45',0),

(2,'财务',1,0,'财务模块',0,'2019-07-24 10:14:23',0,'2019-07-24 10:14:23',0),

(3,'跟单',1,0,'订单管理,商品管理',0,'2019-07-24 10:14:35',0,'2019-07-24 10:14:35',0);

/*Table structure for table `sys_role_permission` */

DROP TABLE IF EXISTS `sys_role_permission`;

CREATE TABLE `sys_role_permission` (
  `role_permission_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '角色',
  `permission_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '权限',
  `create_user` bigint(20) NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL,
  `update_user` bigint(20) NOT NULL DEFAULT '0',
  `update_time` datetime NOT NULL,
  `version` int(5) NOT NULL DEFAULT '0',
  PRIMARY KEY (`role_permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `sys_role_permission` */

/*Table structure for table `sys_role_user` */

DROP TABLE IF EXISTS `sys_role_user`;

CREATE TABLE `sys_role_user` (
  `role_user_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '角色',
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户',
  `is_deleted` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `create_user` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` bigint(20) NOT NULL DEFAULT '0' COMMENT '更新人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `version` int(5) NOT NULL DEFAULT '0' COMMENT '版本',
  PRIMARY KEY (`role_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `sys_role_user` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
