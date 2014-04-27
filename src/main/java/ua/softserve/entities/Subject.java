package ua.softserve.entities;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by troll on 19.12.13.
 */
public class Subject implements Comparable {
    public static final String EXAM = "Іспит";
    public static final String ZALIK = "Залік";
    public static final String DUF_ZALIK = "Д/З";

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
           /* subject.setLections(resultSet.getInt("Lections"));
            subject.setPract(resultSet.getInt("Pract"));
            subject.setLabor(resultSet.getInt("Labor"));
            subject.setConsult(resultSet.getInt("Consult"));*/

            return subject;
        }
    };

    private Integer id;
    private String name;
    private Double ects;
    private Integer hours;
    private int lections;
    private int pract;
    private int labor;
    private int consult;
    private String controlForm;
    private Teacher teacher;
    private int sumestr;

    public Subject() {
    }

    public Subject(ResultSet rs)throws SQLException{
        this.id = rs.getInt("Id");
        this.name = rs.getString("Name");
        this.controlForm = rs.getString("ControlForm");
    }


    public int getSumestr() {
        return sumestr;
    }

    public void setSumestr(int sumestr) {
        this.sumestr = sumestr;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public double getEcts() {
        return ects;
    }

    public void setEcts(double ects) {
        this.ects = ects;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getLections() {
        return lections;
    }

    public void setLections(int lections) {
        this.lections = lections;
    }

    public int getPract() {
        return pract;
    }

    public void setPract(int pract) {
        this.pract = pract;
    }

    public int getLabor() {
        return labor;
    }

    public void setLabor(int labor) {
        this.labor = labor;
    }

    public int getConsult() {
        return consult;
    }

    public void setConsult(int consult) {
        this.consult = consult;
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
    public  String toString(){
        return getName();
    }
}
