package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;
import java.util.NoSuchElementException;

public interface RoleDao {

    List<Role> findAll();

    Role findRoleByAuthority(String authority) throws NoSuchElementException;

    public Role getRoleById(Long id);

    List<Role> findByNameList(String name);

    public List<Role> findAllById(List<Long> roleIds);

    public Role findByName(String name);
}
