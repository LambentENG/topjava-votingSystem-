package org.myvotingsystem.app.repository.interfaces;

import org.myvotingsystem.app.model.User;

import java.util.List;

public interface UserRepo {
    User get(int id);
    User getByName(String name);
    List<User> getAll();

    User save(User user);

    boolean delete(int id);
}
