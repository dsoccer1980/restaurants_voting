package ru.dsoccer1980.web;

public class AuthorizedUser {

    private static int id; //= AbstractBaseEntity.START_SEQ;

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        AuthorizedUser.id = id;
    }

}
