package ua.journal.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import ua.journal.entities.Mark;

import javax.sql.DataSource;
import java.util.List;

import static ua.journal.db.SqlQueries.*;

/**
 * Created by troll on 04.01.14.
 */
public class MarkDao {
    @Autowired
    DataSource dataSource;


    public Mark getMark(int studId, int subjId, int sum) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Mark> result = jdbcTemplate.query(GET_MARK, new Object[]{studId, subjId, sum}, Mark.markRm);
        if (result.isEmpty()) {
            return null;
        }
        return (result.get(0));
    }

    public boolean insert(int mark, String date, int studId, int tsgId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        int row = jdbcTemplate.update(MARK_INSERT, mark, date, studId, tsgId);
        if (row == 1)
            return true;
        else
            return false;


    }

    public Integer getNegMarksCount(Integer studId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Integer res = jdbcTemplate.queryForInt(NEG_MARKS_COUNT, studId);

        return res;
    }
}
