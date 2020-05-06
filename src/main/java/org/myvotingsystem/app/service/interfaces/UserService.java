package org.myvotingsystem.app.service.interfaces;

import org.myvotingsystem.app.model.User;
import org.myvotingsystem.app.to.UserTo;

import java.util.List;

public interface UserService {
    User create(User user);

    User get(int id);
    User getByName(String name);
    List<User> getAll();

    void update(User user);
    void update(UserTo userTo);

    void delete(int id);
}
