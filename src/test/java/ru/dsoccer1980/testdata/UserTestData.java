package ru.dsoccer1980.testdata;

import ru.dsoccer1980.model.User;

import java.time.LocalDate;

public class UserTestData {

    public static final Integer USER_ID1 = 100000;
    public static final Integer USER_ID2 = 100001;
    public static final Integer ADMIN_ID = 100002;
    public static final String USER1_EMAIL = "user1@yandex.ru";
    public static final Integer USER_ID_NOT_EXIST = 900001;
    public static final User USER1 = new User(
            USER_ID1, "User1", USER1_EMAIL, "password");
    public static final User USER2 = new User(
            USER_ID2, "User2", "user2@yandex.ru", "password");
    public static final User ADMIN = new User(
            ADMIN_ID, "Admin", "admin@gmail.com", "admin", LocalDate.now(), true);


}
