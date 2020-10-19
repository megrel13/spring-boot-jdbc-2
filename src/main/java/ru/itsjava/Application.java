package ru.itsjava;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.itsjava.dao.UserDaoJdbc;
import ru.itsjava.domain.User;

import java.sql.SQLException;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws SQLException {
        var context = new SpringApplication(Application.class).run();

        UserDaoJdbc userDaoJdbc = context.getBean(UserDaoJdbc.class);
        System.out.println("Before insert: " + userDaoJdbc.count());
        User dmitriy = new User(3L, "Dmitriy", 28);
        userDaoJdbc.insert(dmitriy);
        System.out.println("After insert: " + userDaoJdbc.count());

        System.out.println(userDaoJdbc.findById(1L));
//        Console.main(args);
    }
}
