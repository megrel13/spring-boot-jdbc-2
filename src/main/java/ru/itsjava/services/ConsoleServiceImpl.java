package ru.itsjava.services;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@AllArgsConstructor
@Service
public class ConsoleServiceImpl implements ConsoleService {
    private final UserService userService;

    @SneakyThrows
    @Override
    public void start() {
        System.out.println("Привет дорогой! Введи id пользователя:");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        long id = Long.parseLong(reader.readLine());
        userService.printById(id);
    }
}
