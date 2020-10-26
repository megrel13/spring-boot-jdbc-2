package ru.itsjava.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itsjava.dao.UserDaoJdbc;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserDaoJdbc userDao;

    @Override
    public void printById(long id) {
         userDao.findById(id)
                .ifPresent(System.out::println);
    }
}
