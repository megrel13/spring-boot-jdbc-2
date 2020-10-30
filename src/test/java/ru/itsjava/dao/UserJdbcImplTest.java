package ru.itsjava.dao;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.itsjava.domain.Email;
import ru.itsjava.domain.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

@JdbcTest
@DisplayName("UserJdbc должен корректно: ")
@Import(UserDaoJdbcImpl.class)
public class UserJdbcImplTest {
    @Autowired
    UserDaoJdbc userDaoJdbc;

    @Test
    @DisplayName(" находить количетсво пользователей")
    public void shouldCorrectCount() {
        assertEquals(userDaoJdbc.count(), 4);
    }

    @Test
    @DisplayName("Находить по ID")
    public void shouldCorrectFindById() {
        User user = new User(3, "ALIEDORA", 33, (),3);
        Assertions.assertEquals(userDaoJdbc.findById(3), user);
    }

    @Test
    @DisplayName("Корректная работа метода insert")
    public void shouldCorrectInsert() {
        User user = new User(5, "Kishiki", 33,5,"");
        userDaoJdbc.insert(user);
        Assertions.assertEquals(userDaoJdbc.findById(5),user);

    }

    @Test
    @DisplayName("Корректная работа метода delete")
    public void shouldCorrectDelete() {
        User user = new User(2, "Alita",23, 2, 2);
        userDaoJdbc.deleteUser(user);
        Assertions.assertEquals(userDaoJdbc.count(), 1);
    }



}