package com.example.emailer.db.entities.id;

import org.neo4j.ogm.id.IdStrategy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class AccountIdStrategy implements IdStrategy {
    private final RowMapper<String> mapper = (rs, i) -> rs.getString("nextval");
    private JdbcTemplate jdbcTemplate;

    public AccountIdStrategy() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource(
                "jdbc:postgresql://localhost:5432/emailer",
                "postgres", "postgres"
        );
        dataSource.setDriverClassName("org.postgresql.Driver");
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public final String generateId(Object o) {
        return jdbcTemplate.queryForObject("SELECT nextval('" + sequenceName() + "')", mapper);
    }

    private String sequenceName() {
        return "account_id_sequence";
    }
}
