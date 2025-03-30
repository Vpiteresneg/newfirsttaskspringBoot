package ru.vpiteresneg.kata.newfirsttaskspringBoot.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import ru.vpiteresneg.kata.newfirsttaskspringBoot.model.User;


import java.util.List;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public User findById(Long id) {
        return em.find(User.class, id);
    }

    @Override
    public List<User> getAll() {
        return em.createQuery("from User", User.class).getResultList();
    }

    @Override
    public void saveUser(User user) {
        if (user.getId() == null) {
            em.persist(user);
        } else {
            em.merge(user);
        }
    }

    @Override
    public void deleteUser(Long id) {
        User user = em.find(User.class, id);
        if (user != null) {
            em.remove(user);
        }
    }

    @Override
    public User findByName(String name) {
        return em.createQuery("from User where name = :name", User.class)
                .setParameter("name", name)
                .getSingleResult();
    }
}