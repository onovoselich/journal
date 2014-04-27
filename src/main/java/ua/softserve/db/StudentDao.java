package ua.softserve.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import ua.softserve.entities.Student;

import javax.sql.DataSource;
import java.util.List;

import static ua.softserve.db.SqlQueries.*;
import static ua.softserve.db.UserDao.getId;

/**
 * Created by troll on 04.01.14.
 */
public class StudentDao {
    @Autowired
    DataSource dataSource;



    public Student getStudentInfo(String login) {
        return getStudentInfo(getId(login, dataSource ));
    }

    public Student getStudentInfo(int id) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Student> result = jdbcTemplate.query(GET_STUDENT_INFO,new Object[]{id},Student.studentInfoRm);

        if(result.isEmpty()){
            return null;
        }
        return (result.get(0));
    }

    public List<Student> getGroupStudents(int grId){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Student> result = jdbcTemplate.query(GET_GROUP_STUDENTS,new Object[]{grId},Student.studentRm);

        if(result.isEmpty()){
            return null;
        }
        return (result);
    }
    public List<Student> getGroupStudentsInfo(int grId){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Student> result = jdbcTemplate.query(GET_GROUP_STUDENTS_INFO,new Object[]{grId},Student.studentInfoRm);

        if(result.isEmpty()){
            return null;
        }
        return (result);
    }

    public boolean updStudent(Integer id, String name, String surname, String patronimic, Integer groupId, String gender, String bDate, String gradebook, String educForm, String edukType, String phone, String address) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        int result = jdbcTemplate.update(UPDATE_STUDENT,name,surname,patronimic,groupId,bDate,gradebook,educForm,edukType,gender,address,phone,id );

        if (result>0)
            return true;
        else return false;

    }

    public boolean newStudent(String name, String surname, String patronimic, Integer groupId, String gender, String bDate, String gradebook, String educForm, String edukType, String phone, String address) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        int result = jdbcTemplate.update(INSERT_STUDENT,name,surname,patronimic,groupId,bDate,gradebook,educForm,edukType,gender,address,phone);

        if (result>0)
            return true;
        else return false;
    }
    public boolean newStudent(Student stud) {
        return newStudent(stud.getName(),
                stud.getSurname(),
                stud.getPatronimic(),
                stud.getGroupId(),
                stud.getGender(),
                stud.getbDate(),
                stud.getGradebook(),
                stud.getEducForm(),
                stud.getEdukType(),
                stud.getPhone(),
                stud.getAddress()
                );
    }


    public List<Student> getAllStudents() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Student> result = jdbcTemplate.query(GET_ALL_STUDENTS,Student.studentInfoRm);

        if(result.isEmpty()){
            return null;
        }
        return (result);
    }


    public boolean updStudent(Student stud) {
        return updStudent(stud.getId(),
                stud.getName(),
                stud.getSurname(),
                stud.getPatronimic(),
                stud.getGroupId(),
                stud.getGender(),
                stud.getbDate(),
                stud.getGradebook(),
                stud.getEducForm(),
                stud.getEdukType(),
                stud.getPhone(),
                stud.getAddress()
        );

    }
}