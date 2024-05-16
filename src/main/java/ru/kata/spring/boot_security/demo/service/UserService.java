package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    void createOreUpdateUser(User user);

    User getUser(long id);

    User deleteUser(long id);

    public void createUser(User user);

    public List<Role> getRolesById(List<Long> roleIds);

    public void updateUser(User user);

}
