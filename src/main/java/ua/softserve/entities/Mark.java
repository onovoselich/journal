package ua.softserve.entities;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

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

    private Integer id;
    private Integer mark;
    private Integer retake;
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

    public Integer getMark() {
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
    public String toString() {
        return Integer.toString(getMark());
    }

    public String getFormatDate() {
        if (date != null)
            return new SimpleDateFormat("yyyy-MM-dd").format(date);
        else
            return "";
    }

    public String get100BaseMark() {
        return null;
    }

    public Integer getNationalScaleMark() {
        switch (this.mark) {
            case 1:
                return 1;
            case 2:
            case 3:
                return 2;
            case 4:
            case 5:
            case 6:
                return 3;
            case 7:
            case 8:
            case 9:
                return 4;
            case 10:
            case 11:
            case 12:
                return 5;
        }
        return null;
    }

    public String getZalMark() {
        if (this.mark <= 3)
            return "не зарах.";
        else
            return "зарах";
    }


    public String getNationalScaleMark(String controlForm) {
        if (controlForm == Subject.ZALIK)
            return getZalMark();
        else
            return getNationalScaleMark().toString();
    }

    public String getFMark(String controlForm) {
        if (controlForm == Subject.ZALIK)
            return getZalMark();
        else
            return getMark().toString();
    }

    public static Map<Integer, Integer> getStatistics(Collection<Mark> values) {
        Map<Integer, Integer> res = new HashMap<Integer, Integer>();
        for (int i = 0; i < 7; i++)
            res.put(i, 0);
        for (Mark m : values) {
            try {
                switch (m.getMark()) {
                    case 12:
                    case 11:
                    case 10:
                        res.put(0, res.get(0) + 1);
                        break;
                    case 9:
                    case 8:
                        res.put(1, res.get(1) + 1);
                        break;
                    case 7:
                        res.put(2, res.get(2) + 1);
                        break;
                    case 6:
                    case 5:
                        res.put(3, res.get(3) + 1);
                        break;
                    case 4:
                        res.put(4, res.get(4) + 1);
                        break;
                    case 3:
                    case 2:
                        res.put(5, res.get(5) + 1);
                        break;
                    case 1:
                        res.put(6, res.get(6) + 1);
                        break;
                }
            } catch (NullPointerException e) {
            }
        }
        return res;
    }


    public static String quality(List<Integer> integers) {
        return "100%";
    }

    public static String success(List<Integer> integers) {

        return "100%";
    }
}
