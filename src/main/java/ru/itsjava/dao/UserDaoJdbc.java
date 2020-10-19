package ru.itsjava.dao;

import ru.itsjava.domain.User;

public interface UserDaoJdbc {
    int count();
    void insert(User user);
    User findById(long id);
}
