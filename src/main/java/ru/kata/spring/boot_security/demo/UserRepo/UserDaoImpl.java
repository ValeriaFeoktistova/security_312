package ru.kata.spring.boot_security.demo.UserRepo;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<User> getAllUsers() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public User getUser(long id) {
        return entityManager.find(User.class, id);
    }


    @Override
    public User getMail(String mail) {
        String jpql = "SELECT u FROM User u WHERE u.mail = :mail";// u - возвращаем Object
        TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
        query.setParameter("mail", mail);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public String getEmailByUsername(String username) {
        String jpql = "SELECT u.mail FROM User u WHERE u.name = :name";
        Query query = entityManager.createQuery(jpql, String.class);
        query.setParameter("name", username);
        return (String) query.getSingleResult();
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        TypedQuery<User> query = entityManager.createQuery("select u from User u where u.name = :username", User.class);
        query.setParameter("username", username);
        List<User> userList = query.getResultList();
        return userList.isEmpty() ? Optional.empty() : Optional.of(userList.get(0));
    }

    @Override
    public void createUser(User user) {
        entityManager.persist(user);
        entityManager.flush();
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
        entityManager.flush();
    }

    @Override
    public void deleteUser(long id) {
        if (entityManager.find(User.class, id) == null) {
            throw new NullPointerException("User not found");
        }
        entityManager.createQuery("DELETE FROM User u WHERE u.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
