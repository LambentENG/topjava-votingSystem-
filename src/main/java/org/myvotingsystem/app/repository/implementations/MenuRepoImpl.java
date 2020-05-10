package org.myvotingsystem.app.repository.implementations;

import org.myvotingsystem.app.model.Menu;
import org.myvotingsystem.app.model.Restaurant;
import org.myvotingsystem.app.repository.interfaces.MenuRepo;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class MenuRepoImpl implements MenuRepo {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Menu get(int id, int restaurantId) {
        Menu menu = em.find(Menu.class, id);
        return menu != null && menu.getRestaurant().getId() == restaurantId ? menu : null;
    }

    @Override
    public List<Menu> getAll() {
        return em.createNamedQuery(Menu.GET_ALL, Menu.class).getResultList();
    }

    @Override
    public List<Menu> getAllByDate(LocalDate date) {
        return em.createNamedQuery(Menu.GET_ALL_BY_DATE, Menu.class)
                .setParameter("date", date)
                .getResultList();
    }

    @Override
    public List<Menu> getAllByDate(LocalDate date, int restaurantId) {
        return em.createNamedQuery(Menu.GET_ALL_BY_DATE_AND_RESTAURANT, Menu.class)
                .setParameter("date", date)
                .setParameter("restaurantId", restaurantId)
                .getResultList();
    }

    @Transactional
    @Override
    public Menu save(Menu menu, int restaurantId) {
        menu.setRestaurant(em.getReference(Restaurant.class, restaurantId));
        if (menu.isNew()) {
            em.persist(menu);
            return menu;
        } else {
            return em.merge(menu);
        }
    }

    @Transactional
    @Override
    public boolean delete(int id, int restaurantId) {
        return em.createNamedQuery(Menu.DELETE)
                .setParameter("id", id)
                .setParameter("restaurantId", restaurantId)
                .executeUpdate() != 0;
    }

    @Transactional
    @Override
    public void deleteAll() {
        em.createNamedQuery(Menu.DELETE_ALL).executeUpdate();
    }
}
