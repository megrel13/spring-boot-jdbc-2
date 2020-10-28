package ru.itsjava.dao;


import ru.itsjava.domain.Pet;

import java.util.Optional;

public interface PetDaoJdbc {
    int count();

    void insert(Pet pet);

    Optional<Pet> findById(long id);
}
