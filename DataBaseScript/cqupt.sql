/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50562
Source Host           : localhost:3306
Source Database       : cqupt

Target Server Type    : MYSQL
Target Server Version : 50562
File Encoding         : 65001

Date: 2023-08-19 15:13:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for apply
-- ----------------------------
DROP TABLE IF EXISTS `apply`;
CREATE TABLE `apply` (
  `apply_id` int(10) NOT NULL AUTO_INCREMENT,
  `apply_num` int(10) DEFAULT NULL,
  `apply_reason` varchar(255) DEFAULT NULL,
  `apply_day` int(10) DEFAULT NULL,
  `apply_datetime` datetime DEFAULT NULL,
  `apply_status` int(10) DEFAULT NULL,
  `review_datetime` datetime DEFAULT NULL,
  `review_comments` varchar(10) DEFAULT NULL,
  `user_id` int(10) DEFAULT NULL,
  `lab_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`apply_id`),
  KEY `apply_reason` (`apply_reason`),
  KEY `apply_ibfk_1` (`lab_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of apply
-- ----------------------------
INSERT INTO `apply` VALUES ('8', '20', '做客', '20', '2023-08-14 00:00:00', '2', '2023-08-24 00:00:00', '通过', '1', '5');
INSERT INTO `apply` VALUES ('22', '52', 'sadas', '25', '2023-08-19 11:17:17', '0', null, null, '1', null);
INSERT INTO `apply` VALUES ('23', '1551', '15', '15', '2023-08-19 11:18:08', '3', null, '', '1', null);

-- ----------------------------
-- Table structure for facility
-- ----------------------------
DROP TABLE IF EXISTS `facility`;
CREATE TABLE `facility` (
  `facility_id` int(10) NOT NULL AUTO_INCREMENT,
  `facility_name` varchar(255) DEFAULT NULL,
  `facility_type` int(10) DEFAULT NULL,
  `lab_id` int(10) DEFAULT NULL,
  `lab_num` int(11) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `user_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`facility_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of facility
-- ----------------------------
INSERT INTO `facility` VALUES ('2', '收集', '50', '3', '55', '2023-08-18 16:30:49', '2023-08-18 16:30:50', null);
INSERT INTO `facility` VALUES ('3', '笔记本', '20', '3', '55', '2023-08-18 16:40:05', '2023-08-18 16:40:07', null);
INSERT INTO `facility` VALUES ('4', '阿拉蕾', '4', '4', '5', '2023-08-18 16:41:32', '2023-08-18 16:41:33', null);
INSERT INTO `facility` VALUES ('5', '6', '66', '6', '66', '2023-08-18 16:41:53', '2023-08-18 16:41:54', null);
INSERT INTO `facility` VALUES ('6', 'iad', '3', '10', '10', '2023-08-19 09:19:50', '2023-08-19 09:19:52', null);
INSERT INTO `facility` VALUES ('7', 'sada', '11', '11', '11', '2023-08-19 09:23:40', '2023-08-19 09:23:42', null);
INSERT INTO `facility` VALUES ('9', 'sdas', '2', '2', '2', null, null, null);

-- ----------------------------
-- Table structure for lab
-- ----------------------------
DROP TABLE IF EXISTS `lab`;
CREATE TABLE `lab` (
  `lab_id` int(10) NOT NULL AUTO_INCREMENT,
  `lab_num` int(10) DEFAULT NULL,
  `apply_day` int(10) DEFAULT NULL,
  `apply_datetime` datetime DEFAULT NULL,
  `apply_status` int(10) DEFAULT NULL,
  `review_datetime` datetime DEFAULT NULL,
  `review_comments` varchar(10) DEFAULT NULL,
  `apply_reason` varchar(255) DEFAULT NULL,
  `user_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`lab_id`),
  KEY `lab_ibfk_1` (`apply_reason`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lab
-- ----------------------------
INSERT INTO `lab` VALUES ('2', '2', null, null, null, null, null, null, null);
INSERT INTO `lab` VALUES ('5', '55', '80', '2023-08-17 22:18:10', '2', null, '', 'sadas', '3');
INSERT INTO `lab` VALUES ('8', '66', null, '2023-08-19 13:59:48', '1', null, null, '做客', '5');
INSERT INTO `lab` VALUES ('12', '10', null, null, '3', null, null, '', '10');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `menu_id` int(11) NOT NULL,
  `menu_name` varchar(100) DEFAULT NULL,
  `menu_url` varchar(100) DEFAULT NULL,
  `menu_path` varchar(100) DEFAULT NULL,
  `menu_icon` varchar(100) DEFAULT NULL,
  `menu_order` int(11) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `createdate` date DEFAULT NULL,
  `updatedate` date DEFAULT NULL,
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '首页', '/main/index.vue', '/main', null, '1', null, '2023-08-11', '2023-08-11');
INSERT INTO `menu` VALUES ('2', '实验室申请', '/apply/index.vue', '/apply', null, '1', null, '2023-08-11', '2023-08-11');
INSERT INTO `menu` VALUES ('3', '申请审批', '/reviewapply/index.vue', '/reviewapply', null, '1', null, '2023-08-11', '2023-08-11');
INSERT INTO `menu` VALUES ('4', '实验室管理', '/admin/lab/index.vue', '/lab', null, '1', null, '2023-08-17', '2023-08-17');
INSERT INTO `menu` VALUES ('5', '仿真设备管理', '/admin/facility/index.vue', '/facility', null, '1', null, '2023-08-18', '2023-08-18');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `role_id` int(11) NOT NULL,
  `role_name` varchar(100) DEFAULT NULL,
  `createdate` date DEFAULT NULL,
  `updatedate` date DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '学生', '2023-08-11', '2023-08-11');
INSERT INTO `role` VALUES ('2', '老师', '2023-08-11', '2023-08-11');
INSERT INTO `role` VALUES ('3', '管理员', '2023-08-17', '2023-08-17');

-- ----------------------------
-- Table structure for role_menu_mapping
-- ----------------------------
DROP TABLE IF EXISTS `role_menu_mapping`;
CREATE TABLE `role_menu_mapping` (
  `role_id` int(11) NOT NULL,
  `menu_id` int(11) NOT NULL,
  `role_menu_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`role_id`,`menu_id`),
  KEY `FK_拥有` (`menu_id`),
  CONSTRAINT `FK_拥有` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_menu_mapping
-- ----------------------------
INSERT INTO `role_menu_mapping` VALUES ('1', '1', '1');
INSERT INTO `role_menu_mapping` VALUES ('1', '2', '2');
INSERT INTO `role_menu_mapping` VALUES ('2', '1', '5');
INSERT INTO `role_menu_mapping` VALUES ('2', '3', '3');
INSERT INTO `role_menu_mapping` VALUES ('3', '1', '6');
INSERT INTO `role_menu_mapping` VALUES ('3', '4', '4');
INSERT INTO `role_menu_mapping` VALUES ('3', '5', '7');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `user_id` int(11) NOT NULL,
  `sut_id` int(11) DEFAULT NULL,
  `stu_name` varchar(100) DEFAULT NULL,
  `stu_age` int(11) DEFAULT NULL,
  `stu_sex` int(11) DEFAULT NULL,
  `createdate` date DEFAULT NULL,
  `updatedate` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('46', null, '王五', '19', null, null, null);
INSERT INTO `student` VALUES ('47', null, '刘阳森', '20', null, null, null);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL,
  `user_type` int(11) DEFAULT NULL,
  `create_date` date DEFAULT NULL,
  `update_date` date DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'lisi', '123123', '李四', '19932381409', '1304752531@qq.com', '上课', '1', null, null, '23');
INSERT INTO `user` VALUES ('2', 'laoshi', '123123', '王五', '19932381409', '1304752531@qq.com', '2313', '2', null, null, '23');
INSERT INTO `user` VALUES ('3', 'admin', '123123', '王五', '19932381409', '123132@qq.com', 'A nice person', '3', '2023-08-12', '2023-08-12', '19');
INSERT INTO `user` VALUES ('47', 'lys', '123123', '刘阳森', '19932381409', '1304752531@qq.com', 'hh', '1', null, null, '20');

-- ----------------------------
-- Table structure for user_role_mapping
-- ----------------------------
DROP TABLE IF EXISTS `user_role_mapping`;
CREATE TABLE `user_role_mapping` (
  `role_id` int(11) NOT NULL,
  `user_role_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role_mapping
-- ----------------------------
INSERT INTO `user_role_mapping` VALUES ('1', '1', '1');
INSERT INTO `user_role_mapping` VALUES ('2', '2', '2');
INSERT INTO `user_role_mapping` VALUES ('3', '3', '3');
