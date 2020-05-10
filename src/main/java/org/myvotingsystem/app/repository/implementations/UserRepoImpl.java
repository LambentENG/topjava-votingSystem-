package org.myvotingsystem.app.repository.implementations;

import org.myvotingsystem.app.model.User;
import org.myvotingsystem.app.repository.interfaces.UserRepo;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class UserRepoImpl implements UserRepo {

    @PersistenceContext
    private EntityManager em;

    @Override
    public User get(int id) {
        return em.find(User.class, id);
    }

    @Override
    public User getByName(String name) {
        List<User> users = em.createNamedQuery(User.GET_BY_NAME, User.class)
                .setParameter("name", name)
                .getResultList();
        return DataAccessUtils.singleResult(users);
    }

    @Override
    public List<User> getAll() {
        return em.createNamedQuery(User.GET_ALL, User.class).getResultList();
    }

    @Transactional
    @Override
    public User save(User user) {
        if (user.isNew()) {
            em.persist(user);
            return user;
        } else {
            return em.merge(user);
        }
    }

    @Transactional
    @Override
    public boolean delete(int id) {
        return em.createNamedQuery(User.DELETE).setParameter("id", id).executeUpdate() != 0;
    }
}
