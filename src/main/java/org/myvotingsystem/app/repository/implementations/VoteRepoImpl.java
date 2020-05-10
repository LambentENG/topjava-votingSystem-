package org.myvotingsystem.app.repository.implementations;

import org.myvotingsystem.app.model.Restaurant;
import org.myvotingsystem.app.model.User;
import org.myvotingsystem.app.model.Vote;
import org.myvotingsystem.app.repository.interfaces.VoteRepo;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class VoteRepoImpl implements VoteRepo {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Vote get(int id) {
        return em.find(Vote.class, id);
    }

    @Override
    public Vote getByUserIdAndDate(int userId, LocalDate date) {
        List<Vote> votes = em.createNamedQuery(Vote.GET_BY_USER_AND_DATE, Vote.class)
                .setParameter("userId", userId)
                .setParameter("date", date)
                .getResultList();
        return DataAccessUtils.singleResult(votes);
    }

    @Override
    public List<Vote> getAll() {
        return em.createNamedQuery(Vote.GET_ALL, Vote.class).getResultList();
    }

    @Override
    public List<Vote> getAllByDate(LocalDate date) {
        return em.createNamedQuery(Vote.GET_ALL_BY_DATE, Vote.class)
                .setParameter("date", date)
                .getResultList();
    }

    @Transactional
    @Override
    public Vote save(Vote vote, int userId, int restaurantId) {
        vote.setUser(em.getReference(User.class, userId));
        vote.setRestaurant(em.getReference(Restaurant.class, restaurantId));
        if (vote.isNew()) {
            em.persist(vote);
            return vote;
        } else {
            return em.merge(vote);
        }
    }

    @Transactional
    @Override
    public boolean delete(int id) {
        return em.createNamedQuery(Vote.DELETE).setParameter("id", id).executeUpdate() != 0;
    }

    @Transactional
    @Override
    public void deleteAll() {
        em.createNamedQuery(Vote.DELETE_ALL).executeUpdate();
    }
}
