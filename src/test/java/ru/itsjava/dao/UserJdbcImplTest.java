package ru.itsjava.dao;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.itsjava.domain.Email;
import ru.itsjava.domain.Pet;
import ru.itsjava.domain.User;


import java.util.Collections;
import java.util.List;

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
        assertEquals(userDaoJdbc.count(), 1);
    }

    @Test
    @DisplayName("Находить по ID")
    public void shouldCorrectFindById() {
        Email email = new Email(3, "Aliedora@mail.ru");
        Pet pet = new Pet(3, "bobik");
        //      List<> newEmail = Collections.singletonList();
        User user = new User(3, "ALIEDORA", 33, email, pet);
        userDaoJdbc.insert(user);
        Assertions.assertEquals(userDaoJdbc.findById(3), user);
    }

    @Test
    @DisplayName("Корректная работа метода insert")
    public void shouldCorrectInsert() {
        Email email = new Email(5, "MomoMegaOtsutsuki@mail.ru");
        Pet pet = new Pet(5, "Kinshiki");
        User user = new User(5, "Momoshiki", 33, email, pet);
        userDaoJdbc.insert(user);
        Assertions.assertEquals(userDaoJdbc.findById(5), user);

    }

    @Test
    @DisplayName("Корректная работа метода delete")
    public void shouldCorrectDelete() {
        Email email = new Email(2, "Alita@mail.ru");
        Pet pet = new Pet(2, "shaniya");
        User user = new User(2, "Alita", 23, email, pet);
        userDaoJdbc.deleteUser(user);
        Assertions.assertEquals(userDaoJdbc.count(), 1);
    }


}