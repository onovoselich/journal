package ua.softserve.entities;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by troll on 19.12.13.
 */
public class Subject implements Comparable {
    public static final String EXAM = "Іспит";
    public static final String ZALIK = "Залік";
    public static final String DUF_ZALIK = "Д/З";
    public static final String OTHER = "practcursdupl";


    public static ParameterizedRowMapper<Subject> subjectRm = new ParameterizedRowMapper() {
        @Override
        public Subject mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Subject(resultSet);
        }
    };

    public static ParameterizedRowMapper<Subject> subjectInfoRm = new ParameterizedRowMapper<Subject>() {
        @Override
        public Subject mapRow(ResultSet resultSet, int i) throws SQLException {
            Subject subject = new Subject(resultSet);
            subject.setEcts(resultSet.getDouble("ECTSCredits"));
            subject.setHours(resultSet.getInt("Hours"));

            /*
            subject.setSumestr(resultSet.getInt("sumestr"));
            subject.setYears(resultSet.getString("Years"));*/

            return subject;
        }
    };

    private Integer id;
    private String name;
    private Double ects;
    private Integer hours;
    private String controlForm;
    private Teacher teacher;
    private String shortcut;
    private Spetiality spetiality;
    private Map<Integer, Integer> sums;

    public Map<Integer, Integer> getSums() {
        return sums;
    }


    public String getShortcut() {
        return shortcut;
    }

    public void setShortcut(String shortcut) {
        this.shortcut = shortcut;
    }

    public void setSums(Map<Integer, Integer> sums) {
        this.sums = sums;
    }

    public void setSums(String sumsStr) {
        if (!"".equals(sumsStr) && sumsStr != null) {
            Map<Integer, Integer> sums = new HashMap<Integer, Integer>();
            String[] pairs = sumsStr.split("[\\{\\} ,]+");
            for (String s : pairs) {
                if (s.indexOf("=") > 0) {
                    sums.put(Integer.parseInt(s.substring(0, s.indexOf("="))), Integer.parseInt(s.substring(s.indexOf("=") + 1)));
                }
            }
            this.sums = sums;
        }
    }

    public Subject() {
    }

    public Subject(ResultSet rs) throws SQLException {
        this.id = rs.getInt("Id");
        this.name = rs.getString("Name");
        this.setSpetiality(new Spetiality(rs.getInt("specId")));
        this.controlForm = rs.getString("ControlForm");
        this.setSums(rs.getString("sums"));
        this.shortcut = (rs.getString("shortcut"));
    }

    public Spetiality getSpetiality() {
        return spetiality;
    }

    public void setSpetiality(Spetiality spetiality) {
        this.spetiality = spetiality;
    }


    public void setEcts(Double ects) {
        this.ects = ects;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }


    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Double getEcts() {
        return ects;
    }

    public void setEcts(double ects) {
        this.ects = ects;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }


    public String getControlForm() {
        return controlForm;
    }

    public void setControlForm(String controlForm) {
        this.controlForm = controlForm;
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


    @Override
    public int compareTo(Object o) {
        return name.compareTo(((Subject) o).getName());
    }

    @Override
    public String toString() {
        if (this.name.length() > 12 && this.shortcut != null)
            return this.shortcut;
        return this.name;
    }

    public String getsSums() {
        if (this.sums == null || this.sums.isEmpty())
            return null;
        else
            return sums.toString();
    }
}
