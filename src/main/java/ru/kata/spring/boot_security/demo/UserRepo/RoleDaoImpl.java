package ru.kata.spring.boot_security.demo.UserRepo;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    public RoleDaoImpl() {
    }

    @Override
    public List<Role> findAll() {
        return entityManager.createQuery("from Role", Role.class).getResultList();

    }

    @Override
    public Role findRoleByAuthority(String authority) throws NoSuchElementException {
        List<Role> roles = findAll();
        for (Role role : roles) {
            if (authority.equals(role.getAuthority())) {
                return role;
            }
        }
        throw new NoSuchElementException(String.format("Role %s not found", authority));
    }

    @Override
    public Role getRoleById(Long id) {

        TypedQuery<Role> query = entityManager.createQuery("SELECT r FROM Role r WHERE r.id = :id", Role.class);
        query.setParameter("id", id);
        Role role = query.getSingleResult();
        return role;
    }

    @Override
    public List<Role> findByNameList(String name) {
        TypedQuery<Role> query = entityManager.createQuery("SELECT r FROM Role r WHERE r.name = :name", Role.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    public List<Role> findAllById(List<Long> roleIds) {
        return entityManager.createQuery("SELECT r FROM Role r WHERE r.id IN :roleIds", Role.class)
                .setParameter("roleIds", roleIds)
                .getResultList();
    }

    @Override
    public Role findByName(String name) {
        TypedQuery<Role> query = entityManager.createQuery("SELECT r FROM Role r WHERE r.name = :name", Role.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }
}

