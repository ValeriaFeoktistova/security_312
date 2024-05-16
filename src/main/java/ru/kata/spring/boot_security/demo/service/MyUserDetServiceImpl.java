package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.UserRepo.UserDao;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.securitu.MyUserDetails;

import java.util.List;

@Service
@Transactional

public class MyUserDetServiceImpl implements MyUserDetService {
    private final UserDao userDao;

    @Autowired
    public MyUserDetServiceImpl(UserDao userDao) {
        this.userDao = userDao;

    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findUserByUsername(username).get();
        return new MyUserDetails(user);
    }


    @Override
    public List<Role> findAllRoles() {
        return null;
    }

}
