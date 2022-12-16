package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private final EntityManager entityManager;

    public UserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void create(User user) {
        entityManager.joinTransaction();
        entityManager.persist(user);
    }

    @Override
    public User show(long id) {
        TypedQuery<User> query = entityManager.createQuery("from User where id=:i", User.class);
        query.setParameter("i", id);
        return query.getSingleResult();
    }

    @Override
    public List<User> getList() {
        TypedQuery<User> query = entityManager.createQuery("from User", User.class);
        return query.getResultList();
    }

    @Override
    public void update(long id, User user) {
        entityManager.joinTransaction();
        User u = show(id);
        u.setName(user.getName());
        u.setLastName(user.getLastName());
        u.setEmail(user.getEmail());
        entityManager.persist(u);
    }

    @Override
    public void delete(long id) {
        entityManager.joinTransaction();
        try {
            entityManager.remove(show(id));
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    @Override
    public void delete(User user) {
        entityManager.joinTransaction();
        find(user).forEach(u -> entityManager.remove(u.getId()));
    }

    @Override
    public List<User> find(User user) {
        TypedQuery<User> query = entityManager.createQuery("from User where name=:n and last_name=:f and email=:e", User.class);
        query.setParameter("n", user.getName());
        query.setParameter("f", user.getLastName());
        query.setParameter("e", user.getEmail());
        return query.getResultList();
    }
}