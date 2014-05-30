package ua.softserve.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import ua.softserve.entities.Group;

import javax.sql.DataSource;
import java.util.List;

import static ua.softserve.db.SqlQueries.*;

/**
 * Created by troll on 04.01.14.
 */
public class GroupDao {

    @Autowired
    DataSource dataSource;
    @Autowired
    SpecDao specDao ;

    public List<Integer> getSpecsByZavvidd(Integer zavvId){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Integer> result = jdbcTemplate.queryForList(GET_ZAVVID_SPECS, Integer.class, new Object[]{zavvId});
        if(result.isEmpty())
            return null;
        return result;
    }

    public Group getGroup(Integer grId){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Group> result = jdbcTemplate.query(GET_GROUP_BY_ID,new Object[]{grId},Group.groupInfoRm);

        if (result.isEmpty())
            return null;
        result.get(0).setSpec(specDao.getSpetiality(result.get(0).getSpec().getId()));
        return result.get(0);
    }

    public Group getGroupByCurator(int curId){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Group> result = jdbcTemplate.query(GET_GROUP_BY_CURATOR,new Object[]{curId},Group.groupRm);

        if (result.isEmpty())
            return null;
        result.get(0).setSpec(specDao.getSpetiality(result.get(0).getSpec().getId()));
        return result.get(0);

    }

    public List<Group> getTeacherGroups(int teacId){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Group> result = jdbcTemplate.query(GET_TEACHER_GROUPS,new Object[]{teacId},Group.groupRm);
        if(result.isEmpty())
            return null;

        for(Group gr: result)
            gr.setSpec(specDao.getSpetiality(gr.getSpec().getId()));
        return result;

    }



    public List<Group> getAllGroups(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Group> result = jdbcTemplate.query(GET_ALL_GROUPS,Group.groupInfoRm);
        if(result.isEmpty())
            return null;
        for(Group gr: result)
            gr.setSpec(specDao.getSpetiality(gr.getSpec().getId()));
        return result;
    }

    public List<Group> getSpecGrops(int vid){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Group> result = jdbcTemplate.query(GET_SPEC_GROUPS,new Object[]{vid},Group.groupInfoRm);
        if(result.isEmpty())
            return null;
        for(Group gr: result)
            gr.setSpec(specDao.getSpetiality(gr.getSpec().getId()));
        return result;
    }

    public boolean updGroup(Integer id, String name, Integer degId, String stDate, String finDate, Integer curId, Integer edYear) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        int result = jdbcTemplate.update(UPDATE_GROUP,name,degId,stDate,finDate,curId,edYear,id);

        if (result>0)
            return true;
        else return false;
    }

    public boolean addGroup(String name, Integer degId, String stDate, String finDate, Integer curId, Integer edYear) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        int result = jdbcTemplate.update(INSERT_GROUP,name,degId,stDate,finDate,curId,edYear);

        if (result>0)
            return true;
        else return false;
    }


    public boolean updGroup(Group group) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        int result = jdbcTemplate.update(UPDATE_GROUP,group.getName(),group.getSpec().getId(),group.getStartDate(),group.getFinishDate(),group.getCurator().getId(),group.getEducYear(),group.getId());

        if (result>0)
            return true;
        else return false;

    }

    public boolean addGroup(Group group) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        int result = jdbcTemplate.update(INSERT_GROUP,group.getName(),group.getSpec().getId(),group.getStartDate(),group.getFinishDate(),group.getCurator().getId(),group.getEducYear());

        if (result>0)
            return true;
        else return false;
    }

    public List<Group> getTeacherSubjectGroups(Integer teacId, Integer subjId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Group> result = jdbcTemplate.query(GET_TEACHER_SUBJECT_GROUPS,new Object[]{teacId, subjId},Group.groupRm);
        if(result.isEmpty())
            return null;
        for(Group gr: result)
            gr.setSpec(specDao.getSpetiality(gr.getSpec().getId()));
        return result;
    }
}
