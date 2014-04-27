package ua.softserve.entities;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by troll on 27.12.13.
 */
public class Mark {

    public static ParameterizedRowMapper<Mark> markRm = new ParameterizedRowMapper() {
        @Override
        public Mark mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Mark(resultSet);
        }
    };

    private int id;
    private int mark;
    private int retake;
    private Date date;

    public Mark() {
    }

    public Mark(ResultSet rs) throws SQLException {
        this.id = rs.getInt("Id");
        this.mark = rs.getInt("Mark");
        this.date = rs.getDate("Date");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public int getRetake() {
        return retake;
    }

    public void setRetake(int retake) {
        this.retake = retake;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public  String toString(){
        return Integer.toString(getMark());
    }
}