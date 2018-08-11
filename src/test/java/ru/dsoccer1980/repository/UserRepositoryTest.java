package ru.dsoccer1980.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.dsoccer1980.model.User;
import ru.dsoccer1980.repository.user.UserRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static ru.dsoccer1980.testdata.UserTestData.*;

public class UserRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void save() throws Exception {
        User newUser = new User("New User", "e@mail.ru", "pass");
        User user = userRepository.save(newUser);
        assertEquals(newUser, user);
    }

    @Test
    public void delete() throws Exception {
        userRepository.delete(USER_ID1);
        assertEquals(Arrays.asList(USER2, ADMIN), userRepository.getAll());
    }

    @Test
    public void get() throws Exception {
        User user = userRepository.get(USER_ID1);
        assertEquals(USER1, user);
    }

    @Test
    public void getAll() throws Exception {
        List<User> users = userRepository.getAll();
        assertEquals(Arrays.asList(USER1, USER2, ADMIN), users);
    }

    @Test
    public void getByEmail() throws Exception {
        User searchUser = userRepository.getByEmail(USER1_EMAIL);
        assertEquals(USER1, searchUser);
    }

}