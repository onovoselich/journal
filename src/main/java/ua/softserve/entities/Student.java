package ua.softserve.entities;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by troll on 19.12.13.
 */
public class Student implements Comparable {


    public static ParameterizedRowMapper<Student> studentRm = new ParameterizedRowMapper() {
        @Override
        public Student mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Student(resultSet);
        }
    };

    public static ParameterizedRowMapper<Student> studentInfoRm = new ParameterizedRowMapper() {
        @Override
        public Student mapRow(ResultSet resultSet, int i) throws SQLException {
            Student student = new Student(resultSet);
            student.setGroupId(resultSet.getInt("GroupId"));
            student.setbDate(resultSet.getString("BDate"));
            student.setEducForm(resultSet.getString("EducForm"));
            student.setEdukType(resultSet.getString("EducType"));
            student.setLogin(resultSet.getString("login"));
            student.setGender(resultSet.getString("Gender"));
            student.setAddress(resultSet.getString("Address"));
            student.setPhone(resultSet.getString("Phone"));
            return student;
        }
    };

    private Integer id;
    private int groupId;
    private String name;
    private String surname;
    private String login;
    private String patronimic;
    private String bDate;
    private String gradebook;
    private String educForm;    //денна/заочна
    private String edukType;    //платне/державне
    private String gender;
    private String address;
    private String phone;
    private boolean alarm;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isAlarm() {
        return alarm;
    }

    public void setAlarm(boolean alarm) {
        this.alarm = alarm;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Student(ResultSet rs) throws SQLException {
        this.id = rs.getInt("Id");
        this.name = rs.getString("Name");
        this.surname = rs.getString("Surname");
        this.patronimic = rs.getString("Patronimic");
        this.gradebook = rs.getString("Gradebook");
        this.status = rs.getString("Status");
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
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

    public String getbDate() {
        return bDate;
    }

    public void setbDate(String bDate) {
        this.bDate = bDate;
    }

    public String getGradebook() {
        return gradebook;
    }

    public void setGradebook(String gradebook) {
        this.gradebook = gradebook;
    }

    public String getEducForm() {
        return educForm;
    }

    public void setEducForm(String educForm) {
        this.educForm = educForm;
    }

    public String getEdukType() {
        return edukType;
    }

    public void setEdukType(String edukType) {
        this.edukType = edukType;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public int compareTo(Object o) {
        Student st = (Student) o;
        if (this.getSurname().compareTo(st.getSurname()) != 0)
            return (this.getSurname().compareTo(st.getSurname()));
        else if (this.getName().compareTo(st.getName()) != 0)
            return (this.getName().compareTo(st.getName()));
        else if (this.getPatronimic().compareTo(st.getPatronimic()) != 0)
            return (this.getPatronimic().compareTo(st.getPatronimic()));
        else return (this.getId().compareTo(st.getId()));

    }

    @Override
    public String toString() {
        String res = getSurname() + " " + getName().charAt(0) + ". " + getPatronimic().charAt(0) + ". ";
        if ("vidrah".equals(this.status))
            res += "(відрах.) ";
        return res;
    }

    public String getFullName() {
        return getSurname() + " " + getName() + " " + getPatronimic();
    }

    public Student() {
    }
}
