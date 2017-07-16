/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50554
Source Host           : localhost:3306
Source Database       : spring_hibernate

Target Server Type    : MYSQL
Target Server Version : 50554
File Encoding         : 65001

Date: 2017-07-16 15:39:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_member`
-- ----------------------------
DROP TABLE IF EXISTS `t_member`;
CREATE TABLE `t_member` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of t_member
-- ----------------------------
INSERT INTO `t_member` VALUES ('5', 'test1', 'test1');
INSERT INTO `t_member` VALUES ('6', 'test0', 'test0');
INSERT INTO `t_member` VALUES ('7', 'test1', 'test1');
INSERT INTO `t_member` VALUES ('8', 'test0', 'test0');
INSERT INTO `t_member` VALUES ('9', 'test1', 'test1');
INSERT INTO `t_member` VALUES ('10', 'test0', 'test0');
INSERT INTO `t_member` VALUES ('11', 'test1', 'test1');
