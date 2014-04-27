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

insert  into `group_teacher_subject`(`id`,`teacher_ID`,`group_ID`,`subject_ID`,`sumestr`,`Years`) values (20,10,8,16,1,'2000-2000'),(21,10,8,15,1,'2000-2000'),(22,13,8,13,1,'2000-2000'),(23,11,9,9,1,'2000-2000'),(24,10,9,15,1,'2000-2000'),(25,13,9,17,1,'2000-2000'),(26,12,10,9,1,'2000-2000'),(27,11,10,12,1,'2000-2000'),(28,12,10,11,1,'2000-2000'),(29,12,11,13,1,'2000-2000'),(30,11,11,9,1,'2000-2000'),(31,13,11,17,1,'2000-2000'),(32,10,8,11,1,'2000-2000'),(33,12,8,9,1,'2000-2000');

/*Data for the table `groups` */

insert  into `groups`(`Id`,`Name`,`Degree`,`StartDate`,`FinishDate`,`CuratorId`,`EducYear`) values (8,'ПІ-10-01',3,'2014-03-11','2014-04-07',12,4),(9,'ПІ-10-02',3,NULL,'2014-03-31',11,4),(10,'КІ-10-01',4,NULL,NULL,10,4),(11,'КІ-10-02',4,NULL,NULL,13,4),(12,'ww',4,NULL,NULL,11,1),(13,'df',5,NULL,NULL,11,2),(14,'er',3,'2014-04-16','2014-04-29',11,2);

/*Data for the table `marks` */

insert  into `marks`(`Id`,`Mark`,`StudentId`,`Retake`,`TeacherSubjectGroupId`,`Date`) values (46,10,14,NULL,26,NULL),(47,5,15,NULL,26,NULL),(51,6,9,NULL,20,NULL),(52,12,9,NULL,21,NULL),(53,8,9,NULL,22,NULL),(55,6,13,NULL,23,NULL),(56,9,9,NULL,23,NULL),(62,8,17,NULL,30,'2014-03-11'),(68,2,10,NULL,23,NULL);

/*Data for the table `spec` */

insert  into `spec`(`id`,`Degree`,`Name`,`Viddil`,`ZavViddil`) values (3,6.02041,'Програмування','Бакалаврської підготовки та програмування',13),(4,4.0508,'Комп\'ютерна інженерія','Комп\'ютерних систем',11),(5,0,'dddfdc','dfg',10),(6,1.1,'xcdg','drj',10);

/*Data for the table `students` */

insert  into `students`(`Id`,`GroupId`,`Name`,`Surname`,`Patronimic`,`BDate`,`login`,`Gradebook`,`EducForm`,`EducType`,`Gender`,`Address`,`Phone`) values (9,8,'Олег','Новосад','Ігорович','1994-10-11','olololeg','2344503','денна','державне','ч','ГОА','757013'),(10,9,'Олег','Протас','Ігорович','2014-03-11','prot','2400345','денна','державне','ч','вадллдавв','30403'),(11,9,'Ірина','Кулікова','Володимирівна',NULL,'blondunka','45443','денна','державне','ж','аіплдлів','4523'),(12,9,'Тарас','Пашко','Іванович',NULL,NULL,'4352','денна','платне','ч','апак','45546432'),(13,9,'Христина','Піцьків','Олегівна',NULL,'aa','35245','денна','державне','ж','відлаові','342654262'),(14,10,'Олег','Цюцьмаць','Ігорович',NULL,NULL,'4е623','','','','',''),(15,10,'Назар','Чорній','Олександрович',NULL,NULL,'42к554','','','','',''),(16,11,'Станіслав','Адаменко','Валерійович',NULL,NULL,'кку434','','','','',''),(17,11,'Петро','Скрипник','Ігорович',NULL,NULL,'валп54','','','','','');

/*Data for the table `subjects` */

insert  into `subjects`(`Id`,`Name`,`specId`,`ECTSCredits`,`Hours`,`ControlForm`) values (9,'ООП',3,43.2,44,'Іспит'),(10,'Основи патентознавства',3,12.4,2,'Д/З'),(11,'Економіка і організація виробництва',3,18.2,16,'Д/З'),(12,'Основи маркетингу',3,14.8,24,'Д/З'),(13,'Соціологія',3,10,8,'Залік'),(14,'Групова динаміка і комунікації',3,34.1,36,'Залік'),(15,'Розробка WEB-застосувань',3,53.2,68,'Іспит'),(16,'Організація комп\'ютерних мереж',3,74.2,64,'Іспит'),(17,'Інструментальні засоби візуального програмування',3,35.6,48,'Іспит'),(20,'ООП',4,43.2,44,'Іспит'),(21,'Основи патентознавства',4,12.4,2,'Д/З'),(22,'Економіка і організація виробництва',4,18.2,16,'Д/З'),(23,'Основи маркетингу',5,14.8,24,'Д/З'),(24,'Соціологія',4,10,8,'Залік'),(25,'Групова динаміка і комунікації',4,34.1,36,'Залік'),(26,'Розробка WEB-застосувань',4,53.2,68,'Іспит'),(27,'Організація комп\'ютерних мереж',4,74.2,64,'Іспит'),(28,'Інструментальні засоби візуального програмування',4,35.6,48,'Іспит');

/*Data for the table `teachers` */

insert  into `teachers`(`Id`,`Name`,`Surname`,`Patronimic`,`Login`,`Spec`,`Dyplom`,`Category`,`Degree`) values (10,'Юрій','Семеген','Володимирович','sem',NULL,NULL,NULL,NULL),(11,'Ігор','Левицький','Володимирович','lev',NULL,NULL,NULL,NULL),(12,'Данило','Кучеренко','Іванович','kuk',NULL,NULL,NULL,NULL),(13,'Світлана','Гнатюк','Олексіївна','dldl',NULL,NULL,NULL,NULL),(14,'','','',NULL,NULL,NULL,NULL,NULL),(15,'','','',NULL,NULL,NULL,NULL,NULL);

/*Data for the table `users` */

insert  into `users`(`Login`,`Password`,`Category`) values ('aa','4124bc0a9335c27f086f24ba207a4912','ROLE_STUDENT'),('admin','202cb962ac59075b964b07152d234b70','ROLE_ADMIN'),('admin1','202cb962ac59075b964b07152d234b70','ROLE_ADMIN'),('blondunka','202cb962ac59075b964b07152d234b70','ROLE_STUDENT'),('dldl','69fa3c66131dc47127f52a671459646f','ROLE_TEACHER'),('kuk','698d51a19d8a121ce581499d7b701668','ROLE_TEACHER'),('lev','202cb962ac59075b964b07152d234b70','ROLE_TEACHER'),('olololeg','202cb962ac59075b964b07152d234b70','ROLE_STUDENT'),('prot','202cb962ac59075b964b07152d234b70','ROLE_STUDENT'),('sem','e5bb23797bfea314a3db43d07dbd6a74','ROLE_TEACHER');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
