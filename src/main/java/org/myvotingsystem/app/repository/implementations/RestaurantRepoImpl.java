package org.myvotingsystem.app.repository.implementations;

import org.myvotingsystem.app.model.Restaurant;
import org.myvotingsystem.app.repository.interfaces.RestaurantRepo;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class RestaurantRepoImpl implements RestaurantRepo {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Restaurant get(int id) {
        return em.find(Restaurant.class, id);
    }

    @Override
    public List<Restaurant> getAll() {
        return em.createNamedQuery(Restaurant.GET_ALL, Restaurant.class).getResultList();
    }

    @Transactional
    @Override
    public Restaurant save(Restaurant restaurant) {
        if (restaurant.isNew()) {
            em.persist(restaurant);
            return restaurant;
        } else {
            return em.merge(restaurant);
        }
    }

    @Transactional
    @Override
    public boolean delete(int id) {
        return em.createNamedQuery(Restaurant.DELETE).setParameter("id", id).executeUpdate() != 0;
    }

    @Transactional
    @Override
    public void deleteAll() {
        em.createNamedQuery(Restaurant.DELETE_ALL).executeUpdate();
    }
}
