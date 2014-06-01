package ua.softserve.entities;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by troll on 04.03.14.
 */
public class Spetiality implements Comparable<Spetiality> {

    private int id;
    private Float degree;
    private String specName;
    private String viddil;
    private Teacher zavViddil;

    public static ParameterizedRowMapper<Spetiality> specRm = new ParameterizedRowMapper<Spetiality>() {
        @Override
        public Spetiality mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Spetiality(resultSet);
        }
    };

    public Spetiality() {
    }

    public Spetiality(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getInt("id");
        this.degree = resultSet.getFloat("degree");
        this.specName = resultSet.getString("Name");
        this.viddil = resultSet.getString("viddil");
        this.zavViddil = new Teacher(resultSet.getInt("ZavViddil"));
    }

    public Spetiality(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Float getDegree() {
        return degree;
    }

    public void setDegree(Float degree) {
        this.degree = degree;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public String getViddil() {
        return viddil;
    }

    public void setViddil(String viddil) {
        this.viddil = viddil;
    }

    public Teacher getZavViddil() {
        return zavViddil;
    }


    public void setZavViddil(Teacher zavViddil) {
        this.zavViddil = zavViddil;
    }


    @Override
    public int compareTo(Spetiality o) {
        return this.getSpecName().compareTo(o.getSpecName());
    }

    @Override
    public String toString() {
        return specName;
    }
}
