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

/*Data for the table `group_teacher_subject` */

insert  into `group_teacher_subject`(`id`,`teacher_ID`,`group_ID`,`subject_ID`,`sumestr`,`Years`) values (21,10,8,15,1,'2000-2000'),(22,13,8,13,1,'2000-2000'),(23,11,9,9,1,'2000-2000'),(24,11,9,15,1,'2000-2000'),(25,11,9,17,1,'2000-2000'),(26,12,10,21,1,'2000-2000'),(27,11,10,22,1,'2000-2000'),(28,12,10,26,1,'2000-2000'),(29,12,11,27,1,'2000-2000'),(30,11,11,25,1,'2000-2000'),(31,13,11,20,1,'2000-2000'),(32,10,8,11,1,'2000-2000'),(33,12,8,9,1,'2000-2000'),(34,10,8,17,1,NULL),(37,10,11,22,3,NULL),(38,10,11,22,4,NULL),(41,10,8,17,3,NULL),(42,10,8,17,8,NULL),(43,11,8,9,1,NULL),(44,11,8,11,4,NULL),(45,11,8,11,1,NULL),(46,11,8,11,2,NULL),(47,11,8,11,3,NULL),(48,13,8,16,7,NULL),(49,13,8,16,8,NULL),(50,11,11,25,3,NULL),(51,11,11,25,8,NULL),(52,11,9,15,2,NULL),(53,11,9,12,3,NULL),(54,11,9,12,4,NULL),(55,11,9,16,5,NULL),(56,11,9,15,5,NULL),(57,11,9,11,8,NULL),(58,11,9,10,8,NULL),(59,10,9,14,5,NULL),(60,10,9,14,6,NULL),(61,10,9,14,7,NULL),(62,11,9,15,8,NULL),(63,11,9,17,3,NULL),(64,11,9,17,8,NULL),(65,11,9,16,8,NULL),(66,11,9,9,2,NULL),(67,11,9,9,3,NULL),(68,11,9,9,4,NULL),(69,11,9,9,5,NULL),(70,11,9,9,6,NULL),(71,11,9,9,7,NULL),(72,11,9,9,8,NULL),(73,10,9,14,1,NULL),(74,11,9,10,1,NULL),(75,11,9,11,1,NULL),(76,11,9,16,1,NULL),(77,11,9,12,1,NULL);

/*Data for the table `groups` */

insert  into `groups`(`Id`,`Name`,`Degree`,`StartDate`,`FinishDate`,`CuratorId`,`EducYear`) values (8,'ПІ-10-01',3,'2014-03-11','2014-04-07',12,4),(9,'ПІ-10-02',3,NULL,'2014-03-31',11,4),(10,'КІ-10-01',4,NULL,NULL,10,4),(11,'КІ-10-02',4,NULL,NULL,13,4),(12,'ww',4,NULL,NULL,11,1),(13,'df',5,NULL,NULL,11,2),(14,'er',3,'2014-04-16','2014-04-29',11,2);

/*Data for the table `marks` */

insert  into `marks`(`Id`,`Mark`,`StudentId`,`Retake`,`TeacherSubjectGroupId`,`Date`) values (46,10,14,NULL,26,NULL),(47,5,15,NULL,26,NULL),(52,12,9,NULL,21,NULL),(53,8,9,NULL,22,NULL),(56,9,9,NULL,23,NULL),(62,8,17,NULL,30,'2014-03-11'),(68,2,10,NULL,23,NULL),(70,6,12,NULL,23,NULL),(71,10,12,NULL,52,NULL),(72,8,10,NULL,62,NULL),(73,12,11,NULL,65,NULL),(74,10,12,NULL,65,NULL),(75,12,10,NULL,65,NULL),(76,12,11,NULL,69,NULL),(77,1,12,NULL,57,NULL),(79,11,11,NULL,64,NULL),(81,10,9,NULL,25,NULL),(82,11,9,NULL,63,NULL),(83,12,9,NULL,64,NULL);

/*Data for the table `spec` */

insert  into `spec`(`id`,`Degree`,`Name`,`Viddil`,`ZavViddil`) values (3,6.02041,'Програмування','Бакалаврської підготовки та програмування',13),(4,4.0508,'Комп\'ютерна інженерія','Комп\'ютерних систем',11),(5,0,'dddfdc','dfg',10),(6,1.1,'xcdg','drj',10);

/*Data for the table `students` */

insert  into `students`(`Id`,`GroupId`,`Name`,`Surname`,`Patronimic`,`BDate`,`login`,`Gradebook`,`EducForm`,`EducType`,`Gender`,`Address`,`Phone`) values (9,9,'Олег','Новосад','Ігорович','1994-10-11','olololeg','2344503','денна','державне','ч','ГОА','757013'),(10,9,'Олег','Протас','Ігорович','2014-03-11','prot','2400345','денна','державне','ч','вадллдавв','30403'),(11,9,'Ірина','Кулікова','Володимирівна',NULL,'blondunka','45443','денна','державне','ж','аіплдлів','4523'),(12,9,'Тарас','Пашко','Іванович',NULL,NULL,'4352','денна','платне','ч','апак','45546432'),(13,9,'Христина','Піцьків','Олегівна',NULL,'aa','35245','денна','державне','ж','відлаові','342654262'),(14,10,'Олег','Цюцьмаць','Ігорович',NULL,NULL,'4е623','','','','',''),(15,10,'Назар','Чорній','Олександрович',NULL,NULL,'42к554','','','','',''),(16,11,'Станіслав','Адаменко','Валерійович',NULL,NULL,'кку434','','','','',''),(17,11,'Петро','Скрипник','Ігорович',NULL,NULL,'валп54','','','','','');

/*Data for the table `subjects` */

insert  into `subjects`(`Id`,`Name`,`specId`,`ECTSCredits`,`Hours`,`ControlForm`) values (9,'ООП',3,43.2,44,'Іспит'),(10,'Основи патентознавства',3,12.4,2,'Д/З'),(11,'Економіка і організація виробництва',3,18.2,16,'Д/З'),(12,'Основи маркетингу',3,14.8,24,'Д/З'),(13,'Соціологія',3,10,8,'Залік'),(14,'Групова динаміка і комунікації',3,34.1,36,'Залік'),(15,'Розробка WEB-застосувань',3,53.2,68,'Іспит'),(16,'Організація комп\'ютерних мереж',3,74.2,64,'Іспит'),(17,'Інструментальні засоби візуального програмування',3,35.6,48,'Іспит'),(20,'ООП',4,43.2,44,'Іспит'),(21,'Основи патентознавства',4,12.4,2,'Д/З'),(22,'Економіка і організація виробництва',4,18.2,16,'Д/З'),(23,'Основи маркетингу',5,14.8,24,'Д/З'),(24,'Соціологія',4,10,8,'Залік'),(25,'Групова динаміка і комунікації',4,34.1,36,'Залік'),(26,'Розробка WEB-застосувань',4,53.2,68,'Іспит'),(27,'Організація комп\'ютерних мереж',4,74.2,64,'Іспит'),(28,'Інструментальні засоби візуального програмування',4,35.6,48,'Іспит');

/*Data for the table `teachers` */

insert  into `teachers`(`Id`,`Name`,`Surname`,`Patronimic`,`Login`,`Spec`,`Dyplom`,`Category`,`Degree`) values (10,'Юрій','Семеген','Володимирович','sem',NULL,NULL,NULL,NULL),(11,'Ігор','Левицький','Володимирович','lev',NULL,NULL,NULL,NULL),(12,'Данило','Кучеренко','Іванович','kuk',NULL,NULL,NULL,NULL),(13,'Світлана','Гнатюк','Олексіївна','dldl',NULL,NULL,NULL,NULL),(14,'','','',NULL,NULL,NULL,NULL,NULL),(15,'','','',NULL,NULL,NULL,NULL,NULL);

/*Data for the table `users` */

insert  into `users`(`Login`,`Password`,`Category`) values ('aa','202cb962ac59075b964b07152d234b70','ROLE_STUDENT'),('admin','202cb962ac59075b964b07152d234b70','ROLE_ADMIN'),('admin1','202cb962ac59075b964b07152d234b70','ROLE_ADMIN'),('blondunka','202cb962ac59075b964b07152d234b70','ROLE_STUDENT'),('dldl','202cb962ac59075b964b07152d234b70','ROLE_TEACHER'),('kuk','202cb962ac59075b964b07152d234b70','ROLE_TEACHER'),('lev','202cb962ac59075b964b07152d234b70','ROLE_TEACHER'),('olololeg','202cb962ac59075b964b07152d234b70','ROLE_STUDENT'),('prot','202cb962ac59075b964b07152d234b70','ROLE_STUDENT'),('sem','202cb962ac59075b964b07152d234b70','ROLE_TEACHER');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
