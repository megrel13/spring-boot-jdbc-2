package ru.itsjava.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.itsjava.domain.Email;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class EmailDaoJdbcImpl implements EmailDaoJdbc {

    @Override
    public int count() {
        return 0;
    }

    @Override
    public void insert(Email email) {

    }

    @Override
    public Optional<Email> findById(long id) {
        return Optional.empty();
    }
}
