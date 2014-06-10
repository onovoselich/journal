package ua.journal.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import ua.journal.entities.Spetiality;

import javax.sql.DataSource;
import java.util.List;

import static ua.journal.db.SqlQueries.*;

/**
 * Created by troll on 04.01.14.
 */
public class SpecDao {

    @Autowired
    DataSource dataSource;


    public List<Spetiality> getAllSpecs() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Spetiality> result = jdbcTemplate.query(GET_ALL_SPECS, Spetiality.specRm);
        if (result.isEmpty())
            return null;
        else
            return result;
    }


    public Spetiality getSpetiality(int id) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Spetiality> result = jdbcTemplate.query(GET_SPEC, new Object[]{id}, Spetiality.specRm);
        if (result.isEmpty())
            return null;
        else
            return result.get(0);
    }

    public boolean addSpec(Float degree, String name, String viddil, Integer zavId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        int result = jdbcTemplate.update(INSERT_SPEC, degree, name, viddil, zavId);

        if (result > 0)
            return true;
        else return false;
    }

    public boolean alterSpec(Integer id, Float degree, String name, String viddil, Integer zavId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        int result = jdbcTemplate.update(UPDATE_SPEC, degree, name, viddil, zavId, id);

        if (result > 0)
            return true;
        else return false;

    }

    public boolean addSpec(Spetiality spec) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        int result = jdbcTemplate.update(INSERT_SPEC, spec.getDegree(), spec.getSpecName(), spec.getViddil(), spec.getZavViddil().getId());

        if (result > 0)
            return true;
        else return false;
    }

    public boolean alterSpec(Spetiality spec) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        int result = jdbcTemplate.update(UPDATE_SPEC, spec.getDegree(), spec.getSpecName(), spec.getViddil(), spec.getZavViddil().getId(), spec.getId());

        if (result > 0)
            return true;
        else return false;
    }
}
