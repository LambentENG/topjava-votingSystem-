package org.myvotingsystem.app.util;

import org.myvotingsystem.app.model.User;

public class UserUtil {
    public static User prepareToSave(User user) {
        user.setPassword(PasswordUtil.encode(user.getPassword()));
        return user;
    }
}
