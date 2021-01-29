/*
 Navicat Premium Data Transfer

 Source Server         : lyg-v2-test
 Source Server Type    : MySQL
 Source Server Version : 50731
 Source Host           : 172.29.1.61:3306
 Source Schema         : iflow

 Target Server Type    : MySQL
 Target Server Version : 50731
 File Encoding         : 65001

 Date: 29/01/2021 11:09:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for flw_his_process_history
-- ----------------------------
DROP TABLE IF EXISTS `flw_his_process_history`;
CREATE TABLE `flw_his_process_history`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `proc_def_id` bigint(20) NULL DEFAULT NULL,
  `proc_def_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `proc_inst_id` bigint(20) NULL DEFAULT NULL,
  `proc_node_id` bigint(20) NULL DEFAULT NULL,
  `proc_node_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `business_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `variables` varchar(4000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `task_id` bigint(20) NULL DEFAULT NULL,
  `assignee_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `assignee_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `group_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `remark` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `creation_date` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_update_date` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 53 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for flw_re_process_define
-- ----------------------------
DROP TABLE IF EXISTS `flw_re_process_define`;
CREATE TABLE `flw_re_process_define`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `proc_def_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '流程唯一标识',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '流程用于展示的名字',
  `enable` tinyint(1) NOT NULL DEFAULT 1,
  `version` bigint(20) NOT NULL DEFAULT 1,
  `creation_date` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_update_date` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for flw_re_process_node
-- ----------------------------
DROP TABLE IF EXISTS `flw_re_process_node`;
CREATE TABLE `flw_re_process_node`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '展示名',
  `version` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `proc_def_id` bigint(20) NULL DEFAULT NULL,
  `proc_def_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `pre_proc_node_id` bigint(20) NULL DEFAULT NULL COMMENT '上一个节点的id，为空则代表无相应节点',
  `next_proc_node_id` bigint(20) NULL DEFAULT NULL COMMENT '下一个节点的id，为空则代表无相应节点',
  `assignee_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '分配人的key，可为主键',
  `group_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '分配组的key，可为角色',
  `delegate_class` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `creation_date` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_update_date` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `enable` tinyint(1) NOT NULL DEFAULT 1,
  `is_start` tinyint(1) NULL DEFAULT NULL,
  `is_end` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for flw_ru_execution
-- ----------------------------
DROP TABLE IF EXISTS `flw_ru_execution`;
CREATE TABLE `flw_ru_execution`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `proc_def_id` bigint(20) NULL DEFAULT NULL,
  `proc_def_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `proc_inst_id` bigint(20) NULL DEFAULT NULL,
  `business_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '关联业务的key',
  `current_proc_node_id` bigint(20) NULL DEFAULT NULL COMMENT '当前流程节点id',
  `pre_proc_node_id` bigint(20) NULL DEFAULT NULL COMMENT '上一个节点的id，为空则代表无相应节点',
  `next_proc_node_id` bigint(20) NULL DEFAULT NULL COMMENT '下一个节点的id，为空则代表无相应节点',
  `assignee_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '分配人的key，可为主键',
  `group_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '分配组的key，可为角色',
  `variables` varchar(4000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '流程流转过程中的变量，HashMap转出json字符串形式保存',
  `delegate_class` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '节点处理操作后的委托处理类，全类名',
  `creation_date` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_update_date` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for flw_ru_process_instance
-- ----------------------------
DROP TABLE IF EXISTS `flw_ru_process_instance`;
CREATE TABLE `flw_ru_process_instance`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `proc_def_id` bigint(20) NULL DEFAULT NULL,
  `proc_def_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `business_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '运行中run；已结束end；已终止stop',
  `current_proc_node_id` bigint(20) NULL DEFAULT NULL COMMENT '当前流程节点id',
  `creation_date` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_update_date` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for flw_ru_task
-- ----------------------------
DROP TABLE IF EXISTS `flw_ru_task`;
CREATE TABLE `flw_ru_task`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `proc_def_id` bigint(20) NOT NULL,
  `proc_def_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `proc_inst_id` bigint(20) NOT NULL,
  `business_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `variables` varchar(4000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `current_proc_node_id` bigint(20) NULL DEFAULT NULL COMMENT '当前流程节点id',
  `assignee_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `group_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `creation_date` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_update_date` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
