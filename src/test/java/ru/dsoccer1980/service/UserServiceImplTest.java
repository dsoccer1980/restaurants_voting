package ru.dsoccer1980.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.dsoccer1980.model.User;


import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static ru.dsoccer1980.testdata.UserTestData.*;

public class UserServiceImplTest extends AbstractServiceTest {

    @Autowired
    private UserService service;

    @Test
    public void save() throws Exception {
        User newUser = new User("New User", "e@mail.ru", "pass");
        User user = service.create(newUser);
        assertEquals(newUser, user);
    }

    @Test
    public void delete() throws Exception {
        service.delete(USER_ID1);
        assertEquals(Arrays.asList(USER2, ADMIN), service.getAll());
    }

    @Test
    public void get() throws Exception {
        User user = service.get(USER_ID1);
        assertEquals(USER1, user);
    }

    @Test
    public void getAll() throws Exception {
        List<User> users = service.getAll();
        assertEquals(Arrays.asList(USER1, USER2, ADMIN), users);
    }

    @Test
    public void getByEmail() throws Exception {
        User searchUser = service.getByEmail(USER1_EMAIL);
        assertEquals(USER1, searchUser);
    }

}