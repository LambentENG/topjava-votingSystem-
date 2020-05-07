package org.myvotingsystem.app.util;

import org.myvotingsystem.app.model.Role;
import org.myvotingsystem.app.model.User;
import org.myvotingsystem.app.to.UserTo;

public class UserUtil {
    public static User prepareToSave(User user) {
        user.setPassword(PasswordUtil.encode(user.getPassword()));
        user.setName(user.getName().toLowerCase());
        return user;
    }

    public static User fromTo(UserTo userTo) {
        return new User(userTo.getId(), userTo.getName(), userTo.getPassword(), Role.ROLE_USER);
    }
}
