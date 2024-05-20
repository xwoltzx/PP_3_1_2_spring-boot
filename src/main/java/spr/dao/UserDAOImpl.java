package spr.dao;

import jakarta.persistence.PersistenceContextType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import spr.entity.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Component
public class UserDAOImpl implements UserDAO {
    @PersistenceContext
    private EntityManager entityManager;



    public List<User> index() {
        return entityManager.createQuery("select u from User u").getResultList();
    }

    public User show(long id) {
        return entityManager.find(User.class, id);
    }

    public void save(User user) {
        entityManager.persist(user);
    }

    public void update(long id, User updateUser) {
        entityManager.find(User.class, id);
        entityManager.merge(updateUser);
    }

    public void delete(long id) {
        entityManager.remove(entityManager.find(User.class, id));
    }
}
