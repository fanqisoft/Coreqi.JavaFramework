/*
 Navicat Premium Data Transfer

 Source Server         : localMysql
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : coreqi_java_framework

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 28/04/2020 18:27:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `url` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `path` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `component` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `iconCls` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `keepAlive` tinyint(1) NULL DEFAULT NULL,
  `requireAuth` tinyint(1) NULL DEFAULT NULL,
  `parentId` int(0) NULL DEFAULT NULL,
  `enabled` tinyint(1) NULL DEFAULT 1,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `parentId`(`parentId`) USING BTREE,
  CONSTRAINT `menu_ibfk_1` FOREIGN KEY (`parentId`) REFERENCES `t_menu` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES (1, '/', NULL, NULL, '所有', NULL, NULL, NULL, NULL, 1);
INSERT INTO `t_menu` VALUES (2, '/', '/home', 'Home', '员工资料', 'fa fa-user-circle-o', NULL, 1, 1, 1);
INSERT INTO `t_menu` VALUES (3, '/', '/home', 'Home', '人事管理', 'fa fa-address-card-o', NULL, 1, 1, 1);
INSERT INTO `t_menu` VALUES (4, '/', '/home', 'Home', '薪资管理', 'fa fa-money', NULL, 1, 1, 1);
INSERT INTO `t_menu` VALUES (5, '/', '/home', 'Home', '统计管理', 'fa fa-bar-chart', NULL, 1, 1, 1);
INSERT INTO `t_menu` VALUES (6, '/', '/home', 'Home', '系统管理', 'fa fa-windows', NULL, 1, 1, 1);
INSERT INTO `t_menu` VALUES (7, '/employee/basic/**', '/emp/basic', 'EmpBasic', '基本资料', NULL, NULL, 1, 2, 1);
INSERT INTO `t_menu` VALUES (8, '/employee/advanced/**', '/emp/adv', 'EmpAdv', '高级资料', NULL, NULL, 1, 2, 1);
INSERT INTO `t_menu` VALUES (9, '/personnel/emp/**', '/per/emp', 'PerEmp', '员工资料', NULL, NULL, 1, 3, 1);
INSERT INTO `t_menu` VALUES (10, '/personnel/ec/**', '/per/ec', 'PerEc', '员工奖惩', NULL, NULL, 1, 3, 1);
INSERT INTO `t_menu` VALUES (11, '/personnel/train/**', '/per/train', 'PerTrain', '员工培训', NULL, NULL, 1, 3, 1);
INSERT INTO `t_menu` VALUES (12, '/personnel/salary/**', '/per/salary', 'PerSalary', '员工调薪', NULL, NULL, 1, 3, 1);
INSERT INTO `t_menu` VALUES (13, '/personnel/remove/**', '/per/mv', 'PerMv', '员工调动', NULL, NULL, 1, 3, 1);
INSERT INTO `t_menu` VALUES (14, '/salary/sob/**', '/sal/sob', 'SalSob', '工资账套管理', NULL, NULL, 1, 4, 1);
INSERT INTO `t_menu` VALUES (15, '/salary/sobcfg/**', '/sal/sobcfg', 'SalSobCfg', '员工账套设置', NULL, NULL, 1, 4, 1);
INSERT INTO `t_menu` VALUES (16, '/salary/table/**', '/sal/table', 'SalTable', '工资表管理', NULL, NULL, 1, 4, 1);
INSERT INTO `t_menu` VALUES (17, '/salary/month/**', '/sal/month', 'SalMonth', '月末处理', NULL, NULL, 1, 4, 1);
INSERT INTO `t_menu` VALUES (18, '/salary/search/**', '/sal/search', 'SalSearch', '工资表查询', NULL, NULL, 1, 4, 1);
INSERT INTO `t_menu` VALUES (19, '/statistics/all/**', '/sta/all', 'StaAll', '综合信息统计', NULL, NULL, 1, 5, 1);
INSERT INTO `t_menu` VALUES (20, '/statistics/score/**', '/sta/score', 'StaScore', '员工积分统计', NULL, NULL, 1, 5, 1);
INSERT INTO `t_menu` VALUES (21, '/statistics/personnel/**', '/sta/pers', 'StaPers', '人事信息统计', NULL, NULL, 1, 5, 1);
INSERT INTO `t_menu` VALUES (22, '/statistics/recored/**', '/sta/record', 'StaRecord', '人事记录统计', NULL, NULL, 1, 5, 1);
INSERT INTO `t_menu` VALUES (23, '/system/basic/**', '/sys/basic', 'SysBasic', '基础信息设置', NULL, NULL, 1, 6, 1);
INSERT INTO `t_menu` VALUES (24, '/system/cfg/**', '/sys/cfg', 'SysCfg', '系统管理', NULL, NULL, 1, 6, 1);
INSERT INTO `t_menu` VALUES (25, '/system/log/**', '/sys/log', 'SysLog', '操作日志管理', NULL, NULL, 1, 6, 1);
INSERT INTO `t_menu` VALUES (26, '/system/hr/**', '/sys/hr', 'SysHr', '操作员管理', NULL, NULL, 1, 6, 1);
INSERT INTO `t_menu` VALUES (27, '/system/data/**', '/sys/data', 'SysData', '备份恢复数据库', NULL, NULL, 1, 6, 1);
INSERT INTO `t_menu` VALUES (28, '/system/init/**', '/sys/init', 'SysInit', '初始化数据库', NULL, NULL, 1, 6, 1);

-- ----------------------------
-- Table structure for t_menu_role
-- ----------------------------
DROP TABLE IF EXISTS `t_menu_role`;
CREATE TABLE `t_menu_role`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `mid` int(0) NULL DEFAULT NULL,
  `rid` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `mid`(`mid`) USING BTREE,
  INDEX `rid`(`rid`) USING BTREE,
  CONSTRAINT `menu_role_ibfk_1` FOREIGN KEY (`mid`) REFERENCES `t_menu` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `menu_role_ibfk_2` FOREIGN KEY (`rid`) REFERENCES `t_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 282 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_menu_role
-- ----------------------------
INSERT INTO `t_menu_role` VALUES (161, 7, 3);
INSERT INTO `t_menu_role` VALUES (162, 7, 6);
INSERT INTO `t_menu_role` VALUES (163, 9, 6);
INSERT INTO `t_menu_role` VALUES (164, 10, 6);
INSERT INTO `t_menu_role` VALUES (165, 11, 6);
INSERT INTO `t_menu_role` VALUES (166, 12, 6);
INSERT INTO `t_menu_role` VALUES (167, 13, 6);
INSERT INTO `t_menu_role` VALUES (168, 14, 6);
INSERT INTO `t_menu_role` VALUES (169, 15, 6);
INSERT INTO `t_menu_role` VALUES (170, 16, 6);
INSERT INTO `t_menu_role` VALUES (171, 17, 6);
INSERT INTO `t_menu_role` VALUES (172, 18, 6);
INSERT INTO `t_menu_role` VALUES (173, 19, 6);
INSERT INTO `t_menu_role` VALUES (174, 20, 6);
INSERT INTO `t_menu_role` VALUES (175, 21, 6);
INSERT INTO `t_menu_role` VALUES (176, 22, 6);
INSERT INTO `t_menu_role` VALUES (177, 23, 6);
INSERT INTO `t_menu_role` VALUES (178, 25, 6);
INSERT INTO `t_menu_role` VALUES (179, 26, 6);
INSERT INTO `t_menu_role` VALUES (180, 27, 6);
INSERT INTO `t_menu_role` VALUES (181, 28, 6);
INSERT INTO `t_menu_role` VALUES (182, 24, 6);
INSERT INTO `t_menu_role` VALUES (247, 7, 4);
INSERT INTO `t_menu_role` VALUES (248, 8, 4);
INSERT INTO `t_menu_role` VALUES (249, 11, 4);
INSERT INTO `t_menu_role` VALUES (250, 7, 2);
INSERT INTO `t_menu_role` VALUES (251, 8, 2);
INSERT INTO `t_menu_role` VALUES (252, 9, 2);
INSERT INTO `t_menu_role` VALUES (253, 10, 2);
INSERT INTO `t_menu_role` VALUES (254, 12, 2);
INSERT INTO `t_menu_role` VALUES (255, 13, 2);
INSERT INTO `t_menu_role` VALUES (256, 7, 1);
INSERT INTO `t_menu_role` VALUES (257, 8, 1);
INSERT INTO `t_menu_role` VALUES (258, 9, 1);
INSERT INTO `t_menu_role` VALUES (259, 10, 1);
INSERT INTO `t_menu_role` VALUES (260, 11, 1);
INSERT INTO `t_menu_role` VALUES (261, 12, 1);
INSERT INTO `t_menu_role` VALUES (262, 13, 1);
INSERT INTO `t_menu_role` VALUES (263, 14, 1);
INSERT INTO `t_menu_role` VALUES (264, 15, 1);
INSERT INTO `t_menu_role` VALUES (265, 16, 1);
INSERT INTO `t_menu_role` VALUES (266, 17, 1);
INSERT INTO `t_menu_role` VALUES (267, 18, 1);
INSERT INTO `t_menu_role` VALUES (268, 19, 1);
INSERT INTO `t_menu_role` VALUES (269, 20, 1);
INSERT INTO `t_menu_role` VALUES (270, 21, 1);
INSERT INTO `t_menu_role` VALUES (271, 22, 1);
INSERT INTO `t_menu_role` VALUES (272, 23, 1);
INSERT INTO `t_menu_role` VALUES (273, 24, 1);
INSERT INTO `t_menu_role` VALUES (274, 25, 1);
INSERT INTO `t_menu_role` VALUES (275, 26, 1);
INSERT INTO `t_menu_role` VALUES (276, 27, 1);
INSERT INTO `t_menu_role` VALUES (277, 28, 1);
INSERT INTO `t_menu_role` VALUES (280, 7, 14);
INSERT INTO `t_menu_role` VALUES (281, 8, 14);
INSERT INTO `t_menu_role` VALUES (282, 9, 14);

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `nameZh` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES (1, 'ROLE_manager', '部门经理');
INSERT INTO `t_role` VALUES (2, 'ROLE_personnel', '人事专员');
INSERT INTO `t_role` VALUES (3, 'ROLE_recruiter', '招聘主管');
INSERT INTO `t_role` VALUES (4, 'ROLE_train', '培训主管');
INSERT INTO `t_role` VALUES (5, 'ROLE_performance', '薪酬绩效主管');
INSERT INTO `t_role` VALUES (6, 'ROLE_admin', '系统管理员');
INSERT INTO `t_role` VALUES (13, 'ROLE_test2', '测试角色2');
INSERT INTO `t_role` VALUES (14, 'ROLE_test1', '测试角色1');
INSERT INTO `t_role` VALUES (17, 'ROLE_test3', '测试角色3');
INSERT INTO `t_role` VALUES (18, 'ROLE_test4', '测试角色4');
INSERT INTO `t_role` VALUES (19, 'ROLE_test4', '测试角色4');
INSERT INTO `t_role` VALUES (20, 'ROLE_test5', '测试角色5');
INSERT INTO `t_role` VALUES (21, 'ROLE_test6', '测试角色6');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `phone` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `telephone` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '住宅电话',
  `address` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系地址',
  `enabled` tinyint(1) NULL DEFAULT 1,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `userface` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户界面',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (3, '系统管理员', '18568887789', '029-82881234', '深圳南山', 1, 'admin', '$2a$10$aqV94yml8DDvEdHIvY9lReuTNLGOxsPJEQtTvdkv/pTCeGfuZ1DMm', 'http://bpic.588ku.com/element_pic/01/40/00/64573ce2edc0728.jpg', NULL);
INSERT INTO `t_user` VALUES (5, '李白', '18568123489', '029-82123434', '海口美兰', 1, 'libai', '$2a$10$oE39aG10kB/rFu2vQeCJTu/V/v4n6DRR0f8WyXRiAYvBpmadoOBE.', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1514093920321&di=913e88c23f382933ef430024afd9128a&imgtype=0&src=http%3A%2F%2Fp.3761.com%2Fpic%2F9771429316733.jpg', NULL);
INSERT INTO `t_user` VALUES (10, '韩愈', '18568123666', '029-82111555', '广州番禺', 1, 'hanyu', '$2a$10$oE39aG10kB/rFu2vQeCJTu/V/v4n6DRR0f8WyXRiAYvBpmadoOBE.', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1517070040185&di=be0375e0c3db6c311b837b28c208f318&imgtype=0&src=http%3A%2F%2Fimg2.soyoung.com%2Fpost%2F20150213%2F6%2F20150213141918532.jpg', NULL);
INSERT INTO `t_user` VALUES (11, '柳宗元', '18568123377', '029-82111333', '广州天河', 1, 'liuzongyuan', '$2a$10$oE39aG10kB/rFu2vQeCJTu/V/v4n6DRR0f8WyXRiAYvBpmadoOBE.', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1515233756&di=0856d923a0a37a87fd26604a2c871370&imgtype=jpg&er=1&src=http%3A%2F%2Fwww.qqzhi.com%2Fuploadpic%2F2014-09-27%2F041716704.jpg', NULL);
INSERT INTO `t_user` VALUES (12, '曾巩', '18568128888', '029-82111222', '广州越秀', 1, 'zenggong', '$2a$10$oE39aG10kB/rFu2vQeCJTu/V/v4n6DRR0f8WyXRiAYvBpmadoOBE.', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1517070040185&di=be0375e0c3db6c311b837b28c208f318&imgtype=0&src=http%3A%2F%2Fimg2.soyoung.com%2Fpost%2F20150213%2F6%2F20150213141918532.jpg', NULL);

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `userid` int(0) NULL DEFAULT NULL,
  `rid` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `rid`(`rid`) USING BTREE,
  INDEX `user_role_ibfk_1`(`userid`) USING BTREE,
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `t_user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`rid`) REFERENCES `t_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 75 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES (1, 3, 6);
INSERT INTO `t_user_role` VALUES (35, 12, 4);
INSERT INTO `t_user_role` VALUES (36, 12, 3);
INSERT INTO `t_user_role` VALUES (37, 12, 2);
INSERT INTO `t_user_role` VALUES (43, 11, 3);
INSERT INTO `t_user_role` VALUES (44, 11, 2);
INSERT INTO `t_user_role` VALUES (45, 11, 4);
INSERT INTO `t_user_role` VALUES (46, 11, 5);
INSERT INTO `t_user_role` VALUES (48, 10, 3);
INSERT INTO `t_user_role` VALUES (49, 10, 4);
INSERT INTO `t_user_role` VALUES (72, 5, 1);
INSERT INTO `t_user_role` VALUES (73, 5, 2);
INSERT INTO `t_user_role` VALUES (74, 5, 3);
INSERT INTO `t_user_role` VALUES (75, 3, 1);

SET FOREIGN_KEY_CHECKS = 1;
