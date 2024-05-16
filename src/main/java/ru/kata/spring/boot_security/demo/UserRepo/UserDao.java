package ru.kata.spring.boot_security.demo.UserRepo;

import ru.kata.spring.boot_security.demo.model.User;
import java.util.List;
import java.util.Optional;

public interface UserDao {
    List<User> getAllUsers();

    void createUser(User user);

    void updateUser(User user);

    void deleteUser(long id);

    User getUser(long id);

    User getMail(String mail);

    String getEmailByUsername(String username);

    public Optional<User> findUserByUsername(String username);

}

