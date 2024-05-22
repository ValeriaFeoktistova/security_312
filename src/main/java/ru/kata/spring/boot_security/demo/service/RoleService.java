package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

public interface RoleService {
    public List<Role> findAll();

    public Role findRoleByAuthority(String authority);

    public Role getRoleById(Long id);

    public List<Role> findByNameList(String name);

    public List<Role> findAllById(List<Long> roleIds);

    public Role findByName(String name);

    public List<Role> getRolesById(List<Long> roleIds);

}
