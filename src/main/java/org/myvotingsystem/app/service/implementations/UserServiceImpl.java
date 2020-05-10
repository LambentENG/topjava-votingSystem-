package org.myvotingsystem.app.service.implementations;

import org.myvotingsystem.app.model.User;
import org.myvotingsystem.app.repository.interfaces.UserRepo;
import org.myvotingsystem.app.service.interfaces.UserService;
import org.myvotingsystem.app.to.UserTo;
import org.myvotingsystem.app.util.UserUtil;
import org.myvotingsystem.app.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    private UserRepo userRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User create(User user) {
        Assert.notNull(user, "some user is required");
        return userRepo.save(UserUtil.prepareToSave(user));
    }

    @Override
    public User get(int id) {
        return ValidationUtil.checkNotFoundWithId(userRepo.get(id), id);
    }

    @Override
    public User getByName(String name) {
        Assert.notNull(name, "some user is required");
        return ValidationUtil.checkNotFound(userRepo.getByName(name), "name=" + name);
    }

    @Override
    public List<User> getAll() {
        return userRepo.getAll();
    }

    @Override
    public void update(User user) {
        Assert.notNull(user, "some user is required");
        userRepo.save(UserUtil.prepareToSave(user));
    }

    @Override
    public void update(UserTo userTo) {
        User user = UserUtil.fromTo(userTo);
        userRepo.save(UserUtil.prepareToSave(user));
    }

    @Override
    public void delete(int id) {
        ValidationUtil.checkNotFoundWithId(userRepo.delete(id), id);
    }
}
