package org.myvotingsystem.app.repository.implementations;

import org.myvotingsystem.app.model.Dish;
import org.myvotingsystem.app.model.Menu;
import org.myvotingsystem.app.repository.interfaces.DishRepo;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class DishRepoImpl implements DishRepo {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Dish get(int id, int menuId) {
        Dish dish = em.find(Dish.class, id);
        return dish != null && dish.getMenu().getId() == menuId ? dish : null;
    }

    @Override
    public List<Dish> getAll() {
        return em.createNamedQuery(Dish.GET_ALL, Dish.class).getResultList();
    }

    @Override
    public List<Dish> getAllMenuDishes(int menuId) {
        return em.createNamedQuery(Dish.GET_ALL_MENU_DISHES, Dish.class)
                .setParameter("menuId", menuId)
                .getResultList();
    }

    @Transactional
    @Override
    public Dish save(Dish dish, int menuId) {
        if (!dish.isNew() && get(dish.getId(), menuId) == null) {
            return null;
        }
        dish.setMenu(em.getReference(Menu.class, menuId));
        if (dish.isNew()) {
            em.persist(dish);
            return dish;
        } else {
            return em.merge(dish);
        }
    }

    @Transactional
    @Override
    public boolean delete(int id, int menuId) {
        return em.createNamedQuery(Dish.DELETE)
                .setParameter("id", id)
                .setParameter("menuId", menuId)
                .executeUpdate() != 0;
    }

    @Transactional
    @Override
    public void deleteAllMenuDishes(int menuId) {
        em.createNamedQuery(Dish.DELETE_ALL_MENU_DISHES)
                .setParameter("menuId", menuId)
                .executeUpdate();
    }
}
