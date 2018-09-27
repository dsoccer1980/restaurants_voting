package ru.dsoccer1980.web.user;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.dsoccer1980.model.User;
import ru.dsoccer1980.service.UserService;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping(value = UserAdminRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserAdminRestController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    static final String REST_URL = "/rest/admin/users/";

    @Autowired
    private UserService userService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createWithLocation(@RequestBody User user) {
        log.info("create user {}", user);
        User created = userService.create(user);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        log.info("delete user {}", id);
        userService.delete(id);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody User user) {
        log.info("update user {}", user);
        userService.update(user);
    }

    @GetMapping(value = "/{id}")
    public User getUserById(@PathVariable("id") int id) {
        log.info("get user by id {}", id);
        return userService.get(id);
    }

    @GetMapping
    public List<User> getAllUsers() {
        log.info("get all users");
        return userService.getAll();
    }

    @GetMapping(value = "/by")
    public User getUserByEmail(@RequestParam("email") String email) {
        log.info("get user by email {}", email);
        return userService.getByEmail(email);
    }

}