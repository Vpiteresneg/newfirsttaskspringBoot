package ru.vpiteresneg.kata.newfirsttaskspringBoot.dao;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import ru.vpiteresneg.kata.newfirsttaskspringBoot.model.Role;


@Repository
@Transactional
public class RoleDaoImpl implements RoleDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Role findByName(String name) {
        return em.createQuery("from Role where name = :name", Role.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public void saveRole(Role role) {
        if (role.getId() == null) {
            em.persist(role);
        } else {
            em.merge(role);
        }
    }
}