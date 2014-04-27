/*
SQLyog Ultimate v10.42 
MySQL - 5.5.33 : Database - kep_v2
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`kep_v2` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `kep_v2`;

/*Table structure for table `group_teacher_subject` */

DROP TABLE IF EXISTS `group_teacher_subject`;

CREATE TABLE `group_teacher_subject` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `teacher_ID` int(11) NOT NULL,
  `group_ID` int(11) NOT NULL,
  `subject_ID` int(11) NOT NULL,
  `sumestr` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_gts_teacher` (`teacher_ID`),
  KEY `fk_gts_group` (`group_ID`),
  KEY `fk_gts_subject` (`subject_ID`),
  CONSTRAINT `fk_gts_subject` FOREIGN KEY (`subject_ID`) REFERENCES `subjects` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_gts_teacher` FOREIGN KEY (`teacher_ID`) REFERENCES `teachers` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

/*Data for the table `group_teacher_subject` */

insert  into `group_teacher_subject`(`id`,`teacher_ID`,`group_ID`,`subject_ID`,`sumestr`) values (20,10,8,16,NULL),(21,10,8,15,NULL),(22,13,8,13,NULL),(23,11,9,9,NULL),(24,10,9,15,NULL),(25,13,9,17,NULL),(26,12,10,9,NULL),(27,11,10,12,NULL),(28,12,10,11,NULL),(29,12,11,13,NULL),(30,11,11,9,NULL),(31,13,11,17,NULL),(32,10,8,11,NULL),(33,12,8,9,NULL);

/*Table structure for table `groups` */

DROP TABLE IF EXISTS `groups`;

CREATE TABLE `groups` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) NOT NULL,
  `Degree` int(11) DEFAULT NULL,
  `StartDate` date DEFAULT NULL,
  `FinishDate` date DEFAULT NULL,
  `CuratorId` int(11) DEFAULT NULL,
  `EducYear` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `CuratorId` (`CuratorId`),
  KEY `groups_ibfk_4` (`Degree`),
  CONSTRAINT `groups_ibfk_3` FOREIGN KEY (`CuratorId`) REFERENCES `teachers` (`Id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `groups_ibfk_4` FOREIGN KEY (`Degree`) REFERENCES `spec` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `groups` */

insert  into `groups`(`Id`,`Name`,`Degree`,`StartDate`,`FinishDate`,`CuratorId`,`EducYear`) values (8,'ПІ-10-01',3,NULL,NULL,12,4),(9,'ПІ-10-02',3,NULL,NULL,11,4),(10,'КІ-10-01',4,NULL,NULL,10,4),(11,'КІ-10-02',4,NULL,NULL,13,4);

/*Table structure for table `marks` */

DROP TABLE IF EXISTS `marks`;

CREATE TABLE `marks` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Mark` int(11) DEFAULT NULL,
  `StudentId` int(11) NOT NULL,
  `Retake` int(11) DEFAULT NULL,
  `TeacherSubjectGroupId` int(11) DEFAULT NULL,
  `Date` date DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `MarksStudents_idx` (`StudentId`),
  KEY `TeacherSubjectGroupId` (`TeacherSubjectGroupId`),
  CONSTRAINT `MarksStudents` FOREIGN KEY (`StudentId`) REFERENCES `students` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `marks_ibfk_1` FOREIGN KEY (`TeacherSubjectGroupId`) REFERENCES `group_teacher_subject` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;

/*Data for the table `marks` */

insert  into `marks`(`Id`,`Mark`,`StudentId`,`Retake`,`TeacherSubjectGroupId`,`Date`) values (46,10,14,NULL,26,NULL),(47,5,15,NULL,26,NULL),(48,11,12,NULL,23,NULL),(51,6,9,NULL,20,NULL),(52,12,9,NULL,21,NULL),(53,8,9,NULL,22,NULL),(55,6,13,NULL,23,NULL);

/*Table structure for table `spec` */

DROP TABLE IF EXISTS `spec`;

CREATE TABLE `spec` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Degree` float NOT NULL,
  `Name` varchar(45) DEFAULT NULL,
  `Viddil` varchar(45) DEFAULT NULL,
  `ZavViddil` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ZavViddil` (`ZavViddil`),
  KEY `id` (`id`),
  CONSTRAINT `spec_ibfk_1` FOREIGN KEY (`ZavViddil`) REFERENCES `teachers` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `spec` */

insert  into `spec`(`id`,`Degree`,`Name`,`Viddil`,`ZavViddil`) values (3,6.02041,'Програмування','Бакалаврської підготовки та програмування',13),(4,4.0508,'Комп\'ютерна інженерія','Комп\'ютерних систем',11);

/*Table structure for table `students` */

DROP TABLE IF EXISTS `students`;

CREATE TABLE `students` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `GroupId` int(11) NOT NULL,
  `Name` varchar(45) NOT NULL,
  `Surname` varchar(45) NOT NULL,
  `Patronimic` varchar(45) NOT NULL,
  `BDate` date DEFAULT NULL,
  `login` varchar(45) DEFAULT NULL,
  `Gradebook` varchar(10) DEFAULT NULL,
  `EducForm` varchar(45) DEFAULT NULL COMMENT '//денна/заочна',
  `EducType` varchar(45) DEFAULT NULL COMMENT '//платне/державне',
  `Gender` varchar(1) DEFAULT NULL,
  `Address` varchar(100) DEFAULT NULL,
  `Phone` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `StudGroup_idx` (`GroupId`),
  KEY `login` (`login`),
  CONSTRAINT `students_ibfk_1` FOREIGN KEY (`login`) REFERENCES `users` (`Login`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `StudGroup` FOREIGN KEY (`GroupId`) REFERENCES `groups` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

/*Data for the table `students` */

insert  into `students`(`Id`,`GroupId`,`Name`,`Surname`,`Patronimic`,`BDate`,`login`,`Gradebook`,`EducForm`,`EducType`,`Gender`,`Address`,`Phone`) values (9,8,'Олег','Новосад','Ігорович','1994-10-11','olololeg','2344503','денна','державне','ч','ГОА','757013'),(10,8,'Олег','Протас','Ігорович',NULL,'prot','2400345','денна','державне','ч','вадллдавв','30403'),(11,8,'Ірина','Кулікова','Володимирівна',NULL,'blondunka','45443','денна','державне','ж','аіплдлів','4523'),(12,9,'Тарас','Пашко','Іванович',NULL,NULL,'4352','денна','платне','ч','апак','45546432'),(13,9,'Христина','Піцьків','Олегівна',NULL,NULL,'35245','денна','державне','ж','відлаові','342654262'),(14,10,'Олег','Цюцьмаць','Ігорович',NULL,NULL,'4е623','','','','',''),(15,10,'Назар','Чорній','Олександрович',NULL,NULL,'42к554','','','','',''),(16,11,'Станіслав','Адаменко','Валерійович',NULL,NULL,'кку434','','','','',''),(17,11,'Петро','Скрипник','Ігорович',NULL,NULL,'валп54','','','','','');

/*Table structure for table `subjects` */

DROP TABLE IF EXISTS `subjects`;

CREATE TABLE `subjects` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL,
  `ECTSCredits` double DEFAULT NULL,
  `Hours` int(11) DEFAULT NULL,
  `Lections` int(11) DEFAULT NULL,
  `Pract` int(11) DEFAULT NULL,
  `Labor` int(11) DEFAULT NULL,
  `Consult` int(11) DEFAULT NULL,
  `ControlForm` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

/*Data for the table `subjects` */

insert  into `subjects`(`Id`,`Name`,`ECTSCredits`,`Hours`,`Lections`,`Pract`,`Labor`,`Consult`,`ControlForm`) values (9,'ООП',43.2,44,NULL,NULL,NULL,NULL,'Іспит'),(10,'Основи патентознавства',12.3,22,NULL,NULL,NULL,NULL,'Д/З'),(11,'Економіка і організація виробництва',18.2,16,NULL,NULL,NULL,NULL,'Д/З'),(12,'Основи маркетингу',14.8,24,NULL,NULL,NULL,NULL,'Д/З'),(13,'Соціологія',10,8,NULL,NULL,NULL,NULL,'Залік'),(14,'Групова динаміка і комунікації',34.1,36,NULL,NULL,NULL,NULL,'Залік'),(15,'Розробка WEB-застосувань',53.2,68,NULL,NULL,NULL,NULL,'Іспит'),(16,'Організація комп\'ютерних мереж',74.2,64,NULL,NULL,NULL,NULL,'Іспит'),(17,'Інструментальні засоби візуального програмування',35.6,48,NULL,NULL,NULL,NULL,'Іспит');

/*Table structure for table `teachers` */

DROP TABLE IF EXISTS `teachers`;

CREATE TABLE `teachers` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) NOT NULL,
  `Surname` varchar(45) DEFAULT NULL,
  `Patronimic` varchar(45) DEFAULT NULL,
  `Login` varchar(45) DEFAULT NULL,
  `Spec` varchar(45) DEFAULT NULL,
  `Dyplom` varchar(45) DEFAULT NULL,
  `Category` varchar(45) DEFAULT NULL,
  `Degree` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `Login` (`Login`),
  CONSTRAINT `teachers_ibfk_1` FOREIGN KEY (`Login`) REFERENCES `users` (`Login`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

/*Data for the table `teachers` */

insert  into `teachers`(`Id`,`Name`,`Surname`,`Patronimic`,`Login`,`Spec`,`Dyplom`,`Category`,`Degree`) values (10,'Юрій','Семеген','Володимирович','sem',NULL,NULL,NULL,NULL),(11,'Ігор','Левицький','Володимирович','lev',NULL,NULL,NULL,NULL),(12,'Данило','Кучеренко','Іванович','kuk',NULL,NULL,NULL,NULL),(13,'Світлана','Гнатюк','Олексіївна',NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `Login` varchar(45) NOT NULL,
  `Password` varchar(32) NOT NULL,
  `Category` text,
  PRIMARY KEY (`Login`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `users` */

insert  into `users`(`Login`,`Password`,`Category`) values ('admin','202cb962ac59075b964b07152d234b70','ROLE_ADMIN'),('admin1','202cb962ac59075b964b07152d234b70','ROLE_ADMIN'),('blondunka','202cb962ac59075b964b07152d234b70','ROLE_STUDENT'),('kuk','202cb962ac59075b964b07152d234b70','ROLE_TEACHER'),('lev','202cb962ac59075b964b07152d234b70','ROLE_TEACHER'),('olololeg','202cb962ac59075b964b07152d234b70','ROLE_STUDENT'),('prot','202cb962ac59075b964b07152d234b70','ROLE_STUDENT'),('sem','202cb962ac59075b964b07152d234b70','ROLE_TEACHER');

/* Procedure structure for procedure `get_group_subjects` */

/*!50003 DROP PROCEDURE IF EXISTS  `get_group_subjects` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `get_group_subjects`(in grId int)
BEGIN
SELECT
    `subjects`.`Id`
    , `subjects`.`Name`
    , `subjects`.`ControlForm`
FROM
    `kep_v2`.`group_teacher_subject`
    INNER JOIN `kep_v2`.`subjects` 
        ON (`group_teacher_subject`.`subject_ID` = `subjects`.`Id`)
    INNER JOIN `kep_v2`.`groups` 
        ON (`group_teacher_subject`.`group_ID` = `groups`.`Id`)
WHERE (`groups`.`Id` =grId);
    END */$$
DELIMITER ;

/* Procedure structure for procedure `get_mark` */

/*!50003 DROP PROCEDURE IF EXISTS  `get_mark` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `get_mark`(in studId int, in subjId int)
BEGIN
SELECT
    `marks`.`Id`
    , `marks`.`Mark`
    , `marks`.`Date`
FROM
    `kep_v2`.`marks`
    INNER JOIN `kep_v2`.`group_teacher_subject` 
        ON (`marks`.`TeacherSubjectGroupId` = `group_teacher_subject`.`id`)
WHERE (`marks`.`StudentId` =studId
    AND `group_teacher_subject`.`subject_ID` =subjId);
    END */$$
DELIMITER ;

/* Procedure structure for procedure `get_teacher` */

/*!50003 DROP PROCEDURE IF EXISTS  `get_teacher` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `get_teacher`(in subjId int, in grId int)
BEGIN
SELECT
    `teachers`.`Id`,
    `teachers`.`Name`
    , `teachers`.`Surname`
    , `teachers`.`Patronimic`
FROM
    `kep_v2`.`group_teacher_subject`
    INNER JOIN `kep_v2`.`teachers` 
        ON (`group_teacher_subject`.`teacher_ID` = `teachers`.`Id`)
WHERE (`group_teacher_subject`.`subject_ID` =subjId
    AND `group_teacher_subject`.`group_ID` = grId);
    END */$$
DELIMITER ;

/* Procedure structure for procedure `get_teacher_groups` */

/*!50003 DROP PROCEDURE IF EXISTS  `get_teacher_groups` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `get_teacher_groups`( IN teacId INT)
BEGIN
SELECT 
    `groups`.`Id`
    , `groups`.`Name`
    , `groups`.`EducYear`
    ,`groups`.`degree`
FROM
    `kep_v2`.`group_teacher_subject`
    INNER JOIN `kep_v2`.`groups` 
        ON (`group_teacher_subject`.`group_ID` = `groups`.`Id`)
WHERE (`group_teacher_subject`.`teacher_ID` =teacId);
    END */$$
DELIMITER ;

/* Procedure structure for procedure `get_teacher_group_subjects` */

/*!50003 DROP PROCEDURE IF EXISTS  `get_teacher_group_subjects` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `get_teacher_group_subjects`(in teacId int, in grId int)
BEGIN
	SELECT distinct
    `subjects`.`Id`
    , `subjects`.`Name`,
    `subjects`.`ControlForm`
FROM
    `kep_v2`.`group_teacher_subject`
    INNER JOIN `kep_v2`.`teachers` 
        ON (`group_teacher_subject`.`teacher_ID` = `teachers`.`Id`)
    INNER JOIN `kep_v2`.`subjects` 
        ON (`group_teacher_subject`.`subject_ID` = `subjects`.`Id`)
WHERE (`teachers`.`Id` =teacId)and(`group_teacher_subject`.`group_ID`=grId);
    END */$$
DELIMITER ;

/* Procedure structure for procedure `get_teacher_subjects` */

/*!50003 DROP PROCEDURE IF EXISTS  `get_teacher_subjects` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `get_teacher_subjects`(in teacId int)
BEGIN
	SELECT DISTINCT
    `subjects`.`Id`
    , `subjects`.`Name`,
    `subjects`.`ControlForm`
FROM
    `kep_v2`.`group_teacher_subject`
    INNER JOIN `kep_v2`.`teachers` 
        ON (`group_teacher_subject`.`teacher_ID` = `teachers`.`Id`)
    INNER JOIN `kep_v2`.`subjects` 
        ON (`group_teacher_subject`.`subject_ID` = `subjects`.`Id`)
WHERE (`teachers`.`Id` =teacId);
    END */$$
DELIMITER ;

/* Procedure structure for procedure `get_user_id` */

/*!50003 DROP PROCEDURE IF EXISTS  `get_user_id` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `get_user_id`(IN inlogin varchar(45))
BEGIN
DECLARE cat VARCHAR(45);
SELECT `Category` FROM `users` WHERE `users`.`login`=inlogin INTO cat;
IF cat = "ROLE_STUDENT"  THEN
	SELECT
    `Id`  
FROM
    `kep_v2`.`students`
WHERE (`login` = inlogin);
ELSEIF  cat = "ROLE_TEACHER" THEN
SELECT
    `Id`
    
FROM
    `kep_v2`.`teachers`
    WHERE (`Login` = inlogin);
else
	SELECT
    0 as `Id`;
END IF;
END */$$
DELIMITER ;

/* Procedure structure for procedure `insert_mark` */

/*!50003 DROP PROCEDURE IF EXISTS  `insert_mark` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_mark`(in mrk int ,in dt varchar(54), in stud int , in tsg int )
BEGIN
delete from `marks` where( `StudentId` = stud and `TeacherSubjectGroupId`= tsg);
if mrk != 0   then	INSERT INTO `marks` (`Mark`, `Date`, `StudentId`, `TeacherSubjectGroupId`) VALUES (mrk, dt, stud, tsg);
end if;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `new_user` */

/*!50003 DROP PROCEDURE IF EXISTS  `new_user` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `new_user`(in id int(11), in role varchar(45),IN login VARCHAR(45),IN pass VARCHAR(45))
BEGIN
    INSERT INTO `users` ( `login`,`password`,`category`)  values (login,pass,role);
    if(role = 'ROLE_TEACHER') then
	UPDATE `teachers` SET `login`=login  WHERE `teachers`.`id` = id;
     elseif(role = 'ROLE_STUDENT') then
	UPDATE `students` SET `login`=login  WHERE `students`.`id` = id;
     end if;
	
    END */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
