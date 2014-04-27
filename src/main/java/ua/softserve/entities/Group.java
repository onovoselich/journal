package ua.softserve.entities;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by troll on 19.12.13.
 */
public class Group {
    public static ParameterizedRowMapper<Group> groupRm = new ParameterizedRowMapper() {
        @Override
        public Group mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Group(resultSet);
        }
    };

    public static ParameterizedRowMapper<Group> groupInfoRm = new ParameterizedRowMapper() {
        @Override
        public Group mapRow(ResultSet resultSet, int i) throws SQLException {
            Group group = new Group(resultSet);
            group.setSpec(new Spetiality(resultSet.getInt("degree")));
            group.setCurator(new Teacher(resultSet.getInt("CuratorId")));
            group.setStartDate(resultSet.getString("StartDate"));
            group.setFinishDate(resultSet.getString("FinishDate"));
            return group;
        }
    };



    private Integer id;
    private String name;
    private Spetiality spec;
    private Teacher curator;
    private String startDate;
    private String finishDate;
    private int educYear;

    public Group() {
    }

    public Group(ResultSet rs) throws SQLException {
        this.id = rs.getInt("Id");
        this.name = rs.getString("Name");
        this.educYear = rs.getInt("EducYear");
        this.setSpec(new Spetiality(rs.getInt("Degree")));
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public Teacher getCurator() {
        return curator;
    }

    public void setCurator(Teacher curator) {
        this.curator = curator;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }

    public int getEducYear() {
        return educYear;
    }

    public void setEducYear(int educYear) {
        this.educYear = educYear;
    }

    @Override
    public String toString(){
        return getName();
    }

    public Spetiality getSpec() {
        return spec;
    }

    public void setSpec(Spetiality spec) {
        this.spec = spec;
    }
}
