package ua.softserve.db;

/**
 * Created by troll on 03.01.14.
 */
public interface SqlQueries {

    public static final String INSERT_USER = "call `new_user`(?,?,?,?);";
    public static final String DELETE_USER = "DELETE FROM `users` WHERE `login` = ?";

    public static final String DELETE_TGS = "DELETE FROM    `kep_v4`.`group_teacher_subject`WHERE (`teacher_ID` =?    AND `group_ID` =?    AND `subject_ID` =?    AND `sumestr` =?);";
    public static final String UPDATE_TGS = "UPDATE `group_teacher_subject` SET `teacher_ID`=?,`group_ID`=?,`subject_ID`=?  WHERE `group_teacher_subject`.`Id` = ?;";
    public static final String INSERT_TGS = "INSERT INTO `group_teacher_subject` ( `teacher_ID`,`group_ID`,`subject_ID`,`sumestr`)  values (?,?,?,?);";
    public static final String GET_TGS_BY_ID = "SELECT * FROM `group_teacher_subject` WHERE `group_teacher_subject`.`id`=?";
    public static final String GET_TGS_IDS = "SELECT `group_teacher_subject`.`id` FROM `group_teacher_subject` WHERE (`teacher_ID` =?    AND `group_ID` =?    AND `subject_ID` =?);";


    public static final String MARK_INSERT = "call insert_mark(?,?,?,?)";

 //   public static final String GET_ALL_GROUPS = "select `id`,`Name`,`EducYear` from `groups`";
//    public static final String GET_ALL_STUDENTS = "select `Id`, `Name`,`Surname`,`Patronimic`,`gradebook` from `students`";
    public static final  String GET_ID = "call get_user_id(?);";
    public static final String GET_STUDENT_INFO = "select * from `students` WHERE  `id` = ?";
    public static final String GET_GROUP_SUBJECTS = "CALL `get_group_subjects`(?);";
    public static final String GET_GROUP_SUBJECTS_SUM = "CALL `get_group_subjects_sum`(?,?);";
    public static final String GET_GROUP_STUDENTS = "SELECT `id`,`name`,`surname`,`patronimic`,`gradebook` FROM `students` WHERE `groupId` = ?;";
    public static final String GET_SPEC_STUDENTS = "SELECT    * FROM    `students`    INNER JOIN `groups`         ON (`students`.`GroupId` = `groups`.`Id`)WHERE (`groups`.`Degree` =?);";

    public static final String GET_GROUP_STUDENTS_INFO = "SELECT * FROM `students` WHERE `groupId` = ?;";
    public static final String GET_TEACHER_BY_SUBJ = "call `get_teacher`(?,?);";
    public static final String GET_TEACHER_INFO = "select * from `teachers` where `id` = ?";
    public static final String GET_SUBJECT = "select * from `subjects` where `Id` = ?";
    public static final String GET_SUMESTERS = "SELECT    `sumestr`FROM    `group_teacher_subject`WHERE (`teacher_ID` =?    AND `group_ID` =?    AND `subject_ID` =?);";
    public static final String GET_MARK = "CALL `get_mark`(?,?,?);";
    public static final String GET_GROUP_BY_CURATOR = "select `id`, `name`, `educYear`,`degree` from `groups` where `curatorId` = ?";
    public static final String GET_TEACHER_GROUPS = "CALL `get_teacher_groups`(?);";
    public static final String GET_TEACHER_GROUP_SUBJECTS = "CALL `get_teacher_group_subjects`(?,?)";
    public static final String GET_TEACHER_SUBJECTS = "CALL `get_teacher_subjects`(?)";
    public static final String GET_TEACHER_SUBJECT_GROUP_ID = "SELECT `id`FROM `group_teacher_subject` WHERE (`teacher_ID` =? AND `group_ID` =? AND `subject_ID` =? AND `sumestr`=?);";
    public static final String GET_ALL_TEACHERS = "select * from `teachers`";
    public static final String GET_ALL_SUBJECTS = "select * from `subjects`";
    public static final String GET_SPEC_SUBJECTS = "select * from `subjects` where `specId` = ?";
    public static final String GET_ALL_SPECS = "select * from `spec`";
    public static final String GET_ALL_GROUPS = "select * from `groups`";
    public static final String GET_ALL_STUDENTS = "select * from `students`";
    public static final String GET_GROUP_BY_ID = "select `id`,`name`,`educYear`,`degree` from `groups` where `groups`.`id` = ?";
    public static final String GET_TGS_FOR_TEACHER = "SELECT `id`,`group_ID`,`subject_ID`, `sumestr` FROM `group_teacher_subject` WHERE `group_teacher_subject`.`teacher_ID` = ?";
    public static final String GET_TGS_FOR_GROUP = "SELECT `id`, `teacher_ID`,`subject_ID`, `sumestr` FROM `group_teacher_subject` WHERE `group_teacher_subject`.`group_ID` = ?";

    public static final String GET_ALL_USERS = "select `login` from `users`";
    public static final String GET_SPEC_GROUPS = "select * from `groups` where `Degree` = ?";
    public static final String GET_SPEC = "select * from `spec` where `id` = ?";

    public static final String UPDATE_TEACHER = "UPDATE `teachers` SET `Name` = ?,`Surname`=?,`Patronimic`=?  WHERE `teachers`.`Id` = ?;";
    public static final String INSERT_TEACHER = "INSERT INTO `teachers` ( `Name`,`Surname`,`Patronimic`)  values (?,?,?);";

    public static final String UPDATE_STUDENT = "UPDATE `students` SET `Name` = ?,`Surname`=?,`Patronimic`=?,`groupId`=?,`BDate`=?,`Gradebook`=?,`EducForm`=?,`EducType`=?,`Gender`=?,`Address`=?,`Phone`=?  WHERE `students`.`Id` = ?;";
    public static final String INSERT_STUDENT = "INSERT INTO `students` ( `Name`,`Surname`,`Patronimic`,`groupId`,`bdate`,`gradebook`,`EducForm`,`EducType`,`Gender`,`Address`,`Phone`)  values (?,?,?,?,?,?,?,?,?,?,?);";

    public static final String UPDATE_SUBJECT = "UPDATE `subjects` SET `Name` = ?,`ECTSCredits`=?,`Hours`=?,`ControlForm`=?,`specId`=?  WHERE `subjects`.`Id` = ?;";
    public static final String INSERT_SUBJECT = "INSERT INTO `subjects` ( `Name`,`ECTSCredits`,`Hours`,`ControlForm`,`specId`)  values (?,?,?,?,?);";

    public static final String UPDATE_SPEC = "UPDATE `spec` SET `Degree` = ?,`Name`=?,`Viddil`=?,`ZavViddil`=?  WHERE `spec`.`Id` = ?;";
    public static final String INSERT_SPEC = "INSERT INTO `spec` ( `Degree`,`Name`,`Viddil`,`ZavViddil`)  values (?,?,?,?);";

    public static final String UPDATE_GROUP = "UPDATE `groups` SET `Name`=?,`Degree`=?,`StartDate`=?,`FinishDate`=?,`CuratorId`=?,`EducYear`=?  WHERE `groups`.`Id` = ?;";
    public static final String INSERT_GROUP = "INSERT INTO `groups` ( `Name`,`Degree`,`StartDate`,`FinishDate`,`CuratorId`,`EducYear`)  values (?,?,?,?,?,?);";

    public static final String UPDATE_USER = "UPDATE `users` SET `password`=?  WHERE `users`.`login` = ?;";




}

