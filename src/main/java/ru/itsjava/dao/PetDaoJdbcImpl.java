package ru.itsjava.dao;

import ru.itsjava.domain.Pet;

import java.util.Optional;

public class PetDaoJdbcImpl implements PetDaoJdbc {


    @Override
    public int count() {
        return 0;
    }

    @Override
    public void insert(Pet pet) {

    }

    @Override
    public Optional<Pet> findById(long id) {
        return Optional.empty();
    }
}
