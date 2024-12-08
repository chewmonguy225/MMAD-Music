package com.example;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;

import MMAD.AccountHandler;
import MMAD.Login;
import MMAD.User;

public class FriendTest {
    private AccountHandler ah;

    @Before
    public void setUp() {
        ah = AccountHandler.access();
        ah.createAccount("friend", "password");
        ah.logout();
        ah.createAccount("mainUser", "password");
    }

    @Test
    public void testingAddFriend() {
        // User object with only friend username
        User userToFollow = new User(new Login("friend", null));

        // Assuming the return value of followUser should be 1
        int result = ah.followUser(userToFollow);

        // Assert that the result is 1
        assertEquals(1, result);
    }

    @After
    public void cleanUp() {
        ah.deleteAccount();
    }

}
