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

insert  into `group_teacher_subject`(`id`,`teacher_ID`,`group_ID`,`subject_ID`,`sumestr`,`Years`) values (139,11,9,9,1,NULL),(140,11,9,9,2,NULL),(141,11,9,9,3,NULL),(142,11,9,9,4,NULL),(143,11,9,10,3,NULL),(144,11,9,10,4,NULL),(145,11,9,11,5,NULL),(146,11,9,11,6,NULL),(147,11,9,12,4,NULL),(148,11,9,12,5,NULL),(149,11,9,12,6,NULL),(150,11,9,13,6,NULL),(151,11,9,13,7,NULL),(152,11,9,13,8,NULL),(153,11,9,14,2,NULL),(154,11,9,14,3,NULL),(155,11,9,15,1,NULL),(156,11,9,15,2,NULL),(157,11,9,15,3,NULL),(158,11,9,15,4,NULL);

/*Data for the table `groups` */

insert  into `groups`(`Id`,`Name`,`Degree`,`StartDate`,`FinishDate`,`CuratorId`,`EducYear`) values (8,'ПІ-10-01',3,'2014-03-11','2014-04-07',12,4),(9,'ПІ-10-02',3,'2014-02-10','2014-03-31',11,4),(10,'КІ-10-01',4,'2014-05-12','2014-05-05',10,4),(11,'КІ-10-02',4,'2014-05-06','2014-05-05',16,4),(12,'ww',4,'2014-05-05','2014-05-11',11,1),(13,'df',5,'2014-05-20','2014-05-06',11,2),(14,'er',3,'2014-04-16','2014-04-29',11,2),(15,'fgf',7,'2014-05-26','2014-05-26',11,2);

/*Data for the table `marks` */

insert  into `marks`(`Id`,`Mark`,`StudentId`,`Retake`,`TeacherSubjectGroupId`,`Date`) values (147,1,11,NULL,142,'2014-05-14'),(148,4,9,NULL,142,'2014-05-15'),(149,6,12,NULL,142,'2014-05-26'),(150,1,11,NULL,154,NULL),(151,4,9,NULL,154,NULL),(152,6,12,NULL,154,NULL),(153,4,10,NULL,154,NULL),(154,5,13,NULL,154,NULL),(155,4,11,NULL,143,NULL),(156,5,9,NULL,143,NULL),(157,2,12,NULL,143,NULL),(158,7,10,NULL,143,NULL),(159,2,13,NULL,143,NULL),(160,5,12,NULL,157,NULL),(161,9,10,NULL,157,NULL);

/*Data for the table `spec` */

insert  into `spec`(`id`,`Degree`,`Name`,`Viddil`,`ZavViddil`) values (3,6.02041,'Програмування','Бакалаврської підготовки та програмування',13),(4,4.0509,'Комп\'ютерна інженерія','Комп\'ютерних систем',11),(5,0,'dddfdc','dfg',13),(6,0,'xcdgfv','drj',10),(7,1.33,'fd','fd3',12);

/*Data for the table `students` */

insert  into `students`(`Id`,`GroupId`,`Name`,`Surname`,`Patronimic`,`BDate`,`login`,`Gradebook`,`EducForm`,`EducType`,`Gender`,`Address`,`Phone`,`status`) values (9,9,'Олег','Новосад','Ігорович','1994-10-11','olololeg','2344503','денна','державне','ч','ГОА','757013',NULL),(10,9,'Олег','Протас','Ігорович','2014-03-11','prot','2400345','денна','державне','ч','вадллдавв','30403',NULL),(11,9,'Ірина','Кулікова','Володимирівна',NULL,'blondunka','45443','денна','державне','ж','аіплдлів','4523',NULL),(12,9,'Тарас','Пашко','Іванович',NULL,NULL,'4352','денна','платне','ч','апак','45546432',NULL),(13,9,'Христина','Піцьків','Олегівна',NULL,'aa','35245','денна','державне','ж','відлаові','342654262',NULL),(14,10,'Олег','Цюцьмаць','Ігорович',NULL,'ggg','4е623','','','','','',NULL),(15,10,'Назар','Чорній','Олександрович',NULL,NULL,'42к554','','','','','',NULL),(16,11,'Станіслав','Адаменко','Валерійович',NULL,'dfg','кку434','','','','','',NULL),(17,11,'Петро','Скрипник','Ігорович',NULL,NULL,'валп54','','','','','',NULL);

/*Data for the table `subjects` */

insert  into `subjects`(`Id`,`Name`,`specId`,`ECTSCredits`,`Hours`,`ControlForm`,`sums`,`shortcut`) values (9,'ООП',3,43.2,44,'Іспит','{1=11, 2=22, 3=33, 4=44}',NULL),(10,'Основи патентознавства',3,12.4,2,'Д/З','{3=12, 4=33}',NULL),(11,'Економіка і організація виробництва',3,18.2,16,'Д/З','{5=12, 6=123}',NULL),(12,'Основи маркетингу',3,14.8,24,'Д/З','{4=3, 5=34, 6=65}',NULL),(13,'Соціологія',3,10,8,'Іспит','{6=12, 7=43, 8=5}',NULL),(14,'Групова динаміка і комунікації',3,34.1,36,'Залік','{2=23, 3=32}',NULL),(15,'Розробка WEB-застосувань',3,53.2,68,'Іспит','{1=32, 2=456, 3=46, 4=465}',NULL),(16,'Організація комп\'ютерних мереж',3,74.2,64,'Іспит','{5=35, 6=4, 7=456, 8=455}',NULL),(17,'Інструментальні засоби візуального програмування',3,35.6,48,'Іспит','{2=4343, 3=34, 4=54}',NULL),(20,'ООП',4,43.2,45,'Іспит',NULL,NULL),(21,'Основи патентознавства',4,12.4,2,'Д/З',NULL,NULL),(22,'Економіка і організація виробництва',4,18.2,16,'Д/З',NULL,NULL),(23,'Основи маркетингу',5,14.8,24,'Д/З',NULL,NULL),(24,'Соціологія',4,10,8,'Залік',NULL,NULL),(25,'Групова динаміка і комунікації',4,34.1,36,'Залік',NULL,NULL),(26,'Розробка WEB-застосувань',4,53.2,68,'Іспит',NULL,NULL),(27,'Організація комп\'ютерних мереж',4,74.2,64,'Іспит',NULL,NULL),(28,'Інструментальні засоби візуального програмування',4,35.6,48,'Іспит',NULL,NULL),(29,'dfd',6,2,5,'Залік',NULL,NULL),(30,'sd',4,3,NULL,'Залік','',NULL),(31,'fx',4,3,NULL,'Залік','{1=3, 2=2}',NULL);

/*Data for the table `teachers` */

insert  into `teachers`(`Id`,`Name`,`Surname`,`Patronimic`,`Login`,`Spec`,`Dyplom`,`Category`,`Degree`) values (10,'Юрій','Семеген','Володимирович','sem',NULL,NULL,NULL,NULL),(11,'Ігор','Левицький','Володимирович','lev',NULL,NULL,NULL,NULL),(12,'Данило','Кучеренко','Івановичa','kuk',NULL,NULL,NULL,NULL),(13,'Світлана','Гнатюк','Олексіївна','dldl',NULL,NULL,NULL,NULL),(14,'','','','uj',NULL,NULL,NULL,NULL),(15,'','','','fg',NULL,NULL,NULL,NULL),(16,'xsllx','dc','dldld','xc',NULL,NULL,NULL,NULL);

/*Data for the table `users` */

insert  into `users`(`Login`,`Password`,`Category`) values ('aa','202cb962ac59075b964b07152d234b70','ROLE_STUDENT'),('admin','202cb962ac59075b964b07152d234b70','ROLE_ADMIN'),('admin1','202cb962ac59075b964b07152d234b70','ROLE_ADMIN'),('blondunka','202cb962ac59075b964b07152d234b70','ROLE_STUDENT'),('dfg','60ee89fb4e82c6464721527041131ba4','ROLE_STUDENT'),('dldl','202cb962ac59075b964b07152d234b70','ROLE_TEACHER'),('fg','8fa14cdd754f91cc6554c9e71929cce7','ROLE_TEACHER'),('ggg','5e36941b3d856737e81516acd45edc50','ROLE_STUDENT'),('kuk','202cb962ac59075b964b07152d234b70','ROLE_TEACHER'),('lev','202cb962ac59075b964b07152d234b70','ROLE_TEACHER'),('olololeg','202cb962ac59075b964b07152d234b70','ROLE_STUDENT'),('prot','202cb962ac59075b964b07152d234b70','ROLE_STUDENT'),('sem','202cb962ac59075b964b07152d234b70','ROLE_TEACHER'),('uj','d41d8cd98f00b204e9800998ecf8427e','ROLE_TEACHER'),('xc','e0323a9039add2978bf5b49550572c7c','ROLE_TEACHER');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
