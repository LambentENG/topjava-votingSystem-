package org.myvotingsystem.app.web.rest;

import org.myvotingsystem.app.model.User;
import org.myvotingsystem.app.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.slf4j.Logger;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.myvotingsystem.app.web.rest.AdminRestController.REST_URL;
import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping(REST_URL)
public class AdminRestController {
    private static final Logger LOG = getLogger(AdminRestController.class);
    static final String REST_URL = "/rest/admin/users";
    private UserService userService;

    @Autowired
    public AdminRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAll() {
        LOG.info("getAll user");
        return userService.getAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User get(@PathVariable Integer id) {
        LOG.info("get user {}", id);
        return userService.get(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> create(@Valid @RequestBody User user) {
        user.setId(null);
        LOG.info("create {}", user);
        User created = userService.create(user);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@PathVariable Integer id, @Valid @RequestBody User user) {
        user.setId(id);
        LOG.info("update {} with id={}", user, id);
        userService.update(user);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Integer id) {
        LOG.info("delete user {}", id);
        userService.delete(id);
    }
}
