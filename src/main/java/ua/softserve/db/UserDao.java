package ua.softserve.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

import static ua.softserve.db.SqlQueries.*;

/**
 * Created by troll on 04.01.14.
 */
public class UserDao {
    public static final String ROLE_TEACHER = "ROLE_TEACHER";
    public static final String ROLE_STUDENT = "ROLE_STUDENT";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";

    @Autowired
    DataSource dataSource;


    public static int getId(String log, DataSource
            dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.queryForObject(GET_ID, Integer.class, log);


    }

    public boolean changePass(String login, String password) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        int result = jdbcTemplate.update(UPDATE_USER, password, login);

        if (result > 0)
            return true;
        else return false;
    }

    public List<String> getAllUsers() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<String> result = jdbcTemplate.queryForList(GET_ALL_USERS, String.class);

        if (result.isEmpty())
            return null;
        else return result;
    }


    public boolean addUser(int id, String role, String login, String password) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        if (0 == jdbcTemplate.update(INSERT_USER, id, role, login, password))
            return false;
        else


            return true;
    }

    public boolean delUser(String login) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        if (jdbcTemplate.update(DELETE_USER, login) == 0)
            return false;


        return true;
    }
}
