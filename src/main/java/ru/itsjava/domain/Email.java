package ru.itsjava.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Email {
    private long id;
    private final String email;
}
