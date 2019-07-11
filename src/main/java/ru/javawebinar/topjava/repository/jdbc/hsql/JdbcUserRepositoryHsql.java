package ru.javawebinar.topjava.repository.jdbc.hsql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.jdbc.JdbcUserRepository;

@Repository
public class JdbcUserRepositoryHsql extends JdbcUserRepository {
    @Override
    public User getWithMeals(int id) {
        return null;
    }

    public JdbcUserRepositoryHsql(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
    }
}
