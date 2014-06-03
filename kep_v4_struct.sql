/*
SQLyog Ultimate v10.42 
MySQL - 5.5.33 : Database - kep_v4
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`kep_v4` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `kep_v4`;

/*Table structure for table `group_teacher_subject` */

DROP TABLE IF EXISTS `group_teacher_subject`;

CREATE TABLE `group_teacher_subject` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `teacher_ID` int(11) NOT NULL,
  `group_ID` int(11) NOT NULL,
  `subject_ID` int(11) NOT NULL,
  `sumestr` int(11) DEFAULT NULL,
  `Years` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_gts_teacher` (`teacher_ID`),
  KEY `fk_gts_group` (`group_ID`),
  KEY `fk_gts_subject` (`subject_ID`),
  CONSTRAINT `fk_gts_subject` FOREIGN KEY (`subject_ID`) REFERENCES `subjects` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_gts_teacher` FOREIGN KEY (`teacher_ID`) REFERENCES `teachers` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=159 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

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
  CONSTRAINT `marks_ibfk_1` FOREIGN KEY (`TeacherSubjectGroupId`) REFERENCES `group_teacher_subject` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=162 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

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
  `status` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `StudGroup_idx` (`GroupId`),
  KEY `login` (`login`),
  CONSTRAINT `students_ibfk_1` FOREIGN KEY (`login`) REFERENCES `users` (`Login`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `StudGroup` FOREIGN KEY (`GroupId`) REFERENCES `groups` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

/*Table structure for table `subjects` */

DROP TABLE IF EXISTS `subjects`;

CREATE TABLE `subjects` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL,
  `specId` int(11) DEFAULT NULL,
  `ECTSCredits` double DEFAULT NULL,
  `Hours` int(11) DEFAULT NULL,
  `ControlForm` varchar(45) DEFAULT NULL,
  `sums` varchar(100) DEFAULT NULL,
  `shortcut` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `specId` (`specId`),
  CONSTRAINT `subjects_ibfk_1` FOREIGN KEY (`specId`) REFERENCES `spec` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `Login` varchar(45) NOT NULL,
  `Password` varchar(32) NOT NULL,
  `Category` text,
  PRIMARY KEY (`Login`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/* Procedure structure for procedure `get_group_subjects` */

/*!50003 DROP PROCEDURE IF EXISTS  `get_group_subjects` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `get_group_subjects`(in grId int)
BEGIN
SELECT
    `subjects`.*
FROM
    `group_teacher_subject`
    INNER JOIN `kep_v4`.`subjects` 
        ON (`group_teacher_subject`.`subject_ID` = `subjects`.`Id`)
    INNER JOIN `groups` 
        ON (`group_teacher_subject`.`group_ID` = `groups`.`Id`)
WHERE (`groups`.`Id` =grId)
Group by `subjects`.`Id`;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `get_group_subjects_sum` */

/*!50003 DROP PROCEDURE IF EXISTS  `get_group_subjects_sum` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `get_group_subjects_sum`(IN grId INT, in sumestr int)
BEGIN
SELECT
    `subjects`.*
FROM
    `kep_v4`.`group_teacher_subject`
    INNER JOIN `kep_v4`.`subjects` 
        ON (`group_teacher_subject`.`subject_ID` = `subjects`.`Id`)
    INNER JOIN `kep_v4`.`groups` 
        ON (`group_teacher_subject`.`group_ID` = `groups`.`Id`)
WHERE (`groups`.`Id` =grId)and(`group_teacher_subject`.`sumestr`  = sumestr );
    END */$$
DELIMITER ;

/* Procedure structure for procedure `get_mark` */

/*!50003 DROP PROCEDURE IF EXISTS  `get_mark` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `get_mark`(in studId int, in subjId int, in sumestr int)
BEGIN
SELECT
    `marks`.`Id`
    , `marks`.`Mark`
    , `marks`.`Date`
FROM
    `kep_v4`.`marks`
    INNER JOIN `kep_v4`.`group_teacher_subject` 
        ON (`marks`.`TeacherSubjectGroupId` = `group_teacher_subject`.`id`)
WHERE (`marks`.`StudentId` =studId
    AND `group_teacher_subject`.`subject_ID` =subjId and `group_teacher_subject`.`sumestr`=sumestr);
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
    `kep_v4`.`group_teacher_subject`
    INNER JOIN `kep_v4`.`teachers` 
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
    `kep_v4`.`group_teacher_subject`
    INNER JOIN `kep_v4`.`groups` 
        ON (`group_teacher_subject`.`group_ID` = `groups`.`Id`)
WHERE (`group_teacher_subject`.`teacher_ID` =teacId)
group by `groups`.`Id`
order by `groups`.`Name`;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `get_teacher_group_subjects` */

/*!50003 DROP PROCEDURE IF EXISTS  `get_teacher_group_subjects` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `get_teacher_group_subjects`(in teacId int, in grId int)
BEGIN
	SELECT distinct
    `subjects`.*
FROM
    `kep_v4`.`group_teacher_subject`
    INNER JOIN `kep_v4`.`teachers` 
        ON (`group_teacher_subject`.`teacher_ID` = `teachers`.`Id`)
    INNER JOIN `kep_v4`.`subjects` 
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
    `subjects`.*
FROM
    `kep_v4`.`group_teacher_subject`
    INNER JOIN `kep_v4`.`teachers` 
        ON (`group_teacher_subject`.`teacher_ID` = `teachers`.`Id`)
    INNER JOIN `kep_v4`.`subjects` 
        ON (`group_teacher_subject`.`subject_ID` = `subjects`.`Id`)
WHERE (`teachers`.`Id` =teacId);
    END */$$
DELIMITER ;

/* Procedure structure for procedure `get_teacher_subject_groups` */

/*!50003 DROP PROCEDURE IF EXISTS  `get_teacher_subject_groups` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `get_teacher_subject_groups`( IN teacId INT, IN subjId INT)
BEGIN
SELECT 
    `groups`.`Id`
    , `groups`.`Name`
    , `groups`.`EducYear`
    ,`groups`.`degree`
FROM
    `kep_v4`.`group_teacher_subject`
    INNER JOIN `kep_v4`.`groups` 
        ON (`group_teacher_subject`.`group_ID` = `groups`.`Id`)
WHERE (`group_teacher_subject`.`teacher_ID` =teacId and `group_teacher_subject`.`subject_ID` =subjId)
GROUP BY `groups`.`Id`
ORDER BY `groups`.`Name`;
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
    `kep_v4`.`students`
WHERE (`login` = inlogin);
ELSEIF  cat = "ROLE_TEACHER" THEN
SELECT
    `Id`
    
FROM
    `kep_v4`.`teachers`
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
