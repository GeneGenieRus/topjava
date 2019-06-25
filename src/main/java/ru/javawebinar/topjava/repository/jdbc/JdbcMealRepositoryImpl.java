package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository("jdbcMealRepository")
public class JdbcMealRepositoryImpl implements MealRepository {


    private static final BeanPropertyRowMapper<Meal> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Meal.class);

    private final JdbcTemplate jdbcTemplate;


    private final SimpleJdbcInsert simpleJdbcInsert;

    public JdbcMealRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("meals")
                .usingGeneratedKeyColumns("id");
        this.jdbcTemplate = jdbcTemplate;

    }

    @Override
    public Meal save(Meal meal, int userId) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", meal.getId())
                .addValue("description", meal.getDescription())
                .addValue("datetime", meal.getDateTime())
                .addValue("calories", meal.getCalories())
                .addValue("user_id", userId);

        Number key;
        if (meal.isNew()) {
            key = simpleJdbcInsert.executeAndReturnKey(map);
            meal.setId(key.intValue());
        } else if (jdbcTemplate.update("UPDATE meals SET description=?, datetime=?, calories=?, user_id=? WHERE id=?",
                meal.getDescription(), meal.getDateTime(), meal.getCalories(), userId, meal.getId()) == 0)
        return null;

        return meal;
    }

    @Override
    public boolean delete(int id, int userId) {

        if (jdbcTemplate.update("DELETE FROM meals WHERE id=" + id + " AND user_id=" + userId) > 0)
            return true;
        else
            return false;
    }

    @Override
    public Meal get(int id, int userId) {
        return jdbcTemplate.queryForObject("SELECT * FROM meals WHERE user_id=" + userId + " AND id=" + id,
                new RowMapper<Meal>() {
                    @Override
                    public Meal mapRow(ResultSet resultSet, int i) throws SQLException {
                        return new Meal(resultSet.getInt("id"),
                                resultSet.getTimestamp("datetime").toLocalDateTime(),
                                resultSet.getString("description"),
                                resultSet.getInt("calories"));
                    }
                });
    }

    @Override
    public List<Meal> getAll(int userId) {
        return jdbcTemplate.query("SELECT * FROM meals WHERE user_id=" + userId, new RowMapper<Meal>() {
            @Override
            public Meal mapRow(ResultSet resultSet, int i) throws SQLException {
                return new Meal(resultSet.getInt("id"),
                        resultSet.getTimestamp("datetime").toLocalDateTime(),
                        resultSet.getString("description"),
                        resultSet.getInt("calories"));

            }
        });

    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return jdbcTemplate.query("SELECT * FROM meals " +
                "WHERE datetime >= \'" + Timestamp.valueOf(startDate) + "\'::timestamp AND datetime <=\'" + Timestamp.valueOf(endDate) + "\'::timestamp", new RowMapper<Meal>() {
            @Override
            public Meal mapRow(ResultSet resultSet, int i) throws SQLException {
                return new Meal(resultSet.getInt("id"),
                        resultSet.getTimestamp("datetime").toLocalDateTime(),
                        resultSet.getString("description"),
                        resultSet.getInt("calories"));

            }
        });
    }
}
