package ua.softserve.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import ua.softserve.entities.Subject;

import javax.sql.DataSource;
import java.util.List;

import static ua.softserve.db.SqlQueries.*;

/**
 * Created by troll on 04.01.14.
 */
public class SubjectDao {
    @Autowired
    DataSource dataSource;

    public List<Subject> getGroupSubjects(int grId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Subject> result = (List<Subject>) jdbcTemplate.query(GET_GROUP_SUBJECTS, new Object[]{grId}, Subject.subjectRm);

        if (result.isEmpty()) {
            return null;
        }
        return result;
    }

    public List<Subject> getGroupSubjects(int grId, int sum) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Subject> result = (List<Subject>) jdbcTemplate.query(GET_GROUP_SUBJECTS_SUM, new Object[]{grId, sum}, Subject.subjectRm);

        if (result.isEmpty()) {
            return null;
        }
        return result;
    }

    public Subject getSubject(int subId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Subject> result = jdbcTemplate.query(GET_SUBJECT, new Object[]{subId}, Subject.subjectInfoRm);

        if (result.isEmpty()) {
            return null;
        }
        return (result.get(0));

    }

    public List<Subject> getTeacherGroupSubjects(int teacId, int grId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Subject> result = jdbcTemplate.query(GET_TEACHER_GROUP_SUBJECTS, new Object[]{teacId, grId}, Subject.subjectRm);
        if (result.isEmpty()) {
            return null;
        }
        return (result);
    }

    public List<Subject> getTeacherSubjects(int teacId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Subject> result = jdbcTemplate.query(GET_TEACHER_SUBJECTS, new Object[]{teacId}, Subject.subjectRm);
        if (result.isEmpty())
            return null;
        else

            return result;
    }

    public List<Subject> getAllSubjects() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Subject> result = jdbcTemplate.query(GET_ALL_SUBJECTS, Subject.subjectRm);

        if (result.isEmpty())
            return null;
        else

            return result;

    }

    public List<Subject> getSpecSubjects(Integer spId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Subject> result = jdbcTemplate.query(GET_SPEC_SUBJECTS, Subject.subjectInfoRm, spId);

        if (result.isEmpty())
            return null;
        else

            return result;

    }

    public List<Subject> getAllSubjectsInfo() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Subject> result = jdbcTemplate.query(GET_ALL_SUBJECTS, Subject.subjectInfoRm);

        if (result.isEmpty())
            return null;
        else

            return result;

    }


    public boolean updSubject(int id, String name, String shortcut, double ects, String sums, String cform, int specId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        int result = jdbcTemplate.update(UPDATE_SUBJECT, name, shortcut, ects, sums, cform, specId, id);

        if (result > 0)
            return true;
        else return false;
    }

    public boolean updSubject(Subject subj) {
        return updSubject(subj.getId(), subj.getName(), subj.getShortcut(), subj.getEcts(), subj.getsSums(), subj.getControlForm(), subj.getSpetiality().getId());
    }

    public boolean addSubject(String name, String shortcut, double ects, String sums, String cform, int specId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        int result = jdbcTemplate.update(INSERT_SUBJECT, name, shortcut, ects, sums, cform, specId);

        if (result > 0)
            return true;
        else return false;
    }

    public boolean addSubject(Subject subj) {
        return addSubject(subj.getName(), subj.getShortcut(), subj.getEcts(), subj.getsSums(), subj.getControlForm(), subj.getSpetiality().getId());
    }

}
