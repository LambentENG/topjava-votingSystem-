package org.myvotingsystem.app.web.rest;

import org.myvotingsystem.app.Authorized;
import org.myvotingsystem.app.model.User;
import org.myvotingsystem.app.service.interfaces.UserService;
import org.myvotingsystem.app.to.UserTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;

import javax.validation.Valid;


import static org.myvotingsystem.app.web.rest.ProfileRestController.REST_URL;
import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping(REST_URL)
public class ProfileRestController {
    private static final Logger LOG = getLogger(ProfileRestController.class);
    static final String REST_URL = "/rest/profile";
    private UserService userService;

    @Autowired
    public ProfileRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public User get(@AuthenticationPrincipal Authorized authorizedUser) {
        LOG.info("get user {}", authorizedUser.getId());
        return userService.get(authorizedUser.getId());
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Valid @RequestBody UserTo userTo, @AuthenticationPrincipal Authorized authorizedUser) {
        userTo.setId(authorizedUser.getId());
        LOG.info("update {} with id={}", userTo, authorizedUser.getId());
        userService.update(userTo);
    }

    @DeleteMapping()
    public void delete(@AuthenticationPrincipal Authorized authorizedUser) {
        LOG.info("delete user {}", authorizedUser.getId());
        userService.delete(authorizedUser.getId());
    }
}
