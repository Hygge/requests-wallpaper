/*
 Navicat Premium Data Transfer

 Source Server         : 101.35.253.129
 Source Server Type    : MySQL
 Source Server Version : 50736
 Source Host           : 101.35.253.129:3306
 Source Schema         : gadgets

 Target Server Type    : MySQL
 Target Server Version : 50736
 File Encoding         : 65001

 Date: 10/01/2022 13:46:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bing_wallpaper
-- ----------------------------
DROP TABLE IF EXISTS `bing_wallpaper`;
CREATE TABLE `bing_wallpaper`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `copyright` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '版权',
  `copyrightlink` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '来源地址',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片地址',
  `urlbase` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `startdate` datetime(0) NULL DEFAULT NULL COMMENT '开始日期',
  `fullstartdate` datetime(0) NULL DEFAULT NULL COMMENT '时间',
  `enddate` datetime(0) NULL DEFAULT NULL COMMENT '结束日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
