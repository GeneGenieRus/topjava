package ru.javawebinar.topjava.repository.jdbc.postgres;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.repository.jdbc.JdbcMealRepository;

@Repository
public class JdbcMealRepositoryPostgres extends JdbcMealRepository {


    public JdbcMealRepositoryPostgres(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
    }
}
