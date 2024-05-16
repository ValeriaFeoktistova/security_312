package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

public interface MyUserDetService extends UserDetailsService {

    public UserDetails loadUserByUsername(String username);

    List<Role> findAllRoles();

}

