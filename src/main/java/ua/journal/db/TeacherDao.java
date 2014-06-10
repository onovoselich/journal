package ua.journal.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import ua.journal.entities.Teacher;

import javax.sql.DataSource;
import java.util.List;

import static ua.journal.db.SqlQueries.*;
import static ua.journal.db.UserDao.getId;

/**
 * Created by troll on 04.01.14.
 */
public class TeacherDao {
    @Autowired
    DataSource dataSource;


    public Teacher getTeacher(int subjId, int grId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Teacher> result = jdbcTemplate.query(GET_TEACHER_BY_SUBJ, new Object[]{subjId, grId}, Teacher.teacherRm);

        if (result.isEmpty()) {
            return null;
        }
        return (result.get(0));
    }

    public Teacher getTeacherInfo(String login) {
        return getTeacherInfo(getId(login, dataSource));
    }

    public Teacher getTeacherInfo(int id) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Teacher> result = jdbcTemplate.query(GET_TEACHER_INFO, new Object[]{id}, Teacher.teacherRm);

        if (result.isEmpty())
            return null;
        else
            return (result.get(0));
    }


    public List<Teacher> getAllTeachers() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Teacher> result = jdbcTemplate.query(GET_ALL_TEACHERS, Teacher.teacherInfoRowMapper);

        if (result.isEmpty())
            return null;
        return result;
    }


    public boolean updTeacher(Integer id, String name, String surname, String patronimic) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        int result = jdbcTemplate.update(UPDATE_TEACHER, name, surname, patronimic, id);

        if (result > 0)
            return true;
        else return false;

    }

    public boolean newTeacher(String name, String surname, String patronimic) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        int result = jdbcTemplate.update(INSERT_TEACHER, name, surname, patronimic);

        if (result > 0)
            return true;
        else return false;
    }


    public boolean updTeacher(Teacher teac) {
        return updTeacher(teac.getId(), teac.getName(), teac.getSurname(), teac.getPatronimic());
    }

    public boolean newTeacher(Teacher teac) {
        return newTeacher(teac.getName(), teac.getSurname(), teac.getPatronimic());
    }
}
