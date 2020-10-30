package ru.itsjava.dao;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.itsjava.domain.Email;
import ru.itsjava.domain.Pet;
import ru.itsjava.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
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
        jdbcOperations.update("INSERT INTO EMAILS(EMAIL) VALUES (:EMAIL)",
                mapSqlParameterSource, keyHolder);
        email.setId(keyHolder.getKey().longValue());
    }

    @Override
    public Optional<Email> findById(long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("ID", id);
        return Optional.of(jdbcOperations.queryForObject("SELECT ID, EMAIL FROM EMAILS WHERE (ID = :ID)", params, new EmailMapper()));
    }

    @Override
    public void changeMail(Email email, String newEmail) {
        final Map<String, Object> params = new HashMap<>();
        params.put("ID", email.getId());
        params.put("EMAIL", newEmail);
        jdbcOperations.update("UPDATE EMAILS SET EMAIL=:EMAIL where ID=:ID", params);
    }

    @Override
    public void deleteMail(Email email) {
        final Map<String, Object> params = new HashMap<>();
        params.put("ID", email.getId());
        jdbcOperations.update("DELETE FROM EMAILS WHERE ID=:ID", params);
    }

    private static class EmailMapper implements RowMapper<Email> {

        @Override
        public Email mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("email.ID");
            String email = resultSet.getString("email.EMAIL");

            return new Email(id, email);
        }
    }
}
