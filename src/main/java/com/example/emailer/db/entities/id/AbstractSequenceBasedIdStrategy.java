package com.example.emailer.db.entities.id;

import org.neo4j.ogm.id.IdStrategy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public abstract class AbstractSequenceBasedIdStrategy implements IdStrategy {
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Long> longMapper = (rs, i) -> rs.getLong("nextval");

    public AbstractSequenceBasedIdStrategy() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource(
                "jdbc:postgresql://localhost:5432/emailer",
                "postgres", "postgres"
        );
        dataSource.setDriverClassName("org.postgresql.Driver");
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    final
    public Long generateId(Object o) {
        return jdbcTemplate.queryForObject("SELECT nextval('" + sequenceName() + "')", longMapper);
    }

    abstract String sequenceName();
}
