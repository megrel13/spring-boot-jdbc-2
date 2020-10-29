package ru.itsjava.dao;

import ru.itsjava.domain.Email;

import java.util.Optional;

public interface EmailDaoJdbc {
    int count();
    void insert(Email email);
    Optional<Email> findById(long id);
    void changeMail(Email email, String newMail);
    void deleteMail(Email email);
}
