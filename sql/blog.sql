/*
 Navicat Premium Data Transfer

 Source Server         : mhq
 Source Server Type    : MySQL
 Source Server Version : 50736
 Source Host           : localhost:3306
 Source Schema         : blog

 Target Server Type    : MySQL
 Target Server Version : 50736
 File Encoding         : 65001

 Date: 05/12/2022 23:33:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_blog
-- ----------------------------
DROP TABLE IF EXISTS `t_blog`;
CREATE TABLE `t_blog`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `first_picture` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `flag` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `views` int(11) NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `appreciation` int(11) NULL DEFAULT NULL,
  `share_statement` int(11) NULL DEFAULT NULL,
  `commentabled` int(11) NULL DEFAULT NULL,
  `published` int(11) NULL DEFAULT NULL,
  `recommend` int(11) NULL DEFAULT NULL,
  `type_id` int(11) NULL DEFAULT NULL,
  `user_id` int(11) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_blog
-- ----------------------------
INSERT INTO `t_blog` VALUES (1, 'SpringBoot简介', 'SpringBoot是一个开发基于Spring框架的应用的快速开发框架，它也是SpringCloud构建微服务分布式系统的基础设施。', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic1.zhimg.com%2Fv2-534a9a52caf487c7323dce0b1f95b6fe_1440w.jpg%3Fsource%3D172ae18b&refer=http%3A%2F%2Fpic1.zhimg.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1672287758&t=85c77ab31d2d62a245d791a203873447', '原创', 12, '这是springboot', 1, 1, 1, 1, 1, 1, 1, '2022-11-29 12:23:52', '2022-11-29 12:23:52');
INSERT INTO `t_blog` VALUES (2, 'vue是什么？vue的优点有哪些？', 'vue就是一个js库，并且无依赖别的js库，直接引入一个js文件就可以使用，与传统JS和JQuery框架不同，Vue的渐进式框架表示开发者可以由简单组件写起，渐渐搭建出一个复杂的前端平台。\r\n形成Vue渐进式框架的核心概念为：组件化，MVVM，响应式，和生命周期。\r\nVue一切是数据为核心，使用数据来驱动视图刷新，我们不建议去操作dom\r\n```\r\n<template>\r\n	<div class=\"demo\">\r\n		<h2>学校名称：{{name}}</h2>\r\n		<button @click=\"showName\">点我提示学校名</button>	\r\n	</div>\r\n</template>\r\n \r\n<script>\r\n	 export default {\r\n		name:\'School\',\r\n		data(){\r\n			return {\r\n				name:\'bdqn\',\r\n			}\r\n		},\r\n		methods: {\r\n			showName(){\r\n				alert(this.name)\r\n			}\r\n		},\r\n	}\r\n</script>\r\n \r\n<style>\r\n	.demo{\r\n		background-color: orange;\r\n	}\r\n</style>\r\n```', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Finews.gtimg.com%2Fnewsapp_bt%2F0%2F13843563168%2F641&refer=http%3A%2F%2Finews.gtimg.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1672290065&t=cce4c4c871c5759d85c8bc5a84fbfd32', '原创', 3, 'vue是什么', 0, 0, 0, 1, NULL, 2, 1, '2022-11-29 13:03:54', '2022-11-29 13:12:25');
INSERT INTO `t_blog` VALUES (3, 'Java是什么？Java到底能干嘛？', 'Java是一种可以撰写跨平台应用程序的、面向对象的程序设计语言。简单说，Java就是一种语言，不过是一种针对jvm的语言，Java编译后是字节码，而jvm会把这些字节码解释成机器码，然后做出相应的动作。', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fwww.pianshen.com%2Fimages%2F729%2Fa848398ed2cfa5cfa7039c203bd03611.png&refer=http%3A%2F%2Fwww.pianshen.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1672290370&t=a4ef77d1f99a253b067ee167cdc8f200', '转载', 0, 'java', 0, 0, 0, 1, NULL, 1, 1, '2022-11-29 13:05:19', '2022-11-29 13:06:20');
INSERT INTO `t_blog` VALUES (4, '什么是spring 框架？', 'IOC控制反转，对象创建责任的反转，在spring中BeanFacotory是IOC容器的核心接口，负责实例化，定位，配置应用程序中的对象及建立这些对象间的依赖。XmlBeanFacotory实现BeanFactory接口，通过获取xml配置文件数据，组成应用对象及对象间的依赖关系。\r\nspring中有3中注入方式，一种是set注入，另一种是接口注入，另一种是构造方法注入\r\nAOP就是纵向的编程，如下图，业务1 和 业务2 都需要一个共同的操作。与其往每个业务中都添加同样的代码，不如，写一遍代码，让两个业务共同使用这段代码。\r\nspring中面向切面编程的实现有两种方式，一种是动态代理，一种是CGLIB，动态代理必须要提供接口，而CGLIB实现是由=有继承。\r\n', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fwww.zyiz.net%2Fupload%2F202004%2F19%2F202004192228419683.jpg&refer=http%3A%2F%2Fwww.zyiz.net&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1672290394&t=80b30fcb440249657a59fb7ec87e0efc', '原创', 5, 'spring', 1, 1, 1, 1, 1, 1, 1, '2022-11-29 13:08:23', '2022-11-29 13:08:23');
INSERT INTO `t_blog` VALUES (5, 'SpringCloud是什么?', 'Spring Cloud就是微服务系统架构的一站式解决方案，是各个微服务架构落地技术的集合体,俗称微服务全家桶\r\n在平时我们构建微服务的过程中需要做如服务发现注册、配置中心、负载均衡、断路器、数据监控等操作,而Spring Cloud 为我们提供了一套简易的编程模型，使我们能在 Spring Boot 的基础上轻松地实现微服务项目的构建\r\n', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg-blog.csdnimg.cn%2Fc3d674d122e8490686a667ef2264ffd4.png&refer=http%3A%2F%2Fimg-blog.csdnimg.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1672290547&t=5650edcf15bd16091e65c7921c8578b4', '原创', 0, 'springcloud', 1, 1, 1, 1, 1, 1, 1, '2022-11-29 13:09:18', '2022-11-29 13:09:18');
INSERT INTO `t_blog` VALUES (6, '什么是面向对象', '面向过程是具体化的，流程化的，解决一个问题，你需要一步一步的分析，一步一步的实现。\r\n面向对象是模型化的，你只需抽象出一个类，这是一个封闭的盒子，在这里你拥有数据也拥有解决问题的方法。需要什么功能直接使用就可以了，不必去一步一步的实现，至于这个功能是如何实现的，管我们什么事？我们会用就可以了。', 'https://img0.baidu.com/it/u=2841044300,2599597810&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=726', '原创', 0, '面向对象', 0, 0, 0, 1, NULL, 1, 1, '2022-11-29 13:10:19', '2022-11-29 13:10:19');

-- ----------------------------
-- Table structure for t_blog_tags
-- ----------------------------
DROP TABLE IF EXISTS `t_blog_tags`;
CREATE TABLE `t_blog_tags`  (
  `blogs_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tags_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_blog_tags
-- ----------------------------
INSERT INTO `t_blog_tags` VALUES ('1', '1');
INSERT INTO `t_blog_tags` VALUES ('3', '1');
INSERT INTO `t_blog_tags` VALUES ('4', '3');
INSERT INTO `t_blog_tags` VALUES ('4', '1');
INSERT INTO `t_blog_tags` VALUES ('4', '4');
INSERT INTO `t_blog_tags` VALUES ('5', '1');
INSERT INTO `t_blog_tags` VALUES ('5', '4');
INSERT INTO `t_blog_tags` VALUES ('6', '3');
INSERT INTO `t_blog_tags` VALUES ('2', '2');

-- ----------------------------
-- Table structure for t_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nick_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `blog_id` int(11) NULL DEFAULT NULL,
  `parent_comment_id` int(11) NULL DEFAULT NULL,
  `is_admin_comment` int(11) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_comment
-- ----------------------------
INSERT INTO `t_comment` VALUES (4, 'Bryant', 'kobe@qq.com', '写的很好', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fww3.sinaimg.cn%2Fmw690%2F002pcC63gy1h8g5f0xm86j60ud0u0ady02.jpg&refer=http%3A%2F%2Fwww.sina.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1672289244&t=a688f63c205ce04370f18dec55191b98', 1, NULL, 0, '2022-11-29 12:54:24');
INSERT INTO `t_comment` VALUES (5, 'Kobe', 'kobe@qq.com', '对呀', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fww3.sinaimg.cn%2Fmw690%2F002pcC63gy1h8g5f0xm86j60ud0u0ady02.jpg&refer=http%3A%2F%2Fwww.sina.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1672289244&t=a688f63c205ce04370f18dec55191b98', 1, NULL, 0, '2022-11-29 12:55:49');
INSERT INTO `t_comment` VALUES (6, 'Paul', 'kobe@qq.com', '非常好的博客', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fww3.sinaimg.cn%2Fmw690%2F002pcC63gy1h8g5f0xm86j60ud0u0ady02.jpg&refer=http%3A%2F%2Fwww.sina.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1672289244&t=a688f63c205ce04370f18dec55191b98', 1, NULL, 0, '2022-11-29 12:58:33');

-- ----------------------------
-- Table structure for t_config
-- ----------------------------
DROP TABLE IF EXISTS `t_config`;
CREATE TABLE `t_config`  (
  `config_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '键名',
  `config_value` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '键值'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_config
-- ----------------------------
INSERT INTO `t_config` VALUES ('qq', '1633049666');
INSERT INTO `t_config` VALUES ('email', '1633049666@qq.com');
INSERT INTO `t_config` VALUES ('wechatQrCode', 'http://127.0.0.1:8080/file/55d7eb20093c4fa888969a1e4af34545.jpg');
INSERT INTO `t_config` VALUES ('wechatCollectionCode', 'http://127.0.0.1:8080/file/fb0fb838ddec4d298f30ed046e83ed8d.jpg');
INSERT INTO `t_config` VALUES ('alipayCollectionCode', 'http://127.0.0.1:8080/file/1e080602bce64cb6a369872f3a404fa4.jpg');

-- ----------------------------
-- Table structure for t_tag
-- ----------------------------
DROP TABLE IF EXISTS `t_tag`;
CREATE TABLE `t_tag`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `blogs_count` int(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_tag
-- ----------------------------
INSERT INTO `t_tag` VALUES (1, 'SpringBoot', NULL);
INSERT INTO `t_tag` VALUES (2, 'Vue', NULL);
INSERT INTO `t_tag` VALUES (3, 'Spring', NULL);
INSERT INTO `t_tag` VALUES (4, 'SpringCloud', NULL);

-- ----------------------------
-- Table structure for t_type
-- ----------------------------
DROP TABLE IF EXISTS `t_type`;
CREATE TABLE `t_type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `blogs_count` int(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_type
-- ----------------------------
INSERT INTO `t_type` VALUES (1, 'Java', NULL);
INSERT INTO `t_type` VALUES (2, 'JavaScript', NULL);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nick_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `avatar` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` int(11) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'admin', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '1633049666@qq.com', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fww3.sinaimg.cn%2Fmw690%2F002pcC63gy1h8g5f0xm86j60ud0u0ady02.jpg&refer=http%3A%2F%2Fwww.sina.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1672289244&t=a688f63c205ce04370f18dec55191b98', 1, '2022-11-29 12:15:02', '2022-11-29 12:15:03');

SET FOREIGN_KEY_CHECKS = 1;
