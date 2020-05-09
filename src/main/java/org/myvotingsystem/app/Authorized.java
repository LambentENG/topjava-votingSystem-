package org.myvotingsystem.app;

import org.myvotingsystem.app.model.User;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

public class Authorized extends org.springframework.security.core.userdetails.User {
    private static final long serialVersionUID = 1L;
    private User user;
    public Authorized(User user) {
        super(user.getName(), user.getPassword(), true, true, true, true, user.getRoles());
        this.user = user;
    }

    private static Authorized safeGet() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        Object principal = auth.getPrincipal();
        return (principal instanceof Authorized) ? (Authorized) principal : null;
    }

    public int getId() {
        return user.getId();
    }

    public static Authorized get() {
        Authorized user = safeGet();
        Objects.requireNonNull(user, "No such user found");
        return user;
    }

    public static int id() {
        return get().user.getId();
    }
}
