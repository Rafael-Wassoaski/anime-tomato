package org.wassoaski.animeTomato.model;


import org.junit.Assert;
import org.junit.Test;

public class UserTest {

    private final String USER_NAME = "Rafael";
    private final String USER_PASSWORD = "Password";

    @Test
    public void souldHaveNameInUser(){
        User user = new User(USER_NAME, USER_PASSWORD);

        Assert.assertEquals(USER_NAME, user.getName());
    }

    @Test
    public void souldHavePasswordInUser(){
        User user = new User(USER_NAME, USER_PASSWORD);

        Assert.assertNotNull(user.getPassword());
    }
}
