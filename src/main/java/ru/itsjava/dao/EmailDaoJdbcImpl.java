package ru.itsjava.dao;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.itsjava.domain.Email;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class EmailDaoJdbcImpl implements EmailDaoJdbc {
    private final NamedParameterJdbcOperations jdbcOperations;

    @Override
    public int count() {
        return jdbcOperations.getJdbcOperations().queryForObject("select count(*) from EMAIL",
                Integer.class);
    }

    @Override
    public void insert(Email email) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("ID", email.getId());
        mapSqlParameterSource.addValue("EMAIL", email.getEmail());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update("INSERT INTO EMAIL(EMAIL) VALUES (:EMAIL)",
                mapSqlParameterSource, keyHolder);
        email.setId(keyHolder.getKey().longValue());
    }

    @Override
    public Optional<Email> findById(long id) {
        return Optional.empty();
    }
}
