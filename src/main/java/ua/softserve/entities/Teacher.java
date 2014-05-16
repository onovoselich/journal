package ua.softserve.entities;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import ua.softserve.logic.Triple;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by troll on 02.01.14.
 */
public class Teacher {
    public static ParameterizedRowMapper<Teacher> teacherRm = new ParameterizedRowMapper() {
        @Override
        public Teacher mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Teacher(resultSet);
        }
    };

    public static ParameterizedRowMapper<Teacher> teacherInfoRowMapper = new ParameterizedRowMapper<Teacher>(){
        @Override
        public Teacher mapRow(ResultSet resultSet, int i)throws SQLException{
            Teacher teacher = new Teacher(resultSet);
            teacher.setSpec(resultSet.getString("Spec"));
            teacher.setDyplom(resultSet.getString("Dyplom"));
            teacher.setCategory(resultSet.getString("Category"));
            teacher.setDegree(resultSet.getString("Degree"));
            teacher.setLogin(resultSet.getString("login"));
            return teacher;

        }
    };

    private Integer id;
    private String name;
    private String surname;
    private String patronimic;
    private String spec;
    private String dyplom;
    private String category;
    private String degree;
    private String login;

    public Teacher() {
    }

    public Teacher(ResultSet rs)throws SQLException{
        this.id =rs.getInt("Id");
        this.name = rs.getString("Name");
        this.surname = rs.getString("Surname");
        this.patronimic = rs.getString("Patronimic");

    }

    public Teacher(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getDyplom() {
        return dyplom;
    }

    public void setDyplom(String dyplom) {
        this.dyplom = dyplom;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronimic() {
        return patronimic;
    }

    public void setPatronimic(String patronimic) {
        this.patronimic = patronimic;
    }

    @Override
    public  String toString(){
        if(("").equals(surname) || surname == null)
            return "No name";
        if(("").equals(name )|| ("").equals(surname) || name == null || surname == null)
            return getSurname();
        return getSurname()+" "+getName().charAt(0)+". "+getPatronimic().charAt(0)+".";

    }

    public String getFullName(){
        return getSurname()+" "+getName()+" "+getPatronimic();
    }

    public static class Tgs extends Triple<Group,Subject,List<Integer>> {

        public Tgs(Group group, Subject subject, List<Integer> integers) {
            super(group, subject, integers);
        }
    }

}
