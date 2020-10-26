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

@SuppressWarnings("ALL")
@Repository
@AllArgsConstructor
public class UserJdbcImpl implements UserDaoJdbc {
    //    private final JdbcOperations jdbcOperations;
    private final NamedParameterJdbcOperations jdbcOperations;

    @Override
    public int count() {
        return jdbcOperations.getJdbcOperations().queryForObject("select count(*) from USERS",
                Integer.class);
    }

    @Override
    public void insert(User user) {
//        jdbcOperations.update("INSERT INTO USERS(ID, NAME, AGE) VALUES (?, ?, ?)",
//                user.getId(), user.getName(), user.getAge());

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("ID", user.getId());
        mapSqlParameterSource.addValue("NAME", user.getName());
        mapSqlParameterSource.addValue("AGE", user.getAge());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update("INSERT INTO USERS(NAME, AGE) VALUES (:NAME, :AGE)",
                mapSqlParameterSource, keyHolder);
        user.setId(keyHolder.getKey().longValue());
    }

    @Override
    public Optional<User> findById(long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("ID", id);
        return Optional.of(jdbcOperations.queryForObject("select u.ID, u.NAME, u.AGE, e.ID, e.EMAIL, p.ID, p.NICKNAME " +
                        " from USERS as u, EMAILS as e, PETS as p where u.ID = :ID and u.EMAIL_ID = e.id and p.USER_ID = u.ID",
                params, new UserMapper()));
    }


    private static class UserMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("users.ID");
            String name = resultSet.getString("users.NAME");
            int age = resultSet.getInt("users.AGE");

            Email email = new Email(resultSet.getLong("emails.ID"), resultSet.getString("emails.EMAIL"));
            Pet pet = new Pet(resultSet.getLong("pets.ID"), resultSet.getString("pets.NICKNAME"));
            return new User(id, name, age, email, pet);
        }
    }
}
