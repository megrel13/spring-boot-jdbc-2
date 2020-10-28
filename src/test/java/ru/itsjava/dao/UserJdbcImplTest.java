package ru.itsjava.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertEquals;

@JdbcTest
@DisplayName("UserJdbc должен корректно: ")
@Import(UserDaoJdbcImpl.class)
public class UserJdbcImplTest {
    @Autowired
    UserDaoJdbc userDaoJdbc;

    @Test
    @DisplayName(" находить количетсво пользователей")
    public void shouldCorrectCount(){
        assertEquals(userDaoJdbc.count(), 1);
    }
}