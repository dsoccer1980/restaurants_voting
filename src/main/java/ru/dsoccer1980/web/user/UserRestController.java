package ru.dsoccer1980.web.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.dsoccer1980.web.AuthorizedUser;

import ru.dsoccer1980.model.User;

import ru.dsoccer1980.service.UserService;


@RestController
@RequestMapping(value = UserRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestController {

    static final String REST_URL = "/rest/user/";

    @Autowired
    private UserService userService;

    @GetMapping
    public User getProfile() {
        int id = AuthorizedUser.getId();
        return userService.get(id);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody User user) {
        userService.update(user);
    }

    @DeleteMapping()
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete() {
        int id = AuthorizedUser.getId();
        userService.delete(id);
    }
}