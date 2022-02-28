/*
 Navicat MySQL Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : wc

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 01/03/2022 02:00:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for circle
-- ----------------------------
DROP TABLE IF EXISTS `circle`;
CREATE TABLE `circle`  (
  `circle_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '朋友圈主键',
  `user_id` int(0) NULL DEFAULT NULL COMMENT '用户主键',
  `text` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文本',
  `pic` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '新建时间',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`circle_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of circle
-- ----------------------------
INSERT INTO `circle` VALUES (2, 5, '这是第一个朋友圈，测试一下，啦啦啦啦，这里有椰树，猫和海的图片。', '126,127,128', '2022-02-28 07:28:28', '2022-02-28 07:28:28');
INSERT INTO `circle` VALUES (3, 5, '再测试一下不发图片的！', '', '2022-02-28 07:29:49', '2022-02-28 07:29:49');
INSERT INTO `circle` VALUES (4, 4, '发个朋友圈，文案文案好难写啊，就随便写点啥吧，这是我的第一条朋友圈。', '129', '2022-02-28 16:08:29', '2022-02-28 16:08:29');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `comment_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '评论主键',
  `circle_id` int(0) NULL DEFAULT NULL COMMENT '朋友圈主键',
  `user_id` int(0) NULL DEFAULT NULL COMMENT '用户主键',
  `text` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内容',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '新建时间',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`comment_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (1, 2, 4, '你发的图片真漂亮', '2022-02-28 17:33:43', '2022-02-28 17:33:43');
INSERT INTO `comment` VALUES (2, 2, 5, '这是在哪儿拍的照片？', '2022-02-28 17:57:45', '2022-02-28 17:57:45');
INSERT INTO `comment` VALUES (3, 3, 5, '你为啥不发图片呢？', '2022-02-28 17:59:03', '2022-02-28 17:59:03');

-- ----------------------------
-- Table structure for file
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file`  (
  `file_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '文件主键',
  `file_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件名',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `user_id` int(0) NULL DEFAULT NULL COMMENT '用户主键',
  PRIMARY KEY (`file_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 134 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of file
-- ----------------------------
INSERT INTO `file` VALUES (90, '4_1645892990173_default-head.png', '2022-02-27 00:29:50', '2022-02-27 00:29:50', 4);
INSERT INTO `file` VALUES (91, '4_1645893584130_beautiful.jpg', '2022-02-27 00:39:44', '2022-02-27 00:39:44', 4);
INSERT INTO `file` VALUES (92, '4_1645893646681_beautiful.jpg', '2022-02-27 00:40:46', '2022-02-27 00:40:46', 4);
INSERT INTO `file` VALUES (126, '5_1646004198723_椰树.jpg', '2022-02-28 07:23:19', '2022-02-28 07:23:19', 5);
INSERT INTO `file` VALUES (127, '5_1646004209720_猫.jpg', '2022-02-28 07:23:29', '2022-02-28 07:23:29', 5);
INSERT INTO `file` VALUES (128, '5_1646004214384_海.jpg', '2022-02-28 07:23:34', '2022-02-28 07:23:34', 5);
INSERT INTO `file` VALUES (129, '4_1646035635994_海.jpg', '2022-02-28 16:07:16', '2022-02-28 16:07:16', 4);
INSERT INTO `file` VALUES (130, '4_1646038916852_back.jpg', '2022-02-28 17:01:56', '2022-02-28 17:01:56', 4);
INSERT INTO `file` VALUES (131, '4_1646054519818_beautiful.jpg', '2022-02-28 21:21:59', '2022-02-28 21:21:59', 4);
INSERT INTO `file` VALUES (132, '5_1646055578984_猫.jpg', '2022-02-28 21:39:38', '2022-02-28 21:39:38', 5);
INSERT INTO `file` VALUES (133, '5_1646056034870_WeChat_20220224152923_Trim.mp4', '2022-02-28 21:47:14', '2022-02-28 21:47:14', 5);

-- ----------------------------
-- Table structure for friend
-- ----------------------------
DROP TABLE IF EXISTS `friend`;
CREATE TABLE `friend`  (
  `friend_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '朋友表主键',
  `user_id` int(0) NULL DEFAULT NULL COMMENT '用户主键',
  `friend` int(0) NULL DEFAULT NULL COMMENT '朋友主键',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '新建时间',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  `tips` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '提示',
  PRIMARY KEY (`friend_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of friend
-- ----------------------------
INSERT INTO `friend` VALUES (2, 5, 4, '2022-02-27 17:20:31', '2022-02-27 18:33:12', '好友', '我是user1,你好，能加个好友吗？');
INSERT INTO `friend` VALUES (5, 5, 6, '2022-02-27 19:44:44', '2022-02-27 19:44:44', '申请', '我是user，久仰大名！');

-- ----------------------------
-- Table structure for likes
-- ----------------------------
DROP TABLE IF EXISTS `likes`;
CREATE TABLE `likes`  (
  `likes_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '点赞主键',
  `circle_id` int(0) NULL DEFAULT NULL COMMENT '朋友圈主键',
  `user_id` int(0) NULL DEFAULT NULL COMMENT '用户主键',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '新建时间',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`likes_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of likes
-- ----------------------------
INSERT INTO `likes` VALUES (1, 3, 5, '点赞', '2022-02-28 15:30:51', '2022-02-28 15:51:26');
INSERT INTO `likes` VALUES (7, 3, 4, '点赞', '2022-02-28 15:51:51', '2022-02-28 15:51:51');
INSERT INTO `likes` VALUES (8, 2, 4, '点赞', '2022-02-28 15:51:54', '2022-02-28 15:51:58');

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `message_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '消息主键',
  `from_id` int(0) NULL DEFAULT NULL COMMENT '来自谁',
  `to_id` int(0) NULL DEFAULT NULL COMMENT '发给谁',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '消息类型',
  `value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '值',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '新建时间',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`message_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES (1, 6, 5, 'text', '123456', NULL, '2022-02-27 23:03:51', '2022-02-28 00:53:21');
INSERT INTO `message` VALUES (2, 4, 5, 'text', '1524353654', NULL, '2022-02-27 23:13:42', '2022-02-28 00:53:32');
INSERT INTO `message` VALUES (3, 5, 4, 'text', '测试一下啦啦啦！', NULL, '2022-02-28 01:04:37', '2022-02-28 01:04:37');
INSERT INTO `message` VALUES (4, 5, 4, 'text', '在试一下', NULL, '2022-02-28 01:08:10', '2022-02-28 01:08:10');
INSERT INTO `message` VALUES (5, 5, 6, 'text', '云南鲜花饼好吃吗？', NULL, '2022-02-28 03:15:05', '2022-02-28 03:15:05');
INSERT INTO `message` VALUES (6, 6, 5, 'text', '我觉得很好吃呀！你想吃吗？我可以帮你带一点。', NULL, '2022-02-28 03:16:19', '2022-02-28 03:16:19');
INSERT INTO `message` VALUES (7, 5, 4, 'friend', '4', NULL, '2022-02-28 20:53:25', '2022-02-28 20:53:25');
INSERT INTO `message` VALUES (8, 4, 5, 'pic', '131', NULL, '2022-02-28 21:21:59', '2022-02-28 21:21:59');
INSERT INTO `message` VALUES (9, 5, 4, 'pic', '132', NULL, '2022-02-28 21:39:39', '2022-02-28 21:39:39');
INSERT INTO `message` VALUES (10, 5, 4, 'file', '133', NULL, '2022-02-28 21:47:15', '2022-02-28 21:47:15');
INSERT INTO `message` VALUES (11, 5, 4, 'text', '可以发送好友名片', NULL, '2022-02-28 21:49:07', '2022-02-28 21:49:07');
INSERT INTO `message` VALUES (12, 5, 4, 'text', '图片', NULL, '2022-02-28 21:49:14', '2022-02-28 21:49:14');
INSERT INTO `message` VALUES (13, 5, 4, 'text', '和文件', NULL, '2022-02-28 21:49:22', '2022-02-28 21:49:22');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户姓名',
  `user_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户手机号',
  `user_head` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户头像',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `user_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户类型',
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `province` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '省',
  `city` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '市',
  `signature` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '个性签名',
  `back` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户朋友圈背景',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (4, 'user', '15888888888', 'http://localhost:8070/4_1645893646681_beautiful.jpg', '2022-02-26 09:18:56', '2022-02-28 17:01:56', '123', '普通', '男', '广西', '南宁', '冲呀！！！乌拉', 'http://localhost:8070/4_1646038916852_back.jpg');
INSERT INTO `user` VALUES (5, 'user1', '123', 'http://localhost:8070/4_1645892990173_default-head.png', '2022-02-27 01:05:15', '2022-02-28 17:13:46', '123', '普通', NULL, NULL, NULL, NULL, 'http://localhost:8070/4_1646038916852_back.jpg');
INSERT INTO `user` VALUES (6, '花蝴蝶', '321', 'http://localhost:8070/4_1645892990173_default-head.png', '2022-02-27 01:10:14', '2022-02-28 18:45:48', '123', '普通', '女', '云南', '大理', '我不卖鲜花饼', 'http://localhost:8070/4_1646038916852_back.jpg');

SET FOREIGN_KEY_CHECKS = 1;
