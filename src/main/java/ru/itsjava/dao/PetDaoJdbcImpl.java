package ru.itsjava.dao;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.itsjava.domain.Pet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class PetDaoJdbcImpl implements PetDaoJdbc {
    private final NamedParameterJdbcOperations jdbcOperations;

    @Override
    public int count() {
        return jdbcOperations.getJdbcOperations().queryForObject("select count(*) from Pet", Integer.class);
    }

    @Override
    public void insert(Pet pet) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("ID", pet.getId());
        mapSqlParameterSource.addValue("NICKNAME", pet.getNickname());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update("INSERT INTO PETS(NICKNAME) VALUES (:NICKNAME)", mapSqlParameterSource, keyHolder);
        pet.setId(keyHolder.getKey().longValue());

    }

    @Override
    public Optional<Pet> findById(long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("ID", id);
        return Optional.of(jdbcOperations.queryForObject("SELECT ID,NICKNAME FROM PETS WHERE (ID = :ID)", params, new PetMapper()));
    }

    @Override
    public void changeNickname(Pet pet, String newNickname) {
        final Map<String, Object> params = new HashMap<>();
        params.put("ID", pet.getId());
        params.put("NICKNAME", newNickname);
        jdbcOperations.update("UPDATE PETS SET NICKNAME WHERE ID=:ID", params);
    }

    @Override
    public void deletePet(Pet pet) {
        final Map<String, Object> params = new HashMap<>();
        params.put("ID", pet.getId());
        jdbcOperations.update("DELETE FROM PETS WHERE ID=:ID", params);
    }

    private static class PetMapper implements RowMapper<Pet> {

        @Override
        public Pet mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("pet.ID");
            String nickname = resultSet.getString("pet.NICKNAME");
            return new Pet(id, nickname);
        }
    }

}
