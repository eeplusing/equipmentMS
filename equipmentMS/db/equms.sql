/*
Navicat MySQL Data Transfer

Source Server         : sql
Source Server Version : 50096
Source Host           : localhost:3306
Source Database       : equms

Target Server Type    : MYSQL
Target Server Version : 50096
File Encoding         : 65001

Date: 2015-05-15 09:33:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for equms_datumformat_info
-- ----------------------------
DROP TABLE IF EXISTS `equms_datumformat_info`;
CREATE TABLE `equms_datumformat_info` (
  `datumformat_id` bigint(20) NOT NULL auto_increment,
  `datumformat_name` varchar(255) NOT NULL,
  `remark` varchar(255) default NULL,
  PRIMARY KEY  (`datumformat_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of equms_datumformat_info
-- ----------------------------
INSERT INTO `equms_datumformat_info` VALUES ('1', 'Word', 'Word文档');
INSERT INTO `equms_datumformat_info` VALUES ('2', 'Excel', 'Excel文档');
INSERT INTO `equms_datumformat_info` VALUES ('3', 'PPT', 'PPT文件');
INSERT INTO `equms_datumformat_info` VALUES ('4', 'PDF', 'PDF文件');
INSERT INTO `equms_datumformat_info` VALUES ('5', 'TXT', '文本文件');
INSERT INTO `equms_datumformat_info` VALUES ('6', 'IMAGE', '图片文件包括JPG/JPEG,\nPNG,GIF,BMP等常见格式');
INSERT INTO `equms_datumformat_info` VALUES ('8', 'THML', '网页文件');

-- ----------------------------
-- Table structure for equms_datumtype_info
-- ----------------------------
DROP TABLE IF EXISTS `equms_datumtype_info`;
CREATE TABLE `equms_datumtype_info` (
  `datumtype_id` bigint(20) NOT NULL auto_increment,
  `datumtype_name` varchar(255) NOT NULL,
  `remark` varchar(255) default NULL,
  PRIMARY KEY  (`datumtype_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of equms_datumtype_info
-- ----------------------------
INSERT INTO `equms_datumtype_info` VALUES ('1', '合同', '合同');
INSERT INTO `equms_datumtype_info` VALUES ('2', '外观', '外观');
INSERT INTO `equms_datumtype_info` VALUES ('3', '设计文档', '设计文档');
INSERT INTO `equms_datumtype_info` VALUES ('4', '使用手册', '使用手册');
INSERT INTO `equms_datumtype_info` VALUES ('5', '技术文档', '技术文档');
INSERT INTO `equms_datumtype_info` VALUES ('6', '维修文档', '维修文档');
INSERT INTO `equms_datumtype_info` VALUES ('7', '变更文档', '变更文档');
INSERT INTO `equms_datumtype_info` VALUES ('8', '其他', '其他');

-- ----------------------------
-- Table structure for equms_datum_info
-- ----------------------------
DROP TABLE IF EXISTS `equms_datum_info`;
CREATE TABLE `equms_datum_info` (
  `datum_id` bigint(20) NOT NULL auto_increment,
  `datum_name` varchar(255) NOT NULL,
  `datumtype_id` bigint(20) NOT NULL,
  `datumformat_id` bigint(20) NOT NULL,
  `datum_path` varchar(255) NOT NULL,
  PRIMARY KEY  (`datum_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of equms_datum_info
-- ----------------------------

-- ----------------------------
-- Table structure for equms_depart_info
-- ----------------------------
DROP TABLE IF EXISTS `equms_depart_info`;
CREATE TABLE `equms_depart_info` (
  `depart_id` bigint(20) NOT NULL auto_increment,
  `depart_name` varchar(255) NOT NULL,
  `parent_depart_id` bigint(20) NOT NULL,
  PRIMARY KEY  (`depart_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of equms_depart_info
-- ----------------------------
INSERT INTO `equms_depart_info` VALUES ('1', '生产部', '0');
INSERT INTO `equms_depart_info` VALUES ('2', '维修部', '0');
INSERT INTO `equms_depart_info` VALUES ('3', '质检科', '0');
INSERT INTO `equms_depart_info` VALUES ('4', '办公室', '0');
INSERT INTO `equms_depart_info` VALUES ('5', '生产一部', '1');
INSERT INTO `equms_depart_info` VALUES ('6', '生产二部', '1');
INSERT INTO `equms_depart_info` VALUES ('7', '生产三部', '1');
INSERT INTO `equms_depart_info` VALUES ('8', '生产四部', '1');
INSERT INTO `equms_depart_info` VALUES ('10', '生产五部', '3');

-- ----------------------------
-- Table structure for equms_equipment_datum
-- ----------------------------
DROP TABLE IF EXISTS `equms_equipment_datum`;
CREATE TABLE `equms_equipment_datum` (
  `equipment_id` bigint(20) NOT NULL,
  `datum_id` bigint(20) NOT NULL,
  PRIMARY KEY  (`equipment_id`,`datum_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of equms_equipment_datum
-- ----------------------------

-- ----------------------------
-- Table structure for equms_equipment_ident_info
-- ----------------------------
DROP TABLE IF EXISTS `equms_equipment_ident_info`;
CREATE TABLE `equms_equipment_ident_info` (
  `equipment_ident_id` bigint(20) NOT NULL auto_increment,
  `equipment_ident_name` varchar(255) NOT NULL,
  `equipment_ident_remark` varchar(255) default NULL,
  PRIMARY KEY  (`equipment_ident_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of equms_equipment_ident_info
-- ----------------------------
INSERT INTO `equms_equipment_ident_info` VALUES ('7', '重点设备', '重点设备');
INSERT INTO `equms_equipment_ident_info` VALUES ('8', '大型设备', '大型设备');
INSERT INTO `equms_equipment_ident_info` VALUES ('9', '一般设备', '一般设备');

-- ----------------------------
-- Table structure for equms_equipment_info
-- ----------------------------
DROP TABLE IF EXISTS `equms_equipment_info`;
CREATE TABLE `equms_equipment_info` (
  `equipment_id` bigint(20) NOT NULL auto_increment,
  `equipment_no` varchar(255) NOT NULL,
  `equipment_name` varchar(255) NOT NULL,
  `equipment_modle` varchar(255) default NULL,
  `type_id` bigint(20) NOT NULL,
  `equipment_ident_id` bigint(20) NOT NULL,
  `equipment_producer` varchar(255) default NULL,
  `equipment_produc_date` datetime default NULL,
  `equipment_supplier` varchar(255) default NULL,
  `equipment_buy_time` datetime default NULL,
  `equipment_buy_type` varchar(255) default NULL,
  `equipment_director` varchar(255) default NULL,
  `equipment_setplace` varchar(255) default NULL,
  `usestate_id` bigint(20) NOT NULL,
  `depart_id` bigint(20) default NULL,
  `equipment_operater` varchar(255) default NULL,
  `equipment_start_date` datetime default NULL,
  `equipment_use_years` int(11) default NULL,
  `equipment_monetary_amount` double NOT NULL,
  `equipment_last_examdate` datetime default NULL,
  `equipment_exam_person` varchar(255) default NULL,
  `equipment_exam_cycle` int(11) default NULL,
  `equipment_plan_examdate` datetime default NULL,
  `txt_field1` varchar(255) default NULL,
  `txt_field2` varchar(255) default NULL,
  `txt_field3` varchar(255) default NULL,
  `txt_field4` varchar(255) default NULL,
  `txt_field5` varchar(255) default NULL,
  `txt_field6` varchar(255) default NULL,
  `date_field1` datetime default NULL,
  `date_field2` datetime default NULL,
  `date_field3` datetime default NULL,
  `num_field1` int(11) default NULL,
  `num_field2` int(11) default NULL,
  `num_field3` int(11) default NULL,
  PRIMARY KEY  (`equipment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of equms_equipment_info
-- ----------------------------
INSERT INTO `equms_equipment_info` VALUES ('15', 'EQU_1256', 'ascsc', 'wadaw', '16', '7', 'dwadaw', '2015-05-12 00:00:00', 'dwadawd', '2015-05-12 00:00:00', 'awdwad', 'dwadwa', 'dwada', '4', '1', 'wdawd', '2015-05-12 00:00:00', '25', '147.36', '2015-05-12 00:00:00', 'dwadawdaw', '12', '2015-05-19 00:00:00', null, null, null, null, null, null, null, null, null, '0', '0', '0');

-- ----------------------------
-- Table structure for equms_equspare_info
-- ----------------------------
DROP TABLE IF EXISTS `equms_equspare_info`;
CREATE TABLE `equms_equspare_info` (
  `equipment_id` bigint(20) NOT NULL,
  `sparepart_id` bigint(20) NOT NULL,
  PRIMARY KEY  (`equipment_id`,`sparepart_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of equms_equspare_info
-- ----------------------------

-- ----------------------------
-- Table structure for equms_iodepot_info
-- ----------------------------
DROP TABLE IF EXISTS `equms_iodepot_info`;
CREATE TABLE `equms_iodepot_info` (
  `iodepot_id` bigint(20) NOT NULL auto_increment,
  `good_id` varchar(255) NOT NULL,
  `type_id` bigint(20) NOT NULL,
  `iodepot_opertype` int(11) NOT NULL,
  `iodepot_time` datetime NOT NULL,
  `iodepot_getter` varchar(255) default NULL,
  `iodepot_returner` varchar(255) default NULL,
  `iodepot_operator` varchar(255) NOT NULL,
  `iodepot_remark` varchar(255) default NULL,
  `retain_field1` varchar(255) default NULL,
  `retain_field2` varchar(255) default NULL,
  `retain_field3` varchar(255) default NULL,
  PRIMARY KEY  (`iodepot_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of equms_iodepot_info
-- ----------------------------

-- ----------------------------
-- Table structure for equms_model_info
-- ----------------------------
DROP TABLE IF EXISTS `equms_model_info`;
CREATE TABLE `equms_model_info` (
  `modle_id` bigint(20) NOT NULL auto_increment,
  `name` varchar(255) NOT NULL,
  `parent_id` bigint(20) NOT NULL,
  `url` varchar(255) NOT NULL,
  PRIMARY KEY  (`modle_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of equms_model_info
-- ----------------------------
INSERT INTO `equms_model_info` VALUES ('1', '系统管理', '0', '/');
INSERT INTO `equms_model_info` VALUES ('2', '设备管理', '0', '/');
INSERT INTO `equms_model_info` VALUES ('3', '备品备件', '0', '/');
INSERT INTO `equms_model_info` VALUES ('4', '库存管理', '0', '/');
INSERT INTO `equms_model_info` VALUES ('5', '文档管理', '0', '/');
INSERT INTO `equms_model_info` VALUES ('6', '账号管理', '1', '/user_manage');
INSERT INTO `equms_model_info` VALUES ('8', '角色管理', '1', '/role_manage');
INSERT INTO `equms_model_info` VALUES ('9', '权限管理', '1', '/right_manage');
INSERT INTO `equms_model_info` VALUES ('10', '部门管理', '1', '/depart_manage');
INSERT INTO `equms_model_info` VALUES ('11', '系统公告', '1', '/notice_manage');
INSERT INTO `equms_model_info` VALUES ('12', '系统日志', '1', '/operlog_manage');
INSERT INTO `equms_model_info` VALUES ('13', '设备类型', '2', '/type_manage');
INSERT INTO `equms_model_info` VALUES ('14', '设备标识', '2', '/ident_manage');
INSERT INTO `equms_model_info` VALUES ('15', '基本信息', '2', '/equ_manage');
INSERT INTO `equms_model_info` VALUES ('16', '使用状态', '2', '/state_manage');
INSERT INTO `equms_model_info` VALUES ('17', '基本信息', '3', '/spart_manage');
INSERT INTO `equms_model_info` VALUES ('18', '库存清单', '4', '/stock_bill');
INSERT INTO `equms_model_info` VALUES ('19', '库存预警', '4', '/stock_alert');
INSERT INTO `equms_model_info` VALUES ('20', '文档类型', '5', '/datumType_manage');
INSERT INTO `equms_model_info` VALUES ('21', '文档格式', '5', '/datumFormat_manage');
INSERT INTO `equms_model_info` VALUES ('22', '文档信息', '5', '/datum_manage');

-- ----------------------------
-- Table structure for equms_notice_info
-- ----------------------------
DROP TABLE IF EXISTS `equms_notice_info`;
CREATE TABLE `equms_notice_info` (
  `notice_id` bigint(20) NOT NULL auto_increment,
  `title` varchar(255) NOT NULL,
  `content` varchar(255) NOT NULL,
  `notice_time` datetime NOT NULL,
  `publisher` varchar(255) NOT NULL,
  PRIMARY KEY  (`notice_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of equms_notice_info
-- ----------------------------

-- ----------------------------
-- Table structure for equms_operlog_info
-- ----------------------------
DROP TABLE IF EXISTS `equms_operlog_info`;
CREATE TABLE `equms_operlog_info` (
  `operlog_id` bigint(20) NOT NULL auto_increment,
  `operlog_type` int(11) NOT NULL,
  `operlog_content` varchar(255) NOT NULL,
  `operlog_time` datetime NOT NULL,
  `operator` varchar(255) NOT NULL,
  `operlog_remark` varchar(255) default NULL,
  PRIMARY KEY  (`operlog_id`)
) ENGINE=InnoDB AUTO_INCREMENT=950 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of equms_operlog_info
-- ----------------------------
INSERT INTO `equms_operlog_info` VALUES ('829', '1', '登录系统', '2015-05-12 16:24:04', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('830', '1', '登录系统', '2015-05-12 17:45:50', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('831', '1', '登录系统', '2015-05-12 17:48:44', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('832', '1', '登录系统', '2015-05-12 17:57:40', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('833', '0', '添加设备：EQU_1256|ascsc', '2015-05-12 18:02:00', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('834', '1', '登录系统', '2015-05-12 18:11:24', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('835', '1', '登录系统', '2015-05-12 18:24:18', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('836', '1', '登录系统', '2015-05-12 18:30:09', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('837', '1', '登录系统', '2015-05-12 18:32:09', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('838', '1', '登录系统', '2015-05-12 18:33:25', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('839', '1', '登录系统', '2015-05-12 18:40:16', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('840', '1', '登录系统', '2015-05-12 18:43:50', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('841', '1', '登录系统', '2015-05-12 19:35:47', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('842', '1', '登录系统', '2015-05-12 19:36:44', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('843', '1', '登录系统', '2015-05-12 19:37:35', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('844', '1', '登录系统', '2015-05-12 19:46:03', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('845', '1', '登录系统', '2015-05-12 19:46:24', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('846', '1', '登录系统', '2015-05-12 19:46:33', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('847', '1', '登录系统', '2015-05-12 19:47:08', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('848', '1', '登录系统', '2015-05-12 19:48:02', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('849', '1', '登录系统', '2015-05-12 19:48:23', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('850', '1', '登录系统', '2015-05-12 19:48:34', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('851', '1', '登录系统', '2015-05-12 19:48:40', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('852', '1', '登录系统', '2015-05-12 19:48:45', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('853', '1', '登录系统', '2015-05-12 19:48:58', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('854', '1', '登录系统', '2015-05-12 19:49:20', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('855', '1', '登录系统', '2015-05-12 19:49:34', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('856', '1', '登录系统', '2015-05-12 19:50:10', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('857', '1', '登录系统', '2015-05-12 19:51:46', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('858', '1', '登录系统', '2015-05-12 19:51:58', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('859', '1', '登录系统', '2015-05-12 19:52:15', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('860', '1', '登录系统', '2015-05-12 19:55:58', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('861', '1', '登录系统', '2015-05-12 19:56:41', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('862', '1', '登录系统', '2015-05-12 19:57:23', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('863', '1', '登录系统', '2015-05-12 19:59:03', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('864', '1', '登录系统', '2015-05-12 20:01:23', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('865', '1', '登录系统', '2015-05-12 20:02:30', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('866', '1', '登录系统', '2015-05-13 09:34:09', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('867', '1', '登录系统', '2015-05-13 09:45:50', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('868', '1', '登录系统', '2015-05-13 09:46:08', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('869', '1', '登录系统', '2015-05-13 10:00:33', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('870', '1', '登录系统', '2015-05-13 10:07:10', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('871', '1', '登录系统', '2015-05-13 18:36:47', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('872', '1', '登录系统', '2015-05-13 18:38:22', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('873', '1', '登录系统', '2015-05-13 18:38:38', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('874', '1', '登录系统', '2015-05-13 18:39:25', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('875', '1', '登录系统', '2015-05-13 18:39:54', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('876', '1', '登录系统', '2015-05-13 18:41:10', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('877', '1', '登录系统', '2015-05-13 18:41:26', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('878', '1', '登录系统', '2015-05-13 18:44:05', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('879', '1', '登录系统', '2015-05-14 09:09:05', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('880', '1', '登录系统', '2015-05-14 09:11:59', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('881', '1', '登录系统', '2015-05-14 09:17:06', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('882', '1', '登录系统', '2015-05-14 09:18:31', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('883', '1', '登录系统', '2015-05-14 09:19:34', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('884', '1', '登录系统', '2015-05-14 09:20:13', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('885', '1', '登录系统', '2015-05-14 09:20:26', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('886', '1', '登录系统', '2015-05-14 09:30:33', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('887', '1', '登录系统', '2015-05-14 09:33:34', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('888', '1', '登录系统', '2015-05-14 09:34:04', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('889', '1', '登录系统', '2015-05-14 09:34:16', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('890', '1', '登录系统', '2015-05-14 09:34:32', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('891', '1', '登录系统', '2015-05-14 09:35:38', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('892', '1', '登录系统', '2015-05-14 09:36:41', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('893', '1', '登录系统', '2015-05-14 09:36:59', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('894', '1', '登录系统', '2015-05-14 09:37:42', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('895', '1', '登录系统', '2015-05-14 09:38:19', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('896', '1', '登录系统', '2015-05-14 09:38:59', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('897', '1', '登录系统', '2015-05-14 09:39:20', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('898', '1', '登录系统', '2015-05-14 09:39:39', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('899', '1', '登录系统', '2015-05-14 09:40:08', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('900', '1', '登录系统', '2015-05-14 09:40:26', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('901', '1', '登录系统', '2015-05-14 09:40:36', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('902', '1', '登录系统', '2015-05-14 09:41:34', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('903', '1', '登录系统', '2015-05-14 09:41:54', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('904', '1', '登录系统', '2015-05-14 09:42:31', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('905', '1', '登录系统', '2015-05-14 09:42:46', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('906', '1', '登录系统', '2015-05-14 09:43:02', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('907', '1', '登录系统', '2015-05-14 09:43:38', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('908', '1', '登录系统', '2015-05-14 09:43:56', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('909', '1', '登录系统', '2015-05-14 09:44:06', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('910', '1', '登录系统', '2015-05-14 09:44:16', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('911', '1', '登录系统', '2015-05-14 09:45:08', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('912', '1', '登录系统', '2015-05-14 09:45:26', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('913', '1', '登录系统', '2015-05-14 09:47:15', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('914', '7', '修改部门信息：9|生产五部', '2015-05-14 09:52:50', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('915', '7', '修改部门信息：9|生产五部', '2015-05-14 09:53:18', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('916', '7', '删除部门信息：9|生产五部', '2015-05-14 09:53:54', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('917', '7', '添加部门：11|qeq2eq2e', '2015-05-14 09:55:13', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('918', '7', '修改部门信息：11|qeq2eq2e1231', '2015-05-14 09:55:28', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('919', '7', '修改部门信息：11|qeq2eq2e12311', '2015-05-14 09:55:39', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('920', '1', '登录系统', '2015-05-14 10:02:35', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('921', '7', '删除部门信息：11|qeq2eq2e12311', '2015-05-14 10:02:42', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('922', '7', '删除部门信息：12|qeq2eq2e12311', '2015-05-14 10:03:25', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('923', '7', '删除部门信息：13|qeq2eq2e12311', '2015-05-14 10:03:49', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('924', '1', '登录系统', '2015-05-14 10:05:59', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('925', '7', '删除部门信息：14|qeq2eq2e12311', '2015-05-14 10:06:07', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('926', '7', '修改部门信息：10|生产五部', '2015-05-14 10:06:29', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('927', '7', '修改部门信息：10|生产五部', '2015-05-14 10:06:58', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('928', '7', '修改部门信息：10|生产五部', '2015-05-14 10:10:22', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('929', '1', '登录系统', '2015-05-14 10:17:54', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('930', '1', '登录系统', '2015-05-14 10:18:57', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('931', '1', '登录系统', '2015-05-14 10:23:43', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('932', '1', '登录系统', '2015-05-14 10:26:40', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('933', '1', '登录系统', '2015-05-14 10:33:17', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('934', '1', '登录系统', '2015-05-14 10:33:58', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('935', '1', '登录系统', '2015-05-14 10:35:05', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('936', '1', '登录系统', '2015-05-14 10:36:44', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('937', '1', '登录系统', '2015-05-14 10:38:40', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('938', '2', '退出系统', '2015-05-14 10:40:01', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('939', '1', '登录系统', '2015-05-14 11:46:24', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('940', '11', '添加账号：test', '2015-05-14 11:46:41', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('941', '2', '退出系统', '2015-05-14 11:46:46', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('942', '1', '登录系统', '2015-05-14 11:47:44', 'test', null);
INSERT INTO `equms_operlog_info` VALUES ('943', '2', '退出系统', '2015-05-14 11:48:31', 'test', null);
INSERT INTO `equms_operlog_info` VALUES ('944', '1', '登录系统', '2015-05-15 09:19:08', 'test', null);
INSERT INTO `equms_operlog_info` VALUES ('945', '2', '退出系统', '2015-05-15 09:19:14', 'test', null);
INSERT INTO `equms_operlog_info` VALUES ('946', '1', '登录系统', '2015-05-15 09:19:28', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('947', '2', '退出系统', '2015-05-15 09:19:39', 'admin', null);
INSERT INTO `equms_operlog_info` VALUES ('948', '1', '登录系统', '2015-05-15 09:19:46', 'test', null);
INSERT INTO `equms_operlog_info` VALUES ('949', '2', '退出系统', '2015-05-15 09:21:23', 'test', null);

-- ----------------------------
-- Table structure for equms_right_info
-- ----------------------------
DROP TABLE IF EXISTS `equms_right_info`;
CREATE TABLE `equms_right_info` (
  `right_id` bigint(20) NOT NULL auto_increment,
  `name` varchar(255) NOT NULL,
  `function_name` varchar(255) NOT NULL,
  `modle_id` bigint(20) NOT NULL,
  `remark` varchar(255) default NULL,
  PRIMARY KEY  (`right_id`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of equms_right_info
-- ----------------------------
INSERT INTO `equms_right_info` VALUES ('1', '增加类型', 'type_save', '13', '增加设备类型');
INSERT INTO `equms_right_info` VALUES ('2', '删除类型', 'type_delete', '13', '删除设备类型');
INSERT INTO `equms_right_info` VALUES ('3', '修改类型', 'type_modify', '13', '修改设备类型信息');
INSERT INTO `equms_right_info` VALUES ('4', '查看类型', 'type_manage|type_list', '13', '浏览及按条件查询设备类型');
INSERT INTO `equms_right_info` VALUES ('5', '增加标识', 'ident_save', '14', '增加设备标识');
INSERT INTO `equms_right_info` VALUES ('6', '删除标识', 'ident_delete', '14', '删除设备标识');
INSERT INTO `equms_right_info` VALUES ('7', '修改标识', 'ident_modify', '14', '修改设备标识信息');
INSERT INTO `equms_right_info` VALUES ('8', '查看标识', 'ident_manage|ident_list', '14', '浏览设备标识信息');
INSERT INTO `equms_right_info` VALUES ('9', '增加设备', 'equ_openSaveWindow|equ_createDoc|equ_save|equ_print', '15', '新增设备');
INSERT INTO `equms_right_info` VALUES ('10', '删除设备', 'equ_delete', '15', '删除设备');
INSERT INTO `equms_right_info` VALUES ('11', '修改设备', 'equ_openModifyWindow|equ_modify', '15', '修改设备信息');
INSERT INTO `equms_right_info` VALUES ('12', '查看设备', 'equ_manage|equ_list', '15', '浏览及按条件查询设备信息');
INSERT INTO `equms_right_info` VALUES ('13', '设备关联备件', 'spart_equList|equ_open|equ_spart|spart_list|equ_spartDelete', '15', '操作设备关联备品，包括查看，添加，删除');
INSERT INTO `equms_right_info` VALUES ('14', '设备关联文档', 'datum_equList|equ_open|equ_datum|datum_list|equ_datumDelete|datum_download', '15', '操作设备关联文档(不包括图片格式)，包括查看，添加，删除，下载');
INSERT INTO `equms_right_info` VALUES ('15', '设备关联图片', 'datum_image|datum_showImage', '15', '查看设备关联图片');
INSERT INTO `equms_right_info` VALUES ('16', '增加状态', 'state_save', '16', '增加设备使用状态');
INSERT INTO `equms_right_info` VALUES ('17', '删除状态', 'state_delete', '16', '删除设备使用状态');
INSERT INTO `equms_right_info` VALUES ('18', '修改状态', 'state_modify', '16', '修改设备使用状态信息');
INSERT INTO `equms_right_info` VALUES ('19', '查看状态', 'state_manage|state_list', '16', '浏览设备使用状态信息');
INSERT INTO `equms_right_info` VALUES ('20', '增加备品', 'spart_openWindow|equ_createDoc|spart_save|equ_print', '17', '增加备品备件');
INSERT INTO `equms_right_info` VALUES ('21', '删除备品', 'spart_delete', '17', '删除备品备件');
INSERT INTO `equms_right_info` VALUES ('22', '修改备品', 'spart_openWindow|spart_modify', '17', '修改备品备件信息');
INSERT INTO `equms_right_info` VALUES ('23', '查看备品', 'spart_manage|spart_list', '17', '浏览及按条件查询备品备件');
INSERT INTO `equms_right_info` VALUES ('24', '备品出库', 'spart_openWindow|spart_outStock|spart_list', '17', '备品出库操作');
INSERT INTO `equms_right_info` VALUES ('25', '备品入库', 'spart_openWindow|spart_inStock|spart_list', '17', '备品入库操作');
INSERT INTO `equms_right_info` VALUES ('26', '查看库存', 'stock_bill|stock_list|spart_list', '18', '查看库存清单');
INSERT INTO `equms_right_info` VALUES ('27', '设置库存上下限', 'stock_modify', '18', '设置库存上下限');
INSERT INTO `equms_right_info` VALUES ('28', '查看库存预警', 'stock_alert|stock_list|spart_list', '19', '查看当前库存预警信息');
INSERT INTO `equms_right_info` VALUES ('29', '增加文档类型', 'datumType_save', '20', '新增文档类型');
INSERT INTO `equms_right_info` VALUES ('30', '删除文档类型', 'datumType_modify', '20', '删除文档类型');
INSERT INTO `equms_right_info` VALUES ('31', '修改文档类型', 'datumType_delete', '20', '修改文档类型');
INSERT INTO `equms_right_info` VALUES ('32', '查看文档类型', 'datumType_manage|datumType_list|', '20', '浏览文档类型');
INSERT INTO `equms_right_info` VALUES ('33', '增加文档格式', 'datumFormat_save', '21', '新增文档格式');
INSERT INTO `equms_right_info` VALUES ('34', '删除文档格式', 'datumFormat_delete', '21', '删除文档格式');
INSERT INTO `equms_right_info` VALUES ('35', '修改文档格式', 'datumFormat_modify', '21', '修改文档格式');
INSERT INTO `equms_right_info` VALUES ('36', '查看文档格式', 'datumFormat_manage|datumFormat_list', '21', '浏览文档格式');
INSERT INTO `equms_right_info` VALUES ('37', '文档上传', 'datum_uploadWindow|equ_list|datum_load|datum_save', '22', '上传文档，包括关联设备');
INSERT INTO `equms_right_info` VALUES ('38', '文档下载', 'datum_download', '22', '下载文档');
INSERT INTO `equms_right_info` VALUES ('39', '删除文档', 'datum_delete', '22', '删除文档');
INSERT INTO `equms_right_info` VALUES ('40', '查看用户', 'user_manage|user_list', '6', '浏览，搜索用户信息');
INSERT INTO `equms_right_info` VALUES ('41', '增加用户', 'user_save', '6', '添加用户信息');
INSERT INTO `equms_right_info` VALUES ('42', '删除用户', 'user_delete', '6', '删除用户信息');
INSERT INTO `equms_right_info` VALUES ('43', '分配角色', 'user_modifyRole', '6', '为账号分配角色');
INSERT INTO `equms_right_info` VALUES ('44', '查看角色', 'role_manage|role_list', '8', '浏览角色信息');
INSERT INTO `equms_right_info` VALUES ('45', '添加角色', 'role_save', '8', '添加角色信息');
INSERT INTO `equms_right_info` VALUES ('46', '删除角色', 'role_delete', '8', '删除角色信息');
INSERT INTO `equms_right_info` VALUES ('47', '编辑角色', 'role_modify', '8', '编辑角色信息');
INSERT INTO `equms_right_info` VALUES ('48', '查看部门', 'depart_manage|depart_list', '10', '浏览部门信息');
INSERT INTO `equms_right_info` VALUES ('49', '添加部门', 'depart_save', '10', '添加部门信息');
INSERT INTO `equms_right_info` VALUES ('50', '删除部门', 'depart_delete', '10', '删除部门信息');
INSERT INTO `equms_right_info` VALUES ('51', '编辑部门', 'depart_modify', '10', '编辑部门');
INSERT INTO `equms_right_info` VALUES ('52', '查看公告', 'notice_manage|notice_list', '11', '浏览，按条件搜索公告信息');
INSERT INTO `equms_right_info` VALUES ('53', '添加公告', 'notice_openSaveWindow|notice_save', '11', '添加公告信息');
INSERT INTO `equms_right_info` VALUES ('54', '删除公告', 'notice_delete', '11', '删除公告信息');
INSERT INTO `equms_right_info` VALUES ('55', '打开公告', 'notice_openModifyWindow', '11', '查看公告内容');
INSERT INTO `equms_right_info` VALUES ('56', '查看日志', 'operlog_manage|operlog_list', '12', '浏览,按条件搜索日志信息');
INSERT INTO `equms_right_info` VALUES ('57', '授权', 'right_getRightTree|right_save', '8', '编辑某个角色的权限');
INSERT INTO `equms_right_info` VALUES ('58', '权限浏览', 'right_manage|right_list', '9', '查看系统中所有权限');

-- ----------------------------
-- Table structure for equms_role_info
-- ----------------------------
DROP TABLE IF EXISTS `equms_role_info`;
CREATE TABLE `equms_role_info` (
  `role_id` bigint(20) NOT NULL auto_increment,
  `name` varchar(255) NOT NULL,
  `role_describe` varchar(255) default NULL,
  PRIMARY KEY  (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of equms_role_info
-- ----------------------------
INSERT INTO `equms_role_info` VALUES ('1', '超级管理员', '超级管理员');
INSERT INTO `equms_role_info` VALUES ('2', '系统管理员', '系统管理员');

-- ----------------------------
-- Table structure for equms_role_model
-- ----------------------------
DROP TABLE IF EXISTS `equms_role_model`;
CREATE TABLE `equms_role_model` (
  `role_id` bigint(20) NOT NULL,
  `modle_id` bigint(20) NOT NULL,
  PRIMARY KEY  (`role_id`,`modle_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of equms_role_model
-- ----------------------------
INSERT INTO `equms_role_model` VALUES ('1', '1');
INSERT INTO `equms_role_model` VALUES ('1', '2');
INSERT INTO `equms_role_model` VALUES ('1', '3');
INSERT INTO `equms_role_model` VALUES ('1', '4');
INSERT INTO `equms_role_model` VALUES ('1', '5');
INSERT INTO `equms_role_model` VALUES ('1', '6');
INSERT INTO `equms_role_model` VALUES ('1', '8');
INSERT INTO `equms_role_model` VALUES ('1', '9');
INSERT INTO `equms_role_model` VALUES ('1', '10');
INSERT INTO `equms_role_model` VALUES ('1', '11');
INSERT INTO `equms_role_model` VALUES ('1', '12');
INSERT INTO `equms_role_model` VALUES ('1', '13');
INSERT INTO `equms_role_model` VALUES ('1', '14');
INSERT INTO `equms_role_model` VALUES ('1', '15');
INSERT INTO `equms_role_model` VALUES ('1', '16');
INSERT INTO `equms_role_model` VALUES ('1', '17');
INSERT INTO `equms_role_model` VALUES ('1', '18');
INSERT INTO `equms_role_model` VALUES ('1', '19');
INSERT INTO `equms_role_model` VALUES ('1', '20');
INSERT INTO `equms_role_model` VALUES ('1', '21');
INSERT INTO `equms_role_model` VALUES ('1', '22');
INSERT INTO `equms_role_model` VALUES ('2', '1');
INSERT INTO `equms_role_model` VALUES ('2', '6');
INSERT INTO `equms_role_model` VALUES ('2', '8');
INSERT INTO `equms_role_model` VALUES ('2', '10');
INSERT INTO `equms_role_model` VALUES ('2', '11');

-- ----------------------------
-- Table structure for equms_role_right
-- ----------------------------
DROP TABLE IF EXISTS `equms_role_right`;
CREATE TABLE `equms_role_right` (
  `role_id` bigint(20) NOT NULL,
  `right_id` bigint(20) NOT NULL,
  PRIMARY KEY  (`role_id`,`right_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of equms_role_right
-- ----------------------------
INSERT INTO `equms_role_right` VALUES ('1', '1');
INSERT INTO `equms_role_right` VALUES ('1', '2');
INSERT INTO `equms_role_right` VALUES ('1', '3');
INSERT INTO `equms_role_right` VALUES ('1', '4');
INSERT INTO `equms_role_right` VALUES ('1', '5');
INSERT INTO `equms_role_right` VALUES ('1', '6');
INSERT INTO `equms_role_right` VALUES ('1', '7');
INSERT INTO `equms_role_right` VALUES ('1', '8');
INSERT INTO `equms_role_right` VALUES ('1', '9');
INSERT INTO `equms_role_right` VALUES ('1', '10');
INSERT INTO `equms_role_right` VALUES ('1', '11');
INSERT INTO `equms_role_right` VALUES ('1', '12');
INSERT INTO `equms_role_right` VALUES ('1', '13');
INSERT INTO `equms_role_right` VALUES ('1', '14');
INSERT INTO `equms_role_right` VALUES ('1', '15');
INSERT INTO `equms_role_right` VALUES ('1', '16');
INSERT INTO `equms_role_right` VALUES ('1', '17');
INSERT INTO `equms_role_right` VALUES ('1', '18');
INSERT INTO `equms_role_right` VALUES ('1', '19');
INSERT INTO `equms_role_right` VALUES ('1', '20');
INSERT INTO `equms_role_right` VALUES ('1', '21');
INSERT INTO `equms_role_right` VALUES ('1', '22');
INSERT INTO `equms_role_right` VALUES ('1', '23');
INSERT INTO `equms_role_right` VALUES ('1', '24');
INSERT INTO `equms_role_right` VALUES ('1', '25');
INSERT INTO `equms_role_right` VALUES ('1', '26');
INSERT INTO `equms_role_right` VALUES ('1', '27');
INSERT INTO `equms_role_right` VALUES ('1', '28');
INSERT INTO `equms_role_right` VALUES ('1', '29');
INSERT INTO `equms_role_right` VALUES ('1', '30');
INSERT INTO `equms_role_right` VALUES ('1', '31');
INSERT INTO `equms_role_right` VALUES ('1', '32');
INSERT INTO `equms_role_right` VALUES ('1', '33');
INSERT INTO `equms_role_right` VALUES ('1', '34');
INSERT INTO `equms_role_right` VALUES ('1', '35');
INSERT INTO `equms_role_right` VALUES ('1', '36');
INSERT INTO `equms_role_right` VALUES ('1', '37');
INSERT INTO `equms_role_right` VALUES ('1', '38');
INSERT INTO `equms_role_right` VALUES ('1', '39');
INSERT INTO `equms_role_right` VALUES ('1', '40');
INSERT INTO `equms_role_right` VALUES ('1', '41');
INSERT INTO `equms_role_right` VALUES ('1', '42');
INSERT INTO `equms_role_right` VALUES ('1', '43');
INSERT INTO `equms_role_right` VALUES ('1', '44');
INSERT INTO `equms_role_right` VALUES ('1', '45');
INSERT INTO `equms_role_right` VALUES ('1', '46');
INSERT INTO `equms_role_right` VALUES ('1', '47');
INSERT INTO `equms_role_right` VALUES ('1', '48');
INSERT INTO `equms_role_right` VALUES ('1', '49');
INSERT INTO `equms_role_right` VALUES ('1', '50');
INSERT INTO `equms_role_right` VALUES ('1', '51');
INSERT INTO `equms_role_right` VALUES ('1', '52');
INSERT INTO `equms_role_right` VALUES ('1', '53');
INSERT INTO `equms_role_right` VALUES ('1', '54');
INSERT INTO `equms_role_right` VALUES ('1', '55');
INSERT INTO `equms_role_right` VALUES ('1', '56');
INSERT INTO `equms_role_right` VALUES ('1', '57');
INSERT INTO `equms_role_right` VALUES ('1', '58');
INSERT INTO `equms_role_right` VALUES ('2', '40');
INSERT INTO `equms_role_right` VALUES ('2', '41');
INSERT INTO `equms_role_right` VALUES ('2', '42');
INSERT INTO `equms_role_right` VALUES ('2', '43');
INSERT INTO `equms_role_right` VALUES ('2', '44');
INSERT INTO `equms_role_right` VALUES ('2', '48');
INSERT INTO `equms_role_right` VALUES ('2', '52');

-- ----------------------------
-- Table structure for equms_sparepart_info
-- ----------------------------
DROP TABLE IF EXISTS `equms_sparepart_info`;
CREATE TABLE `equms_sparepart_info` (
  `sparepart_id` bigint(20) NOT NULL auto_increment,
  `sparepart_no` varchar(255) NOT NULL,
  `sparepart_name` varchar(255) NOT NULL,
  `sparepart_modle` varchar(255) default NULL,
  `type_id` bigint(20) NOT NULL,
  `sparepart_unit` varchar(255) NOT NULL,
  `sparepart_price` double NOT NULL,
  `sparepart_producer` varchar(255) default NULL,
  `sparepart_supplier` varchar(255) default NULL,
  `sparepart_setplace` varchar(255) default NULL,
  `sparepart_start_date` datetime default NULL,
  `sparepart_usemonths` int(11) default NULL,
  `sparepart_remark` varchar(255) default NULL,
  PRIMARY KEY  (`sparepart_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of equms_sparepart_info
-- ----------------------------

-- ----------------------------
-- Table structure for equms_stock_info
-- ----------------------------
DROP TABLE IF EXISTS `equms_stock_info`;
CREATE TABLE `equms_stock_info` (
  `stock_id` bigint(20) NOT NULL auto_increment,
  `good_id` varchar(255) NOT NULL,
  `good_name` varchar(255) NOT NULL,
  `type_id` bigint(20) NOT NULL,
  `stock_place` varchar(255) default NULL,
  `stock_amount` int(11) NOT NULL,
  `stock_minamount` int(11) default NULL,
  `stock_maxamount` int(11) default NULL,
  PRIMARY KEY  (`stock_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of equms_stock_info
-- ----------------------------

-- ----------------------------
-- Table structure for equms_type_info
-- ----------------------------
DROP TABLE IF EXISTS `equms_type_info`;
CREATE TABLE `equms_type_info` (
  `type_id` bigint(20) NOT NULL auto_increment,
  `type_name` varchar(255) NOT NULL,
  `parent_type_id` bigint(20) NOT NULL,
  PRIMARY KEY  (`type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of equms_type_info
-- ----------------------------
INSERT INTO `equms_type_info` VALUES ('16', '特种设备', '1');
INSERT INTO `equms_type_info` VALUES ('17', '锅炉', '16');
INSERT INTO `equms_type_info` VALUES ('18', '压力容器', '16');
INSERT INTO `equms_type_info` VALUES ('19', '机械设备', '0');
INSERT INTO `equms_type_info` VALUES ('20', '磨床', '19');
INSERT INTO `equms_type_info` VALUES ('21', '车床', '19');
INSERT INTO `equms_type_info` VALUES ('22', '铣床', '19');
INSERT INTO `equms_type_info` VALUES ('23', '动力设备', '0');
INSERT INTO `equms_type_info` VALUES ('24', '传导设备', '0');
INSERT INTO `equms_type_info` VALUES ('25', '运输设备', '0');
INSERT INTO `equms_type_info` VALUES ('26', '工具仪器', '0');
INSERT INTO `equms_type_info` VALUES ('27', '办公设备', '0');
INSERT INTO `equms_type_info` VALUES ('28', '其他设备', '0');

-- ----------------------------
-- Table structure for equms_user_info
-- ----------------------------
DROP TABLE IF EXISTS `equms_user_info`;
CREATE TABLE `equms_user_info` (
  `user_id` bigint(20) NOT NULL auto_increment,
  `login_account` varchar(255) NOT NULL,
  `login_password` varchar(255) default NULL,
  `user_name` varchar(255) default NULL,
  `user_phone` varchar(255) default NULL,
  `is_activated` int(11) NOT NULL,
  `email` varchar(255) default NULL,
  `role_id` bigint(20) default NULL,
  PRIMARY KEY  (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of equms_user_info
-- ----------------------------
INSERT INTO `equms_user_info` VALUES ('1', 'admin', '5be4b8cd1f6c93cbb63e97f98108279d', '超级管理员', '00000000000', '1', 'system_manager@equms.com', '1');
INSERT INTO `equms_user_info` VALUES ('2', 'test', 'f791e7974f5979e21a4168269c8e8c63', '我是谁', '15269696868', '1', '123@163.com', '1');

-- ----------------------------
-- Table structure for equms_usestate_info
-- ----------------------------
DROP TABLE IF EXISTS `equms_usestate_info`;
CREATE TABLE `equms_usestate_info` (
  `usestate_id` bigint(20) NOT NULL auto_increment,
  `usestate_name` varchar(255) NOT NULL,
  `usestate_remark` varchar(255) default NULL,
  PRIMARY KEY  (`usestate_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of equms_usestate_info
-- ----------------------------
INSERT INTO `equms_usestate_info` VALUES ('4', '在用', '设备正在使用中');
INSERT INTO `equms_usestate_info` VALUES ('5', '封存', '设备尚未开始使用，存放在仓库中');
INSERT INTO `equms_usestate_info` VALUES ('6', '出租', '设备外租给其他单位');
INSERT INTO `equms_usestate_info` VALUES ('7', '停用', '设备因已到使用期限或其他原因无法继续使用');
INSERT INTO `equms_usestate_info` VALUES ('8', '报废', '设备已经经过审核做了报废处理');
