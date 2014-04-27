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

    public List<Subject> getGroupSubjects(int grId){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Subject> result =  (List<Subject>)jdbcTemplate.query(GET_GROUP_SUBJECTS, new Object[]{grId},Subject.subjectRm);

        if(result.isEmpty()){
            return null;
        }
        return result;
    }

    public Subject getSubject(int subId){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Subject> result = jdbcTemplate.query(GET_SUBJECT, new Object[]{subId},Subject.subjectRm);

        if(result.isEmpty()){
             return null;
        }
        return (result.get(0));

    }

    public List<Subject> getTeacherGroupSubjects(int teacId,int grId){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Subject> result = jdbcTemplate.query(GET_TEACHER_GROUP_SUBJECTS,new Object[]{teacId,grId},Subject.subjectRm);
        if(result.isEmpty()){
            return null;
        }
        return (result);
    }

    public List<Subject> getTeacherSubjects(int teacId){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Subject> result = jdbcTemplate.query(GET_TEACHER_SUBJECTS,new Object[]{teacId},Subject.subjectRm);
        if (result.isEmpty())
            return null;
        else

            return  result;
    }

    public List<Subject> getAllSubjects(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Subject> result = jdbcTemplate.query(GET_ALL_SUBJECTS,Subject.subjectInfoRm);

        if (result.isEmpty())
            return null;
        else

            return  result;

    }


    public boolean updSubject(int id, String name, double ects, int hours, String cform) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        int result = jdbcTemplate.update(UPDATE_SUBJECT,name,ects,hours,cform,id);

        if (result>0)
            return true;
        else return false;
    }

    public boolean updSubject(Subject subj) {
        return updSubject(subj.getId(),subj.getName(),subj.getEcts(),subj.getHours(),subj.getControlForm());
    }
    public boolean addSubject(String name, double ects, int hours, String cform) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        int result = jdbcTemplate.update(INSERT_SUBJECT,name,ects,hours,cform);

        if (result>0)
            return true;
        else return false;
    }

    public boolean addSubject(Subject subj) {
        return addSubject(subj.getName(),subj.getEcts(),subj.getHours(),subj.getControlForm());
    }

}
