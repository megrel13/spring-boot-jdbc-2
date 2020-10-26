package ru.itsjava;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.itsjava.services.ConsoleService;

import java.sql.SQLException;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws SQLException {
        var context = new SpringApplication(Application.class).run();
        context.getBean(ConsoleService.class).start();


// Console.main(args);
    }
}
