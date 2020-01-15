package io.pivotal.pal.tracker;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class JdbcTimeEntryRepository implements TimeEntryRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public JdbcTimeEntryRepository(MysqlDataSource dataSource) {

        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement statement = con.prepareStatement("INSERT INTO time_entries (project_id, user_id, date, hours) values (?,?,?,?)", RETURN_GENERATED_KEYS);
            statement.setLong(1, timeEntry.getProjectId());
            statement.setLong(2, timeEntry.getUserId());
            statement.setDate(3, Date.valueOf(timeEntry.getDate()));
            statement.setLong(4, timeEntry.getHours());
            return statement;
        }, generatedKeyHolder);

       return find(generatedKeyHolder.getKey().longValue());
    }

    @Override
    public TimeEntry find(long timeEntryId) {

        try {

            return (TimeEntry) jdbcTemplate.queryForObject("Select id , project_id, user_id, date,hours from time_entries where id = ?", new Object[]{timeEntryId}, new BeanPropertyRowMapper(TimeEntry.class));
        }
        catch(IncorrectResultSizeDataAccessException ex)
        {
            return null;
        }

    }

    @Override
    public TimeEntry update(long timeEntryId, TimeEntry timeEntry) {


        jdbcTemplate.update(con -> {
            PreparedStatement statement = con.prepareStatement("Update time_entries set project_id = ? , user_id =? , date = ?, hours = ?  where id = ?");
            statement.setLong(1, timeEntry.getProjectId());
            statement.setLong(2, timeEntry.getUserId());
            statement.setDate(3, Date.valueOf(timeEntry.getDate()));
            statement.setLong(4, timeEntry.getHours());
            statement.setLong(5,timeEntryId);
            return statement;
        });

        return find(timeEntryId);

    }

    @Override
    public void delete(long timeEntryId) {

        jdbcTemplate.update("Delete from time_entries where id = ?",timeEntryId);

    }

    @Override
    public List<TimeEntry> list() {
        return jdbcTemplate.query("Select id , project_id, user_id, date,hours from time_entries", new BeanPropertyRowMapper(TimeEntry.class));
    }

}
