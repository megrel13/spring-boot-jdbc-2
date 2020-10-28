package ru.itsjava.dao;

import ru.itsjava.domain.User;

import java.util.Optional;

public interface UserDaoJdbc {
    int count();
    void insert(User user);
    Optional<User> findById(long id);
    void changeAge(User user, int newAge);
    void deleteUser(User user);
}
