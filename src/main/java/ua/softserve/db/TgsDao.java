package ua.softserve.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import ua.softserve.entities.Group;
import ua.softserve.entities.Subject;
import ua.softserve.entities.Teacher;
import ua.softserve.logic.Tuple;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static ua.softserve.db.SqlQueries.*;

/**
 * Created by troll on 20.03.14.
 */
public class TgsDao {
    @Autowired
    DataSource dataSource;

    public Map<Integer,Tuple<Subject,Teacher>> getGroupTgs(int teacId){


        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String,Object>> data  = jdbcTemplate.queryForList(GET_TGS_FOR_GROUP,teacId);
        if(data.isEmpty())
            return null;
        Map<Integer,Tuple<Subject,Teacher>> result = new TreeMap<Integer,Tuple<Subject, Teacher>>();
        for(Map m:data){
            Subject sub = jdbcTemplate.queryForObject(GET_SUBJECT, Subject.subjectRm, m.get("subject_ID"));
            Teacher teac = jdbcTemplate.queryForObject(GET_TEACHER_INFO, Teacher.teacherRm, m.get("teacher_ID"));
            result.put((Integer) m.get("id"), new Tuple<Subject, Teacher>(sub, teac));
        }

        if (result.isEmpty())
            return null;
        return result;

    }

    public Map<Integer,Tuple<Group,Subject>> getTeacherTgs(int teacId){

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String,Object>> data  = jdbcTemplate.queryForList(GET_TGS_FOR_TEACHER,teacId);
        if(data.isEmpty())
            return null;
        Map<Integer,Tuple<Group,Subject>> result = new TreeMap<Integer,Tuple<Group, Subject>>();
        for(Map m:data){
            Group gr = jdbcTemplate.queryForObject(GET_GROUP_BY_ID,Group.groupRm,m.get("group_ID"));
            Subject sub = jdbcTemplate.queryForObject(GET_SUBJECT,Subject.subjectRm,m.get("subject_ID"));
            result.put((Integer) m.get("id"), new Tuple<Group, Subject>(gr, sub));
        }

        if (result.isEmpty())
            return null;
        return result;
    }


    public boolean addTgs(Integer teacId, Integer grId, Integer subjectId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        if(jdbcTemplate.update(INSERT_TGS,teacId,grId,subjectId)==0)
            return false;
        else
           return true;
    }

    public boolean updTgs(Integer id, Integer teacId, Integer grId, Integer subjectId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        if (jdbcTemplate.update(UPDATE_TGS,teacId,grId,subjectId,id)==0)
            return false;


        return true;

    }
}
