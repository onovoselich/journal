package ua.softserve.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import ua.softserve.entities.Group;
import ua.softserve.entities.Subject;
import ua.softserve.entities.Teacher;

import javax.sql.DataSource;
import java.util.LinkedList;
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

    public Map<Integer,Group.Tgs> getGroupTgs(int teacId){


        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String,Object>> data  = jdbcTemplate.queryForList(GET_TGS_FOR_GROUP,teacId);
        if(data.isEmpty())
            return null;
        Map<Integer,Group.Tgs> result = new TreeMap<Integer,Group.Tgs>();
        outerloop:
        for(Map m:data){

                for (Map.Entry<Integer,Group.Tgs> tr:result.entrySet()){
                    if (tr.getValue().getX().getId().equals((Integer)m.get("subject_ID"))){
                        tr.getValue().getZ().add( (Integer)m.get("sumestr"));
                        continue outerloop;
                    }
                }
                Subject sub = jdbcTemplate.queryForObject(GET_SUBJECT, Subject.subjectRm, m.get("subject_ID"));
                Teacher teac = jdbcTemplate.queryForObject(GET_TEACHER_INFO, Teacher.teacherRm, m.get("teacher_ID"));
            List<Integer> sum = new LinkedList<Integer>();
            sum.add((Integer)m.get("sumestr"));
                result.put((Integer) m.get("id"), new Group.Tgs(sub, teac, sum));

            }


        if (result.isEmpty())
            return null;
        return result;

    }

    public Map<Integer,Teacher.Tgs> getTeacherTgs(int teacId){

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String,Object>> data  = jdbcTemplate.queryForList(GET_TGS_FOR_TEACHER,teacId);
        if(data.isEmpty())
            return null;
        Map<Integer,Teacher.Tgs> result = new TreeMap<Integer,Teacher.Tgs>();
        outerloop:
        for(Map m:data){
            for (Map.Entry<Integer,Teacher.Tgs> tr:result.entrySet()){
                if (tr.getValue().getY().getId().equals((Integer)m.get("subject_ID"))){
                    tr.getValue().getZ().add( (Integer)m.get("sumestr"));
                    continue outerloop;
                }
            }

            Group gr = jdbcTemplate.queryForObject(GET_GROUP_BY_ID,Group.groupRm,m.get("group_ID"));
            Subject sub = jdbcTemplate.queryForObject(GET_SUBJECT,Subject.subjectRm,m.get("subject_ID"));
            List<Integer> sum = new LinkedList<Integer>();
            sum.add((Integer)m.get("sumestr"));
            result.put((Integer) m.get("id"), new Teacher.Tgs(gr, sub,sum));
        }

        if (result.isEmpty())
            return null;
        return result;
    }

    public Integer getTeacherGroupSubject(int teacId, int grId, int subjId,int sum){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Integer> result =  jdbcTemplate.queryForList(GET_TEACHER_SUBJECT_GROUP_ID, new Object[]{teacId, grId, subjId,sum},Integer.class);
        if(result.isEmpty())
            return null;
        else
            return (result.get(0));
    }

    public boolean addTgs(Integer teacId, Integer grId, Integer subjectId,Integer sum) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        if(jdbcTemplate.update(INSERT_TGS,teacId,grId,subjectId,sum)==0)
            return false;
        else
           return true;
    }

    public boolean updTgs(Integer id, Integer teacId, Integer grId, Integer subjectId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        try{
                Map<String,Object> old = jdbcTemplate.queryForMap(GET_TGS_BY_ID, id);


                List<Integer> idLst = jdbcTemplate.queryForList(GET_TGS_IDS, Integer.class, new Object[]{old.get("teacher_ID"),old.get("group_ID"),old.get("subject_ID")});

                for(Integer i:idLst)
                    jdbcTemplate.update(UPDATE_TGS,teacId,grId,subjectId,i);
        }catch (Exception e){
            return false;
        }


        return true;

    }

    public List<Integer> getSumesters(int teacId,int groupId, int subjId){

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Integer> result = jdbcTemplate.queryForList(GET_SUMESTERS,Integer.class,new Object[]{teacId,groupId,subjId});
        if (result.isEmpty())
            return null;
        return result;
    }

    public boolean delTgs(int teacId,int grId,int subjId,int sum){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        if (jdbcTemplate.update(DELETE_TGS,teacId,grId,subjId,sum)==0)
            return false;


        return true;
    }

    public List<Integer> getSumesters(Integer groupId, Integer subjId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Integer> result = jdbcTemplate.queryForList(GET_SUMESTERS_NO_TEAC,Integer.class,new Object[]{groupId,subjId});
        if (result.isEmpty())
            return null;
        return result;
    }
    public Integer getGroupId(Integer tgsId){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Integer> result = jdbcTemplate.queryForList(GET_GROUP_ID,Integer.class,new Object[]{tgsId});
        if (result.isEmpty())
            return null;
        return result.get(0);
    }
    public Integer getSubjectId(Integer tgsId){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Integer> result = jdbcTemplate.queryForList(GET_SUBJECT_ID,Integer.class,new Object[]{tgsId});
        if (result.isEmpty())
            return null;
        return result.get(0);
    }

public Integer getSum(Integer tgsId){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Integer> result = jdbcTemplate.queryForList(GET_SUM,Integer.class,new Object[]{tgsId});
        if (result.isEmpty())
        return null;
        return result.get(0);
        }
        }