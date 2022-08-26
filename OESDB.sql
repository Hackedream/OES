/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 8.0.11 : Database - oes
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`oes` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `oes`;

/*Table structure for table `answer` */

DROP TABLE IF EXISTS `answer`;

CREATE TABLE `answer` (
  `anid` int(8) unsigned NOT NULL AUTO_INCREMENT COMMENT '回答ID',
  `anqid` int(8) unsigned NOT NULL COMMENT '问题ID',
  `anuid` int(8) unsigned NOT NULL COMMENT '回答者ID',
  `anUsername` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '回答者昵称',
  `anquid` int(8) unsigned NOT NULL COMMENT '提问者ID',
  `anqUsername` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '提问者昵称',
  `anInfo` varchar(2000) NOT NULL COMMENT '回答内容',
  `anTime` datetime DEFAULT NULL COMMENT '回答时间',
  `anLikeNum` int(8) NOT NULL DEFAULT '0' COMMENT '回答点赞数',
  PRIMARY KEY (`anid`),
  KEY `anserid` (`anuid`),
  KEY `ansqid` (`anqid`),
  CONSTRAINT `answer_ibfk_1` FOREIGN KEY (`anuid`) REFERENCES `user` (`uid`),
  CONSTRAINT `answer_ibfk_2` FOREIGN KEY (`anqid`) REFERENCES `question` (`qid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `answer` */

insert  into `answer`(`anid`,`anqid`,`anuid`,`anUsername`,`anquid`,`anqUsername`,`anInfo`,`anTime`,`anLikeNum`) values (1,1,2,'ZY',1,'LQQ','主动出击，所向披靡（doge）。','2022-08-24 09:11:54',2),(2,2,1,'LQQ',2,'ZY','因为ZY是憨憨。','2022-08-24 09:13:43',3),(3,3,4,'OYY',3,'LXH','为了去寻找适合栖息的坏境。','2022-08-24 09:15:29',4),(4,4,3,'LXH',4,'QYY','大多靠活体传播，也有依附在其他物体上传播的，不过这样的话病毒会在一定时间后失活。','2022-08-24 09:19:27',5);

/*Table structure for table `checklist` */

DROP TABLE IF EXISTS `checklist`;

CREATE TABLE `checklist` (
  `chid` int(8) unsigned NOT NULL AUTO_INCREMENT COMMENT '审核ID',
  `chaid` int(8) unsigned NOT NULL COMMENT '管理员ID',
  `chaName` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '管理员姓名',
  `chlid` int(8) unsigned NOT NULL COMMENT '课程ID',
  `chlName` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '课程名称',
  `chuid` int(8) unsigned NOT NULL COMMENT '讲师ID',
  `chuName` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '讲师姓名',
  `chTime` datetime DEFAULT NULL COMMENT '审核时间',
  `chState` tinyint(1) NOT NULL DEFAULT '0' COMMENT '审核状态（0为未通过，1为已通过）',
  PRIMARY KEY (`chid`),
  KEY `aid` (`chaid`),
  KEY `lid` (`chlid`),
  KEY `uid` (`chuid`),
  CONSTRAINT `checklist_ibfk_1` FOREIGN KEY (`chaid`) REFERENCES `webmaster` (`wid`),
  CONSTRAINT `checklist_ibfk_2` FOREIGN KEY (`chlid`) REFERENCES `lesson` (`lid`),
  CONSTRAINT `checklist_ibfk_3` FOREIGN KEY (`chuid`) REFERENCES `user` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `checklist` */

insert  into `checklist`(`chid`,`chaid`,`chaName`,`chlid`,`chlName`,`chuid`,`chuName`,`chTime`,`chState`) values (1,1,'LQQ',3,NULL,3,NULL,'2022-08-24 16:32:45',1);

/*Table structure for table `collect_lessons` */

DROP TABLE IF EXISTS `collect_lessons`;

CREATE TABLE `collect_lessons` (
  `uid` int(8) unsigned NOT NULL COMMENT '用户id',
  `lid` int(8) unsigned NOT NULL COMMENT '课程id',
  KEY `uid` (`uid`),
  KEY `lid` (`lid`),
  CONSTRAINT `collect_lessons_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`),
  CONSTRAINT `collect_lessons_ibfk_2` FOREIGN KEY (`lid`) REFERENCES `lesson` (`lid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `collect_lessons` */

insert  into `collect_lessons`(`uid`,`lid`) values (1,2),(4,2),(1,3),(1,1),(7,3);

/*Table structure for table `comment` */

DROP TABLE IF EXISTS `comment`;

CREATE TABLE `comment` (
  `cid` int(8) unsigned NOT NULL AUTO_INCREMENT COMMENT '评论ID',
  `cInfo` varchar(2000) NOT NULL COMMENT '评论内容',
  `cTime` datetime DEFAULT NULL COMMENT '评论时间',
  `cFloor` int(8) DEFAULT NULL COMMENT '评论楼层',
  `cuid` int(8) unsigned NOT NULL COMMENT '评论人ID',
  `cUsername` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '评论人昵称',
  `cLikeNum` int(8) unsigned NOT NULL DEFAULT '0' COMMENT '评论点赞数',
  `cType` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '评论类型（0为评论，1为回复）',
  `cruid` int(8) unsigned DEFAULT NULL COMMENT '评论回复人ID',
  `crUsername` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '评论回复人昵称',
  PRIMARY KEY (`cid`),
  KEY `comerID` (`cuid`),
  KEY `comReplyID` (`cruid`),
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`cuid`) REFERENCES `user` (`uid`),
  CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`cruid`) REFERENCES `user` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `comment` */

insert  into `comment`(`cid`,`cInfo`,`cTime`,`cFloor`,`cuid`,`cUsername`,`cLikeNum`,`cType`,`cruid`,`crUsername`) values (1,'妙啊妙啊','2022-08-24 09:44:51',1,2,'ZY',1,0,NULL,NULL),(2,'实在是妙啊','2022-08-24 09:46:52',2,1,'LQQ',21,1,2,'ZY'),(3,'真是妙蛙种子到妙妙屋了','2022-08-24 09:48:10',3,3,'LXH',66,1,1,'LQQ'),(4,'妙到家了','2022-08-24 09:50:30',4,4,'OYY',123,1,3,'LXH');

/*Table structure for table `indent` */

DROP TABLE IF EXISTS `indent`;

CREATE TABLE `indent` (
  `inid` int(8) unsigned NOT NULL AUTO_INCREMENT COMMENT '订单ID',
  `inStuID` int(8) unsigned NOT NULL COMMENT '卖家ID',
  `inStuUsername` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '卖家姓名',
  `inTeaID` int(8) unsigned NOT NULL COMMENT '买家ID',
  `inTeaUsername` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '买家姓名',
  `inlid` int(8) unsigned NOT NULL COMMENT '课程ID',
  `inlName` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '课程名称',
  `inCreateTime` datetime DEFAULT NULL COMMENT '订单创建时间',
  `inState` tinyint(1) NOT NULL DEFAULT '0' COMMENT '订单状态（0为取消支付，1为已支付）',
  `inCompleteTime` datetime DEFAULT NULL COMMENT '订单完成时间',
  `inAlipayTradeNo` varchar(64) DEFAULT NULL COMMENT '支付宝交易流水号',
  PRIMARY KEY (`inid`),
  KEY `osalerid` (`inStuID`),
  KEY `obuyerid` (`inTeaID`),
  KEY `olessonid` (`inlid`),
  CONSTRAINT `indent_ibfk_1` FOREIGN KEY (`inStuID`) REFERENCES `user` (`uid`),
  CONSTRAINT `indent_ibfk_2` FOREIGN KEY (`inTeaID`) REFERENCES `user` (`uid`),
  CONSTRAINT `indent_ibfk_3` FOREIGN KEY (`inlid`) REFERENCES `lesson` (`lid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `indent` */

insert  into `indent`(`inid`,`inStuID`,`inStuUsername`,`inTeaID`,`inTeaUsername`,`inlid`,`inlName`,`inCreateTime`,`inState`,`inCompleteTime`,`inAlipayTradeNo`) values (1,2,'ZY',1,'LQQ',1,'核物理的美学','2022-08-24 09:42:09',1,'2022-08-24 09:42:19',NULL),(2,1,'LQQ',2,'ZY',2,'高中物理（高一下）','2022-08-24 09:43:12',2,'2022-08-24 09:43:16',NULL),(3,3,'LXH',1,'LQQ',1,'核物理的美学','2022-08-24 09:52:24',2,'2022-08-25 09:52:29',NULL),(4,4,'OYY',3,'LXH',5,'语文专项训练','2022-08-24 09:53:25',2,'2022-08-26 09:53:30',NULL),(7,7,'Qiqi',1,'LQQ',2,'高中物理（高一下）','2022-08-26 03:51:06',1,'2022-08-26 03:51:06',NULL);

/*Table structure for table `lesson` */

DROP TABLE IF EXISTS `lesson`;

CREATE TABLE `lesson` (
  `lid` int(8) unsigned NOT NULL AUTO_INCREMENT COMMENT '课程ID',
  `lName` varchar(64) NOT NULL COMMENT '课程名称',
  `lCategory` int(4) unsigned NOT NULL COMMENT '课程类别',
  `lIntro` varchar(200) DEFAULT NULL COMMENT '课程简介',
  `lInfo` varchar(2000) DEFAULT NULL COMMENT '课程详情',
  `lPrice` double(12,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '课程价格',
  `lRank` double(12,1) DEFAULT NULL COMMENT '课程评分',
  `lTime` double(12,1) NOT NULL DEFAULT '0.0' COMMENT '课程时长',
  `lDiscount` double(4,2) DEFAULT NULL COMMENT '课程折扣',
  `lLikeNum` int(8) unsigned NOT NULL DEFAULT '0' COMMENT '课程点赞数',
  `lCollectNum` int(8) unsigned NOT NULL DEFAULT '0' COMMENT '课程收藏数',
  `luid` int(8) unsigned NOT NULL COMMENT '课程上传者ID',
  `luName` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '课程上传者',
  `luTime` datetime DEFAULT NULL COMMENT '课程上传时间',
  `lState` int(4) unsigned NOT NULL DEFAULT '0' COMMENT '课程审核状态',
  PRIMARY KEY (`lid`),
  KEY `lupid` (`luid`),
  CONSTRAINT `lesson_ibfk_1` FOREIGN KEY (`luid`) REFERENCES `user` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `lesson` */

insert  into `lesson`(`lid`,`lName`,`lCategory`,`lIntro`,`lInfo`,`lPrice`,`lRank`,`lTime`,`lDiscount`,`lLikeNum`,`lCollectNum`,`luid`,`luName`,`luTime`,`lState`) values (1,'核物理的美学',4,'这是一本有关核物理基础知识的书。',NULL,111.11,1.0,20.0,12.00,23,12,2,'ZY','2022-08-24 09:23:27',1),(2,'高中物理（高一下）',4,'由省级十佳教师倾力打造',NULL,66.66,2.0,66.6,66.00,66,66,1,'LQQ','2022-08-24 09:26:01',1),(3,'十天速通生物（初中）',6,'全面覆盖初中生物的各个知识点，将难点疑点一网打尽',NULL,123.23,4.0,22.3,34.00,34,23,4,'OYY','2022-08-24 09:32:32',0),(4,'高中生物',6,'趣味生动带你学习高中生物',NULL,55.50,9.2,55.5,23.00,3,45,3,'LXH','2022-08-24 09:38:12',0),(5,'语文专项训练',1,'特级教师带你突破语文的各个难关',NULL,666666.66,3.0,66666666666.7,6.00,6,6,3,'LXH','2022-08-24 09:29:13',2);

/*Table structure for table `question` */

DROP TABLE IF EXISTS `question`;

CREATE TABLE `question` (
  `qid` int(8) unsigned NOT NULL AUTO_INCREMENT COMMENT '问题ID',
  `quid` int(8) unsigned NOT NULL COMMENT '提问者ID',
  `qUsername` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '提问者昵称',
  `qContent` varchar(2000) NOT NULL COMMENT '提问内容',
  `qTime` datetime DEFAULT NULL COMMENT '提问时间',
  `qLikeNum` int(8) unsigned NOT NULL DEFAULT '0' COMMENT '提问点赞数',
  `qCollectNum` int(8) unsigned NOT NULL DEFAULT '0' COMMENT '提问收藏数',
  `qlid` int(8) DEFAULT NULL COMMENT '课程提问ID',
  `qlName` varchar(64) DEFAULT NULL COMMENT '课程名称',
  PRIMARY KEY (`qid`),
  KEY `qerid` (`quid`),
  CONSTRAINT `question_ibfk_1` FOREIGN KEY (`quid`) REFERENCES `user` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `question` */

insert  into `question`(`qid`,`quid`,`qUsername`,`qContent`,`qTime`,`qLikeNum`,`qCollectNum`,`qlid`,`qlName`) values (1,1,'LQQ','为什么我没有npy？','2022-08-24 08:56:26',0,0,1,'核物理的美学'),(2,2,'ZY','为什么LQQ没有npy？','2022-08-24 08:59:59',0,0,2,'高中物理（高一下）'),(3,3,'LXH','为什么大雁要迁徙？','2022-08-24 09:04:20',0,0,3,'10天速通生物（初中）'),(4,4,'OYY','病毒依靠什么传播？','2022-08-24 09:06:16',0,0,4,'高中生物');

/*Table structure for table `searchlist` */

DROP TABLE IF EXISTS `searchlist`;

CREATE TABLE `searchlist` (
  `sid` int(8) unsigned NOT NULL AUTO_INCREMENT COMMENT '搜索人ID',
  `sKeyword` varchar(120) NOT NULL COMMENT '关键词',
  `sTime` datetime DEFAULT NULL COMMENT '搜索时间',
  PRIMARY KEY (`sid`),
  CONSTRAINT `searchlist_ibfk_1` FOREIGN KEY (`sid`) REFERENCES `user` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `searchlist` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `uid` int(8) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(32) NOT NULL COMMENT '用户昵称',
  `uName` varchar(32) DEFAULT NULL COMMENT '用户姓名',
  `uRegiTime` datetime DEFAULT NULL COMMENT '注册时间',
  `uUpdateTime` datetime DEFAULT NULL COMMENT '更新时间',
  `uGender` tinyint(1) DEFAULT '1' COMMENT '用户性别',
  `uBirth` date DEFAULT NULL COMMENT '用户生日',
  `uTel` varchar(16) NOT NULL COMMENT '用户手机号',
  `uEmail` varchar(32) DEFAULT NULL COMMENT '用户邮箱',
  `uPassword` varchar(16) NOT NULL DEFAULT '123456' COMMENT '用户密码',
  `uBalance` double(12,2) DEFAULT '0.00' COMMENT '用户余额',
  `uWatching` double(12,1) DEFAULT '0.0' COMMENT '观看时长',
  `uType` tinyint(1) NOT NULL DEFAULT '0' COMMENT '用户类型',
  `uRank` double(12,1) DEFAULT NULL COMMENT '讲师评分',
  `uLessonNum` int(8) DEFAULT '0' COMMENT '课程数量',
  `uPayPassword` int(6) DEFAULT NULL COMMENT '支付密码',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`uid`,`username`,`uName`,`uRegiTime`,`uUpdateTime`,`uGender`,`uBirth`,`uTel`,`uEmail`,`uPassword`,`uBalance`,`uWatching`,`uType`,`uRank`,`uLessonNum`,`uPayPassword`) values (1,'LQQ','刘清泉','2022-08-17 12:05:08','2022-08-22 16:59:03',1,'2022-08-17','15932586495','sdfghjkl@qq.com','123456',99999.99,999.9,1,9.9,99,741852),(2,'ZY','卓阳','2022-08-17 12:06:42','2022-08-22 16:59:06',1,'2022-07-17','13896248632',NULL,'123456',0.00,0.0,0,NULL,0,852741),(3,'LXH','骆小会','2022-08-17 12:07:38','2022-08-22 16:59:08',0,'2022-06-17','18368495219',NULL,'123456',12345.67,987.6,0,NULL,0,741963),(4,'OYY','欧阳毅','2022-08-17 12:08:33','2022-08-22 16:59:10',1,'2022-05-17','18092195626',NULL,'123456',0.00,0.0,0,NULL,0,852963),(6,'asd','ASD','2022-08-22 10:50:47','2022-08-22 10:50:47',0,'2002-07-05','14765984258','abgsdiuk@165.com','asdfghjkl',0.00,0.0,0,0.0,0,654987),(7,'Qiqi','七七','2022-08-25 01:40:42','2022-08-25 01:40:42',0,'2017-07-07','15977777777','QIQI@Genshin.com','zuiaiheyenai',7777.77,77.7,0,0.0,0,777777);

/*Table structure for table `user_lesson` */

DROP TABLE IF EXISTS `user_lesson`;

CREATE TABLE `user_lesson` (
  `uid` int(8) unsigned NOT NULL COMMENT '用户id',
  `lid` int(8) unsigned NOT NULL COMMENT '课程id',
  KEY `uid` (`uid`),
  KEY `lid` (`lid`),
  CONSTRAINT `user_lesson_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`),
  CONSTRAINT `user_lesson_ibfk_2` FOREIGN KEY (`lid`) REFERENCES `lesson` (`lid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_lesson` */

insert  into `user_lesson`(`uid`,`lid`) values (1,1),(4,1),(1,2),(1,3),(2,1),(7,1);

/*Table structure for table `webmaster` */

DROP TABLE IF EXISTS `webmaster`;

CREATE TABLE `webmaster` (
  `wid` int(8) unsigned NOT NULL AUTO_INCREMENT COMMENT '管理员ID',
  `wAccount` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员账号',
  `wPassword` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号密码',
  `wName` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员姓名',
  `wTel` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员手机号',
  `wEmail` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员邮箱',
  PRIMARY KEY (`wid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `webmaster` */

insert  into `webmaster`(`wid`,`wAccount`,`wPassword`,`wName`,`wTel`,`wEmail`) values (1,'qwer123','123qwer','LQQ','15962849584','163@163.com'),(2,'asdf456','456asdf','ZY','18301685205','118@qq.com'),(3,'zxcv789','789zxcv','LXH','18054856254','wuhu@qq.com'),(4,'qaz741','741qaz','OYY','113','miao@qq.com'),(5,'wsx852','852wsx','QIQI','13879526845','yenai@genshin.com'),(6,'edc963','963edc','KLEE','17095846258','klqlbd@genshin.com');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
